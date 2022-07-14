package com.tyss.layover.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Entity(name = "layover_request")
public class LayoverRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer layoverRequestId;
	private String flightNumber;
	private Integer requestedCount;
	private Integer requestedSingleRooms;
	private Integer requestedDoubleRooms;
	private Integer requestedAccessibleRooms;
	private String requestedMeals;
	private LocalDateTime checkIntegerime;
	private LocalDateTime checkOutTime;
	private LocalDateTime nextFlightDepTime;
	private String loc;
	private String passengerList;
	private String specialRequest;
	private Integer acceptedCount;
	private Integer acceptedSingleRooms;
	private Integer acceptedDoubleRooms;
	private Integer acceptedAccessibleRooms;
	private String acceptedMeals;
	private String responseNote;
	private String status;
	private LocalDateTime timestamp;
	private Integer hotelId;
	private Integer airlineId;
	
}
