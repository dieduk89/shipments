package com.test.shipments.service;

import com.test.shipments.model.LocationEntity;
import com.test.shipments.model.PackageEntity;
import com.test.shipments.model.StateEntity;
import com.test.shipments.model.StateHistoryEntity;
import com.test.shipments.model.request.ShipmentRequest;
import com.test.shipments.model.response.ListShipmentsResponse;
import com.test.shipments.model.response.StatePackageResponse;
import com.test.shipments.repository.LocationRepository;
import com.test.shipments.repository.PackageRepository;
import com.test.shipments.repository.StateHistoryRepository;
import com.test.shipments.repository.StateRepository;
import jakarta.websocket.OnClose;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShipmentsService {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    StateHistoryRepository stateHistoryRepository;


    public PackageEntity createShipment(ShipmentRequest request) throws Exception{

        LocationEntity sourceLocation = locationRepository.findByCode(request.getSource()).orElse(null);
        LocationEntity destinationLocation = locationRepository.findByCode(request.getDestination()).orElse(null);

        if(sourceLocation == null || destinationLocation == null) {
            throw new Exception("Source location or destination location not found");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setCode(generatePackageCode());
        packageEntity.setSourceId(sourceLocation.getId());
        packageEntity.setDestinationId(destinationLocation.getId());
        packageEntity.setWeight(request.getWeight());
        packageEntity.setDescription(request.getDescription());
        packageEntity.setCurrentStateId(1L);
        packageEntity.setCreateDate(currentDateTime);
        packageEntity = packageRepository.save(packageEntity);

        StateHistoryEntity stateHistoryEntity = new StateHistoryEntity();
        stateHistoryEntity.setStateId(1L);
        stateHistoryEntity.setPackageId(packageEntity.getId());
        stateHistoryEntity.setChangeDate(currentDateTime);
        stateHistoryRepository.save(stateHistoryEntity);

        return packageEntity;
    }

    public StatePackageResponse checkShipment(String code) throws Exception{

        PackageEntity packageEntity = packageRepository.findByCode(code).orElse(null);
        if(packageEntity == null) {
            throw new Exception("Package not found");
        }

        return new StatePackageResponse(
                code,
                stateRepository.getReferenceById(packageEntity.getCurrentStateId()).getName()
        );
    }

    public List<String> listShipments(Map<String, String> requestParams){
        String source = "none";
        String destination = "none";
        String state = "none";
        String date = "none";

        if(requestParams.get("source") != null) {
            source = requestParams.get("source");
        }
        if(requestParams.get("destination") != null) {
          destination = requestParams.get("destination");
        }
        if(requestParams.get("state") != null) {
            state = requestParams.get("state");
        }
        if(requestParams.get("date") != null) {
            date = requestParams.get("date");
        }

        return packageRepository.listShipments(source, destination, state, date);
    }

    public StatePackageResponse updateShipment(String code) throws Exception{

        PackageEntity packageEntity = packageRepository.findByCode(code).orElse(null);
        if(packageEntity == null) {
            throw new Exception("Package not found");
        }

        //Implementar llamada a api externa para el tracking del paquete
        // -- send package code
        // -- receive new state
        // -- update current state and state history

        Long newStateId = packageEntity.getCurrentStateId() + 1L;
        StateEntity stateEntity = stateRepository.findById(newStateId).orElse(null);
        if(stateEntity == null) {
            throw new Exception("State not found");
        }

        packageEntity.setCurrentStateId(newStateId);
        packageRepository.save(packageEntity);

        LocalDateTime currentDateTime = LocalDateTime.now();

        // ----- MEJORAS ---> se puede implementar este insert en un triger update para la tabla packages
        StateHistoryEntity stateHistoryEntity = new StateHistoryEntity();
        stateHistoryEntity.setStateId(newStateId);
        stateHistoryEntity.setPackageId(packageEntity.getId());
        stateHistoryEntity.setChangeDate(currentDateTime);
        stateHistoryRepository.save(stateHistoryEntity);

        return new StatePackageResponse(
                code,
                stateRepository.getReferenceById(newStateId).getName()
        );
    }

    private String generatePackageCode(){
        return RandomStringUtils.secureStrong().nextAlphanumeric(10);
    }
}
