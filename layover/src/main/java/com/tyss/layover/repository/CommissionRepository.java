package com.tyss.layover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Integer> {

}
