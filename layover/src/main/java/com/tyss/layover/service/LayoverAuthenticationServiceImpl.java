package com.tyss.layover.service;

import static com.tyss.layover.constant.LayoverConstant.ACCEPTED;
import static com.tyss.layover.constant.LayoverConstant.ACCOUNT_HAS_BEEN_NOT_APPROVED_BY_ADMIN;
import static com.tyss.layover.constant.LayoverConstant.GENERATED_OTP;
import static com.tyss.layover.constant.LayoverConstant.INVALID_EMAIL_ADDRESS;
import static com.tyss.layover.constant.LayoverConstant.ONE_TIME_PASSCODE_HAS_BEEN_SENT_TO;
import static com.tyss.layover.constant.LayoverConstant.ONE_TIME_PASSWORD_IS;
import static com.tyss.layover.constant.LayoverConstant.OTP_EXPIRED;
import static com.tyss.layover.constant.LayoverConstant.OTP_FOR_AUTHENTICATION;
import static com.tyss.layover.constant.LayoverConstant.OTP_IS_NOT_VALID;
import static com.tyss.layover.constant.LayoverConstant.USER_HAS_BEEN_DELETED;
import static com.tyss.layover.constant.LayoverConstant.USER_NOT_FOUND_EXCEPTION;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.tyss.layover.entity.OtpLog;
import com.tyss.layover.entity.User;
import com.tyss.layover.exception.AccountNotApprovedException;
import com.tyss.layover.exception.EmailInterruptionException;
import com.tyss.layover.exception.IncorrectOtp;
import com.tyss.layover.exception.UserNotFoundException;
import com.tyss.layover.repository.OtpLogRepository;
import com.tyss.layover.repository.UserRepository;
import com.tyss.layover.util.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LayoverAuthenticationServiceImpl implements LayoverAuthenticationService {

	private final JavaMailSender javaMailSender;

	private final UserRepository userRepository;

	private final OtpLogRepository otpLogRepository;

	private final JwtUtils jwtUtils;

	@Override
	public String authenticate(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));
		if (!user.getStatus().equalsIgnoreCase(ACCEPTED)) {

			log.info(ACCOUNT_HAS_BEEN_NOT_APPROVED_BY_ADMIN);
			throw new AccountNotApprovedException(ACCOUNT_HAS_BEEN_NOT_APPROVED_BY_ADMIN);
		}
		if (Boolean.TRUE.equals(user.getIsDeleted())) {
			log.info(USER_HAS_BEEN_DELETED);
			throw new UserNotFoundException(USER_HAS_BEEN_DELETED);
		}

		return sendEmail(user);
	}

	@Async
	private String sendEmail(User user) {

		SecureRandom random = new SecureRandom();
		try {
			int otp = random.nextInt(900000) + 100000;
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(user.getEmail());
			messageHelper.setSubject(OTP_FOR_AUTHENTICATION);
			messageHelper.setText(ONE_TIME_PASSWORD_IS + otp);
			log.info(GENERATED_OTP + otp);
			javaMailSender.send(mimeMessage);
			OtpLog otpLog = new OtpLog();
			Optional<OtpLog> findByUser = otpLogRepository.findByUserId(user.getUserId());
			if (findByUser.isPresent()) {
				otpLog.setOtpLogId(findByUser.get().getOtpLogId());
			} 
			otpLog.setLogDatetime(LocalDateTime.now());
			otpLog.setOtp(otp);
			otpLog.setEmail(user.getEmail());
			otpLog.setUserId(user.getUserId());
			otpLog.setRole(user.getUserType());

			otpLogRepository.save(otpLog);
			return ONE_TIME_PASSCODE_HAS_BEEN_SENT_TO + user.getEmail();
		} catch (Exception exception) {
			log.info(INVALID_EMAIL_ADDRESS);
			throw new EmailInterruptionException(exception.getMessage());
		}
	}

	@Override
	public Map<String, Object> validateOtp(Integer otp) {
		OtpLog otpLog = otpLogRepository.findByOtp(otp).orElseThrow(() -> new IncorrectOtp(OTP_IS_NOT_VALID));
		LocalDateTime localDateTime = otpLog.getLogDatetime().plusMinutes(10);
		LocalDateTime now = LocalDateTime.now();
		if (now.isAfter(localDateTime)) {
			throw new IncorrectOtp(OTP_EXPIRED);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + otpLog.getRole()));
		return jwtUtils.generateToken(otpLog, authorities);
	}

}
