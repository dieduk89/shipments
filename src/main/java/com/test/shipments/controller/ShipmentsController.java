package com.test.shipments.controller;

import com.test.shipments.model.PackageEntity;
import com.test.shipments.model.request.ShipmentRequest;
import com.test.shipments.service.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shipments")
public class ShipmentsController {

    @Autowired
    private ShipmentsService shipmentsService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private ResponseEntity<Object> createShipment(@RequestBody ShipmentRequest request){
        try {
            PackageEntity packageEntity = shipmentsService.createShipment(request);
            return ResponseEntity.ok(packageEntity);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
