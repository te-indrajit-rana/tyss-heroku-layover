package com.tyss.layover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
