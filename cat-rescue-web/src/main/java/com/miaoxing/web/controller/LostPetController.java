package com.miaoxing.web.controller;

import com.miaoxing.business.entity.LostPet;
import com.miaoxing.business.service.LostPetService;
import com.miaoxing.web.dto.LostPetRequest;
import com.miaoxing.web.dto.StatusUpdateRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LostPetController {

    private final LostPetService lostPetService;

    public LostPetController(LostPetService lostPetService) {
        this.lostPetService = lostPetService;
    }

    @GetMapping("/lost-pets")
    public List<LostPet> getLostPets() {
        return lostPetService.findAll();
    }

    @PostMapping("/lost-pets")
    public Map<String, Object> addLostPet(@RequestBody LostPetRequest request) {
        LostPet lostPet = new LostPet();
        lostPet.setPetName(request.getName());
        lostPet.setStatus(request.getStatus());
        lostPet.setDescription(request.getDesc());
        lostPet.setContact(request.getContact());
        lostPet.setImageUrl(request.getImg());
        return successMap(lostPetService.add(lostPet));
    }

    @PutMapping("/lost-pets/{id}")
    public Map<String, Object> updateLostPetStatus(@PathVariable Integer id, @RequestBody StatusUpdateRequest request) {
        return successMap(lostPetService.updateStatus(id, request.getStatus()));
    }

    @DeleteMapping("/lost-pets/{id}")
    public Map<String, Object> deleteLostPet(@PathVariable Integer id) {
        return successMap(lostPetService.deleteById(id));
    }

    private Map<String, Object> successMap(boolean success) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
}
