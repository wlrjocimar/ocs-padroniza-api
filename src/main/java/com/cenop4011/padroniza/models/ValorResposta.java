package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.RespostaDTO;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_valor_resposta")
public class ValorResposta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="descricao")
	private String Descricao;
	@Column(name="ativa")
	private Boolean ativa= true;
	

	public ValorResposta(RespostaDTO respostaDTO) {
		this.id=respostaDTO.getCodigoResposta();
	}
	
	public ValorResposta(Resposta resposta) {
		this.id=resposta.getValorResposta().getId();
	}
	
	public ValorResposta() {
		// TODO Auto-generated constructor stub
	}
	

}
