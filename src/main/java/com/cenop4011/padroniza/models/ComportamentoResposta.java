package com.cenop4011.padroniza.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_comportamento_resposta")
public class ComportamentoResposta {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
	private TipoComportamentoResposta tipoComportamento;
	
	
	@JsonIgnore
	@ManyToOne
	private Resposta resposta;
	
	public ComportamentoResposta() {
		
	}
	
	public ComportamentoResposta(ComportamentoRespostaDTO comportamentoRespostaDTO) {
		this.tipoComportamento = new TipoComportamentoResposta(comportamentoRespostaDTO);
		
	}

	 

}
