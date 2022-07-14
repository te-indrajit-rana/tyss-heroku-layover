package com.tyss.layover.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity(name="commission")
public class Commission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commissionId;
    private BigDecimal commissionAmount;
    private String invoice;
    private String status;
    
    @OneToOne(optional=false)
    @JoinColumn(name="payment_id", nullable=false)
    private Payment payment;

}
