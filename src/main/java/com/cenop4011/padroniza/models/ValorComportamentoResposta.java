package com.cenop4011.padroniza.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.repositories.OcorrenciaRepository;

import lombok.Data;

@Entity
@Table(name="tb_valor_comportamento")
@Data
public class ValorComportamentoResposta {
	
	
	
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
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	public ValorComportamentoResposta(ComportamentoRespostaDTO comportamentoRespostaDTO) {
		
		if(comportamentoRespostaDTO.getCodigoTipoComportamento()==1) {
			
			this.notaTecnica =  new NotaTecnicaAlvo(comportamentoRespostaDTO.getValorComportamentoResposta().getNotaTecnica());
		}else if(comportamentoRespostaDTO.getCodigoTipoComportamento()==3) {
			this.agiliza = new Agiliza(comportamentoRespostaDTO.getValorComportamentoResposta().getAgiliza());
		}else if(comportamentoRespostaDTO.getCodigoValorComportamento()!=null) {
			this.diligencia = new Diligencia(comportamentoRespostaDTO.getCodigoValorComportamento()); // aqui é o codigo que é omesmo id da dligencia
		}else {
			this.diligencia = new Diligencia(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia());
		}
		
		
		
	}






	public ValorComportamentoResposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
