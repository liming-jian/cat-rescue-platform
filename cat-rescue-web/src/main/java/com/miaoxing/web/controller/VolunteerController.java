package com.miaoxing.web.controller;

import com.miaoxing.business.entity.Volunteer;
import com.miaoxing.business.service.VolunteerService;
import com.miaoxing.web.dto.VolunteerRequest;
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
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/volunteers")
    public Map<String, Object> addVolunteer(@RequestBody VolunteerRequest request) {
        Volunteer volunteer = new Volunteer();
        volunteer.setName(request.getName());
        volunteer.setPhone(request.getPhone());
        volunteer.setReason(request.getReason());
        return successMap(volunteerService.add(volunteer));
    }

    @GetMapping("/volunteers")
    public List<Volunteer> getVolunteers() {
        return volunteerService.findAll();
    }

    @DeleteMapping("/volunteers/{id}")
    public Map<String, Object> deleteVolunteer(@PathVariable Integer id) {
        return successMap(volunteerService.deleteById(id));
    }

    private Map<String, Object> successMap(boolean success) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
}
