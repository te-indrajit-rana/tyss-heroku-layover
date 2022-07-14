package com.tyss.layover.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageId;
	private String imageName;
	private String imagePath;
	@ManyToOne
	@JoinColumn(name = "hotel_details_id")
	private HotelDetails hotelDetails;
}
