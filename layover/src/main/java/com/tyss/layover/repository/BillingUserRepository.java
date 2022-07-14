package com.tyss.layover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.BillingUser;

public interface BillingUserRepository extends JpaRepository<BillingUser, Integer> {

}
