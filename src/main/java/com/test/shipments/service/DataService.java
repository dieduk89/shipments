package com.test.shipments.service;

import com.test.shipments.model.LocationEntity;
import com.test.shipments.model.StateEntity;
import com.test.shipments.model.request.LocationRequest;
import com.test.shipments.repository.LocationRepository;
import com.test.shipments.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StateRepository stateRepository;

    public List<LocationEntity> getLocations(){
        return locationRepository.findAll();
    }

    public List<StateEntity> getStates(){
        return stateRepository.findAll();
    }

    public LocationEntity addLocation(LocationRequest request) throws Exception{
        LocationEntity locationEntity = locationRepository.findByCode(request.getCode()).orElse(null);
        if(locationEntity != null){
            throw new Exception("Location already registered");
        }


        // La migración inicial entra en conflicto con la secuencia en BD, se puede mejorar actualizando el index
        Long nextId = locationRepository.getMaxId() + 1L;

        LocationEntity newLocationEntity = new LocationEntity();
        newLocationEntity.setId(nextId);
        newLocationEntity.setCode(request.getCode());
        newLocationEntity.setName(request.getName());
        newLocationEntity.setAddress(request.getAddress());
        newLocationEntity.setCity(request.getCity());
        newLocationEntity.setCountry(request.getCountry());
        newLocationEntity.setZipCode(request.getZipCode());
        newLocationEntity.setGeolocation(request.getGeolocation());
        newLocationEntity = locationRepository.save(newLocationEntity);

        return newLocationEntity;
    }

}
