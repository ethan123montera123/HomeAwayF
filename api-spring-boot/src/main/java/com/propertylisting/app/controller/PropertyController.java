package com.propertylisting.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propertylisting.app.dto.Response;
import com.propertylisting.app.model.Property;
import com.propertylisting.app.service.PropertyService;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app's URL
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public Response<List<Property>> getAllProperties() {
        try {
            List<Property> properties = propertyService.getAllProperties();
            return new Response<>(true, null, properties);
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @GetMapping("/{id}")
    public Response<Property> getPropertyById(@PathVariable Long id) {
        try {
            return new Response<>(true, null, propertyService.getPropertyById(id));
    
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @PostMapping
    public Response<Property> addProperty(@RequestBody Property property) {
        try {
            return new Response<>(true, "Properties created successfully.", propertyService.addProperty(property));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @PutMapping("/{id}")
    public Response<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        try {
            return new Response<>(true, "Properties updated successfully.", propertyService.updateProperty(id, property));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteProperty(@PathVariable Long id) {
        try {
            return new Response<>(true, "Property deleted successfully.", propertyService.deleteProperty(id));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }
}
