package com.example.log4j2sample.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_account")
public class AccountDetails implements Serializable{

	@Id
	@Column(name="account_id")
	protected Long accountId;
	
	@Column(name="version")
	protected Long version;


	
}
