package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
		this.tipo = perguntaDTO.getTipo();
		
		
		
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPerguntaList tipo;
	
	@ManyToMany(mappedBy = "perguntas")
    private List<Bloco> blocos = new ArrayList<>();



	public void adicionarBlocos(Bloco bloco) {
		bloco.getPerguntas().add(this);
		blocos.add(bloco);
		
	}
	
	
	
	
	
	
	
	
	
	

}
