package com.test.shipments.controller;

import com.test.shipments.model.LocationEntity;
import com.test.shipments.model.PackageEntity;
import com.test.shipments.model.StateEntity;
import com.test.shipments.model.request.LocationRequest;
import com.test.shipments.model.request.ShipmentRequest;
import com.test.shipments.repository.LocationRepository;
import com.test.shipments.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/data")
public class DataController {

    @Autowired
    DataService dataService;
    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    private ResponseEntity<Object> getLocations(){
        try {
            List<LocationEntity> list = dataService.getLocations();
            return ResponseEntity.ok(list);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/states", method = RequestMethod.GET)
    private ResponseEntity<Object> getStates(){
        try {
            List<StateEntity> list = dataService.getStates();
            return ResponseEntity.ok(list);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    private ResponseEntity<Object> addLocation(@RequestBody LocationRequest request){
        try {
            LocationEntity locationEntity = dataService.addLocation(request);
            return ResponseEntity.ok(locationEntity);



        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
