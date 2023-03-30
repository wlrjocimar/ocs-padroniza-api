package com.cenop4011.security.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name="tb_users")
public class User {
	
	
		@Id
		@GeneratedValue(generator = "UUID")
		@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
		private String id;
		@NotNull
		@Column(unique = true)
		private String userName;
		@NotNull
		private  String password;
		//ponto de atenção caso não funcione
		@ManyToMany(fetch = FetchType.EAGER)
		private  Collection<Role> roles = new ArrayList<>();
		
		
		public User() {
			// TODO Auto-generated constructor stub
		}


		public User( @NotNull String userName, @NotNull String password,
				Collection<Role> roles) {
			
			this.userName = userName;
			this.password = password;
			this.roles = roles;
		}


		

		
}
