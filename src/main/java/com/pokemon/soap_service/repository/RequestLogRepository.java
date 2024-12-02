package com.pokemon.soap_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.soap_service.model.RequestLog;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
} 