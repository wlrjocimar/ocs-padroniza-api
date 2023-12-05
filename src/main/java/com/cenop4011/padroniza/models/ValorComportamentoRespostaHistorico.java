package com.cenop4011.padroniza.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_valor_comportamento_historico")
@Data
public class ValorComportamentoRespostaHistorico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "diligencia_id", referencedColumnName = "id")
    private Diligencia diligencia;
	

	
	public ValorComportamentoRespostaHistorico() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ValorComportamentoRespostaHistorico(ComportamentoResposta comportamentoResposta) {
		this.diligencia = new Diligencia(comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento()); // aqui é o codigo que é omesmo id da dligencia
		
	}


}
