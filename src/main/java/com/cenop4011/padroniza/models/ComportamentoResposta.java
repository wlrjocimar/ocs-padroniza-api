package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_comportamento_resposta")
public class ComportamentoResposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_comportamento")
	private TipoPerguntaList tipoComportamento;
	 

}
