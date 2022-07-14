package com.tyss.layover.controller;
import static com.tyss.layover.constant.LayoverConstant.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tyss.layover.pojo.Userpojo;
import com.tyss.layover.response.LayoverResponse;
import com.tyss.layover.service.LayoverUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/layover/user")
@SecurityRequirement(name = "layover-api")
public class LayoverUserController {

	private final LayoverUserService layoverUserService;

	@PostMapping("/save")
	public ResponseEntity<LayoverResponse> saveUser(@Valid @RequestBody Userpojo userpojo) {
		Userpojo savedUser = layoverUserService.saveUser(userpojo);
		LayoverResponse layoverResponse = LayoverResponse.builder().isError(false).message(USER_SAVED_SUCCESSFULLY)
				.data(savedUser).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(layoverResponse);
	}

	@PreAuthorize(AUTHORITIES)
	@GetMapping("/fetch/{id}")
	public ResponseEntity<LayoverResponse> fetchByUserId(@PathVariable("id") Integer id) {
		Userpojo userpojo = layoverUserService.fetchByUserId(id);
		LayoverResponse layoverResponse = LayoverResponse.builder().isError(false).message(FETCH_SUCCESSFULLY)
				.data(userpojo).build();
		return ResponseEntity.ok().body(layoverResponse);
	}

	@PreAuthorize(AUTHORITIES)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<LayoverResponse> deleteByUserId(@PathVariable("id") Integer id) {
		LayoverResponse layoverResponse = LayoverResponse.builder().isError(false)
				.message(layoverUserService.deleteByUserId(id)).build();
		return ResponseEntity.ok().body(layoverResponse);
	}
	
	@PreAuthorize(AUTHORITIES)
	@PutMapping("/update/{id}")
	public ResponseEntity<LayoverResponse> updateUser(@RequestParam("file") MultipartFile multipartFile,@PathVariable("id")Integer id) {
		String user = layoverUserService.updateUser(multipartFile, id);
		LayoverResponse layoverResponse = LayoverResponse.builder().isError(false).message(user).build();
		return ResponseEntity.ok().body(layoverResponse);

	}
}
