package com.miaoxing.web.controller;

import com.miaoxing.business.entity.Cat;
import com.miaoxing.business.service.CatService;
import com.miaoxing.web.dto.CatQueryRequest;
import com.miaoxing.web.dto.CatRequest;
import com.miaoxing.web.dto.StatusUpdateRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/adoption-cats")
    public List<Cat> getCats() {
        return catService.findAll();
    }

    @GetMapping("/adoption-cats/search")
    public List<Cat> searchCats(@RequestParam(name = "status", required = false) String status,
                                @RequestParam(name = "gender", required = false) String gender,
                                @RequestParam(name = "ageKeyword", required = false) String ageKeyword,
                                @RequestParam(name = "nameKeyword", required = false) String nameKeyword) {
        return catService.findByConditions(status, gender, ageKeyword, nameKeyword);
    }

    @PostMapping("/adoption-cats")
    public Map<String, Object> addCat(@RequestBody CatRequest request) {
        Cat cat = new Cat();
        cat.setName(request.getName());
        cat.setStatus(request.getStatus());
        cat.setDescription(request.getDescription());
        cat.setImageUrl(request.getImageUrl());
        cat.setAge(request.getAge());
        cat.setGender(request.getGender());
        return successMap(catService.add(cat));
    }

    @PutMapping("/adoption-cats/{id}")
    public Map<String, Object> updateCatStatus(@PathVariable Integer id, @RequestBody StatusUpdateRequest request) {
        return successMap(catService.updateStatus(id, request.getStatus()));
    }

    @DeleteMapping("/adoption-cats/{id}")
    public Map<String, Object> deleteCat(@PathVariable Integer id) {
        return successMap(catService.deleteById(id));
    }

    private Map<String, Object> successMap(boolean success) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
}
