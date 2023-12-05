package com.cenop4011.padroniza.models;

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
@Table(name="tb_codigo_linha_historico")
@Data
public class CodigoLinhaHistorico {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="codigo_linha")
	private Integer codigoLinha;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pergunta_id",referencedColumnName = "id")
	private PerguntaHistorico pergunta;

	
	

	public CodigoLinhaHistorico(CodigoLinha codigoLinha,PerguntaHistorico perguntaHistorico) {
		super();
		this.codigoLinha = codigoLinha.getCodigoLinha();
		this.pergunta=perguntaHistorico;
		
	}




	public CodigoLinhaHistorico() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
