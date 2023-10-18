package com.example.log4j2sample.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_account")
public class AccountDetails implements Serializable{

	@Id
	@Column(name="account_id")
	private Long accountId;

	@Column(name="account_name")
	private String accountName;

	@Column(name="account_type")
	private String accountType;
	
	@Column(name="open_date")
	private LocalDate opendDate;

	@Column(name="account_status")
	private String accountStatus;

	@Column(name="holder_name")
	private String holderName;

	@Column(name="nic")
	private String nic;

	@Column(name="account_number")
	private String accountNumber;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@Column(name = "created_by",updatable = false)
	@CreatedBy
	private String createdBy;

	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

}
