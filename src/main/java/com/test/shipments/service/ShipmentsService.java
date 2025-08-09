package com.test.shipments.service;

import com.test.shipments.model.LocationEntity;
import com.test.shipments.model.PackageEntity;
import com.test.shipments.model.StateHistoryEntity;
import com.test.shipments.model.request.ShipmentRequest;
import com.test.shipments.repository.LocationRepository;
import com.test.shipments.repository.PackageRepository;
import com.test.shipments.repository.StateHistoryRepository;
import com.test.shipments.repository.StateRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String generatePackageCode(){
        return RandomStringUtils.secureStrong().nextAlphanumeric(10);
    }
}
