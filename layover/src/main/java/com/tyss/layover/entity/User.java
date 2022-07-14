package com.tyss.layover.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tyss.layover.converter.StringListConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String address;
	private String title;
	private String name;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "phone_number", nullable = false, unique = true)
	private Long phoneNumber;
	private String country;
	@Convert(converter = StringListConverter.class)
	private List<String> handlingAirlines;
	private String location;
	private String status;
	private String note;
	private String userType;
	private String logo;
	private Boolean isDeleted;
	

}
