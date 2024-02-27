package com.cenop4011.padroniza.dtos;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;

import lombok.Data;


// Esta classe tem o objetivo de modelar a entrada de dados referentes a uma pergunta somenta para atualização de alguns atributosa


@Data
public class PerguntaInputDTO {

	
	private String descricao;
	private Integer versao;
	private String ajuda;
	private String observacao;
    private Integer tempoAlerta;
	private String instrucaoIn;
	private String link;
	private Boolean ativo;
	private Boolean automatizavel;
	
	
	@NotNull(message = "tipo de resposta deve ser 'NUMERICO' ou 'CONDICIONAL' ")
	@Enumerated(EnumType.STRING)
	private TipoPerguntaList tipoResposta;
	
	private List<Integer> listaCodigosLinha = new ArrayList<>();
	
	private List<RespostaDTO> respostas = new ArrayList<>();
	
	
}
