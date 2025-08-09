package com.test.shipments.controller;

import com.test.shipments.model.PackageEntity;
import com.test.shipments.model.request.ShipmentRequest;
import com.test.shipments.model.response.StatePackageResponse;
import com.test.shipments.service.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/check/{code}",method = RequestMethod.GET)
    private ResponseEntity<Object> checkShipment(@PathVariable String code){
        try {
            StatePackageResponse response = shipmentsService.checkShipment(code);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private ResponseEntity<Object> listShipments(@RequestParam Map<String, String> requestParams){
        try {
            List<String> list = shipmentsService.listShipments(requestParams);
            return ResponseEntity.ok(list);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/update/{code}",method = RequestMethod.PUT)
    private ResponseEntity<Object> updateShipment(@PathVariable String code){
        try {
            StatePackageResponse response = shipmentsService.updateShipment(code);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
