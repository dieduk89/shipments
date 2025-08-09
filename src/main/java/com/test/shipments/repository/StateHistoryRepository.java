package com.test.shipments.repository;

import com.test.shipments.model.StateHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateHistoryRepository extends JpaRepository<StateHistoryEntity, Long> {

}
