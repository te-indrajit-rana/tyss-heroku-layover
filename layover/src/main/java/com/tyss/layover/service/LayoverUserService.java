package com.tyss.layover.service;

import org.springframework.web.multipart.MultipartFile;

import com.tyss.layover.pojo.Userpojo;

public interface LayoverUserService {

	Userpojo saveUser(Userpojo userpojo);

	Userpojo fetchByUserId(Integer id);

	String deleteByUserId(Integer id);
	
	String updateUser(MultipartFile file,Integer id);
}
