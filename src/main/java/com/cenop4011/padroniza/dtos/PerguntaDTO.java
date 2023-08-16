package com.cenop4011.padroniza.dtos;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.cenop4011.padroniza.models.CodigoLinha;
import com.cenop4011.padroniza.models.Pergunta;

import lombok.Data;

@Data
public class PerguntaDTO implements Serializable{
	
 	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Integer versao;
	private String ajuda;
	private String observacao;
	private Integer tempoAlerta;
	private Integer instrucaoIn;
	private String link;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPerguntaList tipoResposta;
	
	
	private List<Integer> listaCodigosLinha = new ArrayList<>();
	private List<RespostaDTO> respostas = new ArrayList<>();
	
	
	
	public PerguntaDTO(Pergunta pergunta) {
		super();
		this.id = pergunta.getId();
		this.descricao = pergunta.getDescricao();
		this.versao = pergunta.getVersao();
		this.ajuda = pergunta.getAjuda();
		this.observacao = pergunta.getObservacao();
		this.tempoAlerta = pergunta.getTempoAlerta();
		this.instrucaoIn = pergunta.getInstrucaoIn();
		this.tipoResposta = pergunta.getTipoResposta();
		this.link = pergunta.getLink();
		pergunta.getListaCodigosLinha().stream().map(CodigoLinha::getCodigoLinha) // Mapeia cada objeto para seu atributo "codigo"
			    .forEach(listaCodigosLinha::add);
	}


	public PerguntaDTO() {
		super();
		
	}
	
	
	
	
	
	
	

}
