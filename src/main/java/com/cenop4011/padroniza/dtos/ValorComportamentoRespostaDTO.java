package com.cenop4011.padroniza.dtos;

import com.cenop4011.padroniza.models.Agiliza;
import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class ValorComportamentoRespostaDTO {
	

	
	


	private Integer id;
	
	
    private DiligenciaDTO diligencia;
    
    
    private NotaTecnicaAlvoDTO notaTecnica;
    
    
    
    private AgilizaDTO agiliza;
    
    
    
    public ValorComportamentoRespostaDTO(ComportamentoResposta comportamentoResposta) {
    	this.id = comportamentoResposta.getValorComportamentoResposta().getId();
    	
    	if(comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento()==2) {
    		this.diligencia = new DiligenciaDTO(comportamentoResposta.getValorComportamentoResposta().getDiligencia());
    	}
		
		
	}



	public ValorComportamentoRespostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    

}
