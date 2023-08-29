package com.cenop4011.padroniza.dtos;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.cenop4011.padroniza.models.CodigoLinha;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.models.Resposta;

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
	private String instrucaoIn;
	private String link;
	private Integer posicao;   // atributo somente de retorno para mostrar model pergunta quando dentro de um bloco qual a posição que esta pegunta assume
	
	
	
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
		respostas= pergunta.getRespostas().stream().map((resp)-> new RespostaDTO(resp)).collect(Collectors.toList());
		
	}


	public PerguntaDTO() {
		super();
		
	}
	
	
	
	
	
	
	

}
