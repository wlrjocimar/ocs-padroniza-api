package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_pergunta_agiliza")
@Data
public class PerguntaAgiliza implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		@Column(name="pergunta_id")
		private Integer perguntaId;
		
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "agiliza_id",referencedColumnName = "id")
		private Agiliza agiliza;
}
