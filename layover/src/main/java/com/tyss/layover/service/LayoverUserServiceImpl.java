package com.tyss.layover.service;

import static com.tyss.layover.constant.LayoverConstant.GIVEN_USER_IS_NOT_PRESENT;
import static com.tyss.layover.constant.LayoverConstant.USER_HAS_BEEN_DELETED_SUCCESSFULLY;
import static com.tyss.layover.constant.LayoverConstant.USER_UPDATED_SUCCESSFULLY;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tyss.layover.entity.User;
import com.tyss.layover.exception.UserNotFoundException;
import com.tyss.layover.pojo.Userpojo;
import com.tyss.layover.repository.UserRepository;
import com.tyss.layover.util.SSSUploadFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LayoverUserServiceImpl implements LayoverUserService {

	private final UserRepository userRepository;

	private final SSSUploadFile sssUploadFile;

	@Override
	public Userpojo saveUser(Userpojo userpojo) {
		User user = new User();
		BeanUtils.copyProperties(userpojo, user);
		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, userpojo);
		return userpojo;

	}

	@Override
	public Userpojo fetchByUserId(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(GIVEN_USER_IS_NOT_PRESENT));
		Userpojo userpojo = new Userpojo();
		BeanUtils.copyProperties(user, userpojo);
		return userpojo;
	}

	@Transactional
	@Override
	public String deleteByUserId(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(GIVEN_USER_IS_NOT_PRESENT));
		user.setIsDeleted(true);
		return USER_HAS_BEEN_DELETED_SUCCESSFULLY;
	}

	@Override
	public String updateUser(MultipartFile file,Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(GIVEN_USER_IS_NOT_PRESENT));
		if (file != null) {
			sssUploadFile.deleteS3Folder(user.getLogo());
			String logoPath = sssUploadFile.uploadFile(file);
			user.setLogo(logoPath);
		}
		Userpojo userpojo = new Userpojo();
		
		BeanUtils.copyProperties(user, userpojo);
		saveUser(userpojo);

		return USER_UPDATED_SUCCESSFULLY;
	}

}
