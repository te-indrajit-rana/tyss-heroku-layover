package com.tyss.layover.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.OtpLog;

public interface OtpLogRepository extends JpaRepository<OtpLog, Integer> {

	Optional<OtpLog> findByEmail(String username);
	
	Optional<OtpLog> findByOtp(Integer otp);

	Optional<OtpLog> findByUserId(Integer userId);

}
