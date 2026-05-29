const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');
const bodyParser = require('body-parser');
const fs = require('fs');

const app = express();
app.use(cors());
app.use(bodyParser.json({ limit: '50mb' }));

// 1. 挂载图片
// 【重要】路径里的单斜杠 \ 必须全部改成双斜杠 \\
const IMG_PATH = 'C:\\Users\\HP\\Desktop\\《喵星守护站》-流浪猫救助平台-202302150044 李铭健\\图片';
if (!fs.existsSync(IMG_PATH)) {
    try { fs.mkdirSync(IMG_PATH, { recursive: true }); } catch(e){}
    console.error(`❌ 检查路径: ${IMG_PATH}`);
}
app.use('/myImages', express.static(IMG_PATH));

// 2. 数据库连接
const db = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    user: 'root',
    password: '',
    database: 'cat_rescue'
});

db.connect(err => {
    if (err) console.error('❌ 数据库连接失败:', err.message);
    else console.log('✅ 数据库连接成功 (Port 3306)');
});

function fixImagePath(url) {
    if (!url) return '';
    if (url.startsWith('http') || url.startsWith('data:')) return url;
    let cleanUrl = url.replace(/\\/g, '/');
    const fileName = cleanUrl.split('/').pop();
    return 'http://localhost:3000/myImages/' + fileName;
}

// --- 发送通知 (核心功能) ---
function sendNotification(msg, target='all') {
    const sql = "INSERT INTO system_notifications (message, target) VALUES (?, ?)";
    db.query(sql, [msg, target], err => { 
        if(err) console.error("通知发送失败:", err); 
        else console.log("📢 通知已发送:", msg);
    });
}

// ========================
// 🔑 注册、登录与重置密码
// ========================

// 1. 注册
app.post('/register', (req, res) => {
    const { username, password } = req.body;
    if (!username || !password) return res.json({ success: false, message: '不能为空' });
    const sql = 'INSERT INTO users (username, password, role) VALUES (?, ?, ?)';
    db.query(sql, [username, password, 'user'], (err) => {
        if (err) return res.json({ success: false, message: '用户名已存在' });
        res.json({ success: true });
    });
});

// 2. 登录
app.post('/login', (req, res) => {
    const { username, password } = req.body;
    const sql = 'SELECT * FROM users WHERE username=? AND password=?';
    db.query(sql, [username, password], (err, results) => {
        if (err || results.length === 0) return res.json({ success: false, message: '账号或密码错误' });
        res.json({ success: true, user: { username: results[0].username, role: results[0].role } });
    });
});

// 3. 重置密码接口
app.post('/reset-password', (req, res) => {
    const { username, newPassword } = req.body;
    if (!username || !newPassword) return res.json({ success: false, message: '信息不完整' });

    // 直接更新数据库里的密码
    const sql = 'UPDATE users SET password=? WHERE username=?';
    db.query(sql, [newPassword, username], (err, result) => {
        if (err) return res.json({ success: false, message: '数据库错误' });
        
        // affectedRows 为 0 说明没找到这个用户名
        if (result.affectedRows === 0) {
            return res.json({ success: false, message: '用户名不存在，请先注册' });
        }
        
        console.log(`🔐 用户 ${username} 已重置密码`);
        res.json({ success: true });
    });
});

// ========================
// 🐱 业务功能接口 (通知、领养、寻猫)
// ========================

// 获取通知
app.get('/notifications', (req, res) => {
    db.query('SELECT * FROM system_notifications ORDER BY id DESC LIMIT 10', (err, results) => res.json(results || []));
});

// --- 1. 待领养猫咪 ---
app.get('/adoption-cats', (req, res) => {
    db.query('SELECT * FROM cats ORDER BY id DESC', (err, r) => {
        if(err) return res.json([]);
        res.json(r.map(c => ({ ...c, image_url: fixImagePath(c.image_url) })));
    });
});

app.post('/adoption-cats', (req, res) => {
    const { name, status, description, image_url, age, gender } = req.body;
    db.query('INSERT INTO cats (name, status, description, image_url, age, gender) VALUES (?, ?, ?, ?, ?, ?)', 
    [name, status, description, image_url, age, gender], (e) => {
        if(!e) sendNotification(`😻 新猫上架：${name} 正在寻找新家！`);
        res.json({success:!e});
    });
});

app.put('/adoption-cats/:id', (req, res) => {
    const status = req.body.status; 
    db.query('UPDATE cats SET status=? WHERE id=?', [status, req.params.id], (err) => {
        if(!err) sendNotification(`🏠 喜讯：猫咪已被成功领养！`);
        res.json({success: !err});
    });
});

app.delete('/adoption-cats/:id', (req, res) => {
    db.query('DELETE FROM cats WHERE id=?', [req.params.id], (e) => {
        if(!e) sendNotification(`ℹ️ 管理员更新了猫咪库`); 
        res.json({success:!e});
    });
});

// --- 2. 寻猫/招领 ---
app.get('/lost-pets', (req, res) => {
    db.query('SELECT * FROM lost_pets ORDER BY id DESC', (err, r) => {
        if(err) return res.json([]);
        res.json(r.map(p => ({ ...p, image_url: fixImagePath(p.image_url) })));
    });
});

app.post('/lost-pets', (req, res) => {
    const { name, status, desc, contact, img } = req.body;
    db.query('INSERT INTO lost_pets (pet_name, status, description, contact, image_url) VALUES (?, ?, ?, ?, ?)', 
    [name, status, desc, contact, img], (e) => {
        if(!e) sendNotification(`🆘 互助信息更新：${name}`);
        res.json({success:!e});
    });
});

app.put('/lost-pets/:id', (req, res) => {
    const status = req.body.status; 
    db.query('UPDATE lost_pets SET status=? WHERE id=?', [status, req.params.id], (err) => {
        if(!err) sendNotification(`🎉 好消息：走失的宠物已经回家了！`);
        res.json({success: !err});
    });
});

// 【修改处】这里加上了你要求的通知功能
app.delete('/lost-pets/:id', (req, res) => {
    db.query('DELETE FROM lost_pets WHERE id=?', [req.params.id], (e) => {
        if(!e) sendNotification('ℹ️ 管理员更新了寻猫/招领信息'); // 加了这一行
        res.json({success:!e});
    });
});

// --- 3. 领养申请 & 志愿者 ---
app.post('/adopt', (req, res) => { 
    db.query('INSERT INTO adoptions (cat_name, applicant_name, phone, housing, experience) VALUES (?, ?, ?, ?, ?)', 
    [req.body.catName, req.body.applicant_name, req.body.phone, req.body.housing, req.body.experience], (e)=>{
        if(!e) sendNotification(`📝 新领养申请：${req.body.applicant_name}`, 'admin');
        res.json({success:!e});
    });
});

app.post('/volunteers', (req, res) => {
    db.query('INSERT INTO volunteers (name, phone, reason) VALUES (?, ?, ?)', [req.body.name, req.body.phone, req.body.reason], (e) => {
        if(!e) sendNotification(`🙋‍♂️ 新志愿者申请：${req.body.name}`, 'admin');
        res.json({success:!e});
    });
});

app.get('/adoptions', (req,r)=>{db.query('SELECT * FROM adoptions ORDER BY id DESC',(e,d)=>r.json(d||[]))});
app.get('/volunteers', (req,r)=>{db.query('SELECT * FROM volunteers ORDER BY id DESC',(e,d)=>r.json(d||[]))});

app.delete('/adoptions/:id', (req, res) => {
    db.query('DELETE FROM adoptions WHERE id=?', [req.params.id], (e) => res.json({success:!e}));
});

app.delete('/volunteers/:id', (req, res) => {
    db.query('DELETE FROM volunteers WHERE id=?', [req.params.id], (e) => res.json({success:!e}));
});

app.listen(3000, () => console.log('🚀 服务器已启动: http://localhost:3000'));