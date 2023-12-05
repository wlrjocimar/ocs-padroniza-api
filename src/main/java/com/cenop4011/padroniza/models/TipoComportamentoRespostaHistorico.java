package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_tip_comportamento_resposta_historico")
@Data
public class TipoComportamentoRespostaHistorico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_tipo_comportamento")
	private Integer codigoTipoComportamento;
	@Column(name = "nome_tipo_comportamento")
	private String nomeTipoComportamento;
	
	
	
	
	public TipoComportamentoRespostaHistorico() {
		// TODO Auto-generated constructor stub
	}
	
	
	public TipoComportamentoRespostaHistorico(ComportamentoResposta comportamentoResposta) {
		this.codigoTipoComportamento=comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento();
		
	}
}
