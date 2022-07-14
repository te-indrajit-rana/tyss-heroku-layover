package com.tyss.layover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.LayoverRequest;

public interface LayoverRequestRepository extends JpaRepository<LayoverRequest, Integer> {

}
