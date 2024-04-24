package com.cenop4011.padroniza.dtos;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
	@NotNull(message = "descrição não pode ser nulo")
	@NotBlank(message="informe a descição")
	private String descricao;
	private Integer versao;
	private String ajuda;
	private String observacao;
    private Integer tempoAlerta;
	private String link;
	private Integer posicao;   // atributo somente de retorno para mostrar model pergunta quando dentro de um bloco qual a posição que esta pegunta assume
	private Boolean automatizavel;
	
	
	
	

	public PerguntaDTO()  {

		super();
		
		
			
		
		
	}
	
	
	
	
	
	@NotNull(message = "tipo de resposta deve ser 'NUMERICO' ou 'CONDICIONAL' ")
	@Enumerated(EnumType.STRING)
	private TipoPerguntaList tipoResposta;
	
	
	private List<Integer> listaCodigosLinha = new ArrayList<>();
	
	@NotNull(message = "Informe as possives respostas para gravar uma pergunta")
	@NotEmpty(message = "Informe as possives respostas para gravar uma pergunta")
	private List<RespostaDTO> respostas = new ArrayList<>();
	
	private List<LinkDTO> links = new ArrayList<>();
	
	private List<InstrucaoNormativaDTO> instrucoesNormativas = new ArrayList<>();
	private List<TagDTO> tags = new ArrayList<>();
	
	
	
	public PerguntaDTO(Pergunta pergunta) {
		super();
		
		
		
		
		this.id = pergunta.getId();
		this.descricao = pergunta.getDescricao();
		this.versao = pergunta.getVersao();
		this.ajuda = pergunta.getAjuda();
		this.observacao = pergunta.getObservacao();
		this.tempoAlerta = pergunta.getTempoAlerta();
		this.tipoResposta = pergunta.getTipoResposta();
		this.link = pergunta.getLink();
		pergunta.getListaCodigosLinha().stream().map(CodigoLinha::getCodigoLinha) // Mapeia cada objeto para seu atributo "codigo"
			    .forEach(listaCodigosLinha::add);
		//respostas= pergunta.getRespostas().stream().map((resp)-> new RespostaDTO(resp)).collect(Collectors.toList());
		
		this.automatizavel = pergunta.getAutomatizavel();
		
	}


	
	
	
	
	
	

}
