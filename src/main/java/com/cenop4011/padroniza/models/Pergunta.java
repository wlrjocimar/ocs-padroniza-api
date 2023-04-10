package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_pergunta")
@Data
public class Pergunta implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="descricao")
	private String descricao;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name="versao")
	private Integer versao;
	private String ajuda;
	private String observacao;
	@Column(name="tempo_alerta")
	private Integer tempoAlerta;
	@Column(name="ref_in")// instrução normativa de referencia
	private Integer instrucaoIn; /// relacionar com uma lista de instruções que vinculam à pergunta
	@Column(name="link")
	private String link;
	
	
	
	
	
	public Pergunta() {
		super();
	}
	
	
	
	public Pergunta(PerguntaDTO perguntaDTO) {
		super();
	
		this.descricao = perguntaDTO.getDescricao();
		this.createdAt = new Date();
		
		this.versao = perguntaDTO.getVersao();
		this.ajuda = perguntaDTO.getAjuda();
		this.observacao = perguntaDTO.getObservacao();
		this.tempoAlerta = perguntaDTO.getTempoAlerta();
		this.instrucaoIn = perguntaDTO.getInstrucaoIn();
		this.tipoResposta = perguntaDTO.getTipoResposta();
		this.listaCodigosLinha = adicionarCodigosLinha(perguntaDTO);
		
		
	}

	private List<CodigoLinha> adicionarCodigosLinha(PerguntaDTO perguntaDTO) {
		
		this.setListaCodigosLinha(new ArrayList<>());
		for (Integer  cod : perguntaDTO.getListaCodigosLinha()) {
			CodigoLinha codigoLinha = new CodigoLinha();
			codigoLinha.setCodigoLinha(cod);
			codigoLinha.setPergunta(this);
			
			this.listaCodigosLinha.add(codigoLinha);
			
		}
		
		
		return this.listaCodigosLinha;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_resposta")
	private TipoPerguntaList tipoResposta;;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "perguntas", cascade = CascadeType.ALL)
    private List<Bloco> blocos = new ArrayList<>();
	
	//@JsonIgnore
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CodigoLinha> listaCodigosLinha = new ArrayList<>();



	public Pergunta atualizaAtributos(@Valid PerguntaDTO perguntaDTO) {
		
		
		if(perguntaDTO.getListaCodigosLinha().size()>0) {
			this.listaCodigosLinha = adicionarCodigosLinha(perguntaDTO);
			setListaCodigosLinha(listaCodigosLinha);
		}
		

		this.descricao = perguntaDTO.getDescricao();
		this.updatedAt = new Date();
		
		this.versao = perguntaDTO.getVersao();
		this.ajuda = perguntaDTO.getAjuda();
		this.observacao = perguntaDTO.getObservacao();
		this.tempoAlerta = perguntaDTO.getTempoAlerta();
		this.instrucaoIn = perguntaDTO.getInstrucaoIn();
		this.tipoResposta = perguntaDTO.getTipoResposta();
		
		
		return this;
		
		
	}






	
	
	
	
	
	
	
	
	
	
	

}
