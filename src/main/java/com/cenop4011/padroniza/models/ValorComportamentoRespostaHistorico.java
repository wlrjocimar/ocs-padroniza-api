package com.cenop4011.padroniza.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "diligencia_id", referencedColumnName = "id")
    private Diligencia diligencia;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "nota_tecnica_alvo_id", referencedColumnName = "id")
    private NotaTecnicaAlvo  notaTecnica;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "agiliza_id", referencedColumnName = "id")
    private Agiliza  agiliza;
	
	

	
	public ValorComportamentoRespostaHistorico() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ValorComportamentoRespostaHistorico(ComportamentoResposta comportamentoResposta) {
		
		
	if(comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento()==1) {
			
			this.notaTecnica =  new NotaTecnicaAlvo(comportamentoResposta.getValorComportamentoResposta().getNotaTecnica());
		}else if(comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento()==3) {
			this.agiliza = comportamentoResposta.getValorComportamentoResposta().getAgiliza();
		
		}else if(comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento()==2){
			this.diligencia = comportamentoResposta.getValorComportamentoResposta().getDiligencia();
		}
		

		
	}


}
