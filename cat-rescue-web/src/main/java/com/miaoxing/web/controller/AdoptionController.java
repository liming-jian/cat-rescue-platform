package com.miaoxing.web.controller;

import com.miaoxing.business.entity.Adoption;
import com.miaoxing.business.entity.view.AdoptionView;
import com.miaoxing.business.service.AdoptionService;
import com.miaoxing.web.dto.AdoptionRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdoptionController {

    private final AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping("/adopt")
    public Map<String, Object> adopt(@RequestBody AdoptionRequest request) {
        Adoption adoption = new Adoption();
        adoption.setCatName(request.getCatName());
        adoption.setApplicantName(request.getApplicantName());
        adoption.setPhone(request.getPhone());
        adoption.setHousing(request.getHousing());
        adoption.setExperience(request.getExperience());
        return successMap(adoptionService.add(adoption));
    }

    @GetMapping("/adoptions")
    public List<AdoptionView> getAdoptions() {
        return adoptionService.findAll();
    }

    @DeleteMapping("/adoptions/{id}")
    public Map<String, Object> deleteAdoption(@PathVariable Integer id) {
        return successMap(adoptionService.deleteById(id));
    }

    private Map<String, Object> successMap(boolean success) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
}
