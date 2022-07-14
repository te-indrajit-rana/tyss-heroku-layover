package com.tyss.layover.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity(name = "hotel_details")
public class HotelDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hotelDetailsId;
	private Integer singleRooms;
	private Integer doubleRooms;
	private Integer accessibleRooms;
	private BigDecimal singleRoomPrice;
	private BigDecimal doubleRoomPrice;
	private BigDecimal accessibleRoomPrice;
	private BigDecimal breakfastPrice;
	private BigDecimal lunchPrice;
	private BigDecimal beveragePrice;
	private Long hotelContactNumber;
	private Integer hotelStar;
	private String description;
	private Integer hotelId;
 
}
