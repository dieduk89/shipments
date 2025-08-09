package com.test.shipments.repository;

import com.test.shipments.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @Query
    public Optional<LocationEntity> findByCode(String code);
}
