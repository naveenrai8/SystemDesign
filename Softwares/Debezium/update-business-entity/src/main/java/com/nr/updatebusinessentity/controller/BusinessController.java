package com.nr.updatebusinessentity.controller;

import com.nr.updatebusinessentity.dto.BusinessResponseDto;
import com.nr.updatebusinessentity.service.BusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/business")
public class BusinessController {
    private final BusinessService businessService;

    @GetMapping("{id}")
    private ResponseEntity<BusinessResponseDto> businessEntity(@PathVariable int businessId){
    
    }
}
