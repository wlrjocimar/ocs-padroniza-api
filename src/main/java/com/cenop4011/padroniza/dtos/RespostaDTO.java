package com.cenop4011.padroniza.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.models.Resposta;

import lombok.Data;

@Data
public class RespostaDTO {
	
	
	private Integer numeroResposta;
	
	
	private Integer codigoResposta;
	
	
	
	
	
private List<ComportamentoRespostaDTO> comportamentos = new ArrayList<>();


public RespostaDTO() {
	// TODO Auto-generated constructor stub
}


	public RespostaDTO(Resposta resp) {
		this.numeroResposta= resp.getNumeroResposta();
		comportamentos= resp.getComportamentos().stream().map((comp)-> new ComportamentoRespostaDTO(comp)).collect(Collectors.toList());
		
		
	}
	
	
	
	
	

}
