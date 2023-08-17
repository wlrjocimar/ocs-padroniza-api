package com.cenop4011.padroniza.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.cenop4011.padroniza.models.Resposta;

import lombok.Data;

@Data
public class RespostaDTO {
	
	
	
	private String resposta;
	
	
	
	
	
	private List<ComportamentoRespostaDTO> comportamentos = new ArrayList<>();


public RespostaDTO() {
	// TODO Auto-generated constructor stub
}


	public RespostaDTO(Resposta resp) {
		this.resposta= resp.getResposta();
		
		
	}
	
	
	
	
	

}
