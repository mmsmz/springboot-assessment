package com.integrator.service.repository;

import com.integrator.service.entity.AuditEventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditEventRepository extends JpaRepository<AuditEventsEntity, Integer> {


}
