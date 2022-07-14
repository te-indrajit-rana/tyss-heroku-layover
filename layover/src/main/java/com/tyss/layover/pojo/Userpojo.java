package com.tyss.layover.pojo;

import static com.tyss.layover.constant.LayoverConstant.COUNTRY_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.COUNTRY_CANNOT_BE_NULL;
import static com.tyss.layover.constant.LayoverConstant.INVALID_EMAIL_ADDRESS;
import static com.tyss.layover.constant.LayoverConstant.INVALID_PHONE_NUMBER;
import static com.tyss.layover.constant.LayoverConstant.LOCATION_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.LOCATION_CANNOT_BE_NULL;
import static com.tyss.layover.constant.LayoverConstant.PERSON_NAME_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.PERSON_NAME_CANNOT_BE_NULL;
import static com.tyss.layover.constant.LayoverConstant.USER_ADDRESS_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.USER_ADDRESS_CANNOT_BE_NULL;
import static com.tyss.layover.constant.LayoverConstant.USER_NAME_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.USER_NAME_CANNOT_BE_NULL;
import static com.tyss.layover.constant.LayoverConstant.USER_TITLE_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.USER_TITLE_CANNOT_BE_NULL;
import static com.tyss.layover.constant.LayoverConstant.USER_TYPE_CANNOT_BE_BLANK;
import static com.tyss.layover.constant.LayoverConstant.USER_TYPE_CANNOT_BE_NULL;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Userpojo {

	
	private Integer userId;
	@NotBlank(message =USER_NAME_CANNOT_BE_BLANK)
	@NotBlank(message = USER_NAME_CANNOT_BE_NULL)
	private String userName;
	@NotBlank(message = USER_ADDRESS_CANNOT_BE_BLANK)
	@NotBlank(message = USER_ADDRESS_CANNOT_BE_NULL)
	private String address;
	@NotBlank(message = USER_TITLE_CANNOT_BE_BLANK)
	@NotBlank(message = USER_TITLE_CANNOT_BE_NULL)
	private String title;
	@NotBlank(message = PERSON_NAME_CANNOT_BE_BLANK)
	@NotBlank(message = PERSON_NAME_CANNOT_BE_NULL)
	private String name;
	@Email(message = INVALID_EMAIL_ADDRESS)
	private String email;
	@Positive(message = INVALID_PHONE_NUMBER)
	private Long phoneNumber;
	@NotBlank(message = COUNTRY_CANNOT_BE_BLANK)
	@NotBlank(message = COUNTRY_CANNOT_BE_NULL)
	private String country;
	private List<String> handlingAirlines;
	@NotBlank(message = LOCATION_CANNOT_BE_BLANK)
	@NotBlank(message = LOCATION_CANNOT_BE_NULL)
	private String location;
	private String status;
	private String note;
	@NotBlank(message = USER_TYPE_CANNOT_BE_BLANK)
	@NotBlank(message = USER_TYPE_CANNOT_BE_NULL)
	private String userType;
	private String logo;
	private Boolean isDeleted;

}
