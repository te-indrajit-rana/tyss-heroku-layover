package com.tyss.layover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
