package com.cenop4011.padroniza.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.dtos.RespostaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_resposta_historico")
@Data
public class RespostaHistorico {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="resposta")
	private String resposta;
	@Column(name="ativo")
	private Boolean ativo=true;
	
	
	@JsonIgnore
	@ManyToOne
	private PerguntaHistorico pergunta;
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "resposta", cascade = CascadeType.ALL)
	private List<ComportamentoRespostaHistorico> comportamentos = new ArrayList<>();
	
	public RespostaHistorico() {
		
	}
	

	public RespostaHistorico(RespostaDTO respostaDTO) {
		this.resposta=respostaDTO.getResposta();
		this.adicionarComportamento(respostaDTO);
		
	}
	
	
	
	

	
	private List<ComportamentoRespostaHistorico> adicionarComportamento(RespostaDTO respostaDTO) {
		this.setComportamentos(new ArrayList<>());
		
		for (ComportamentoRespostaDTO comportamentoRespostaDTO : respostaDTO.getComportamentos()) {
			
			ComportamentoRespostaHistorico comportamentoResposta = new ComportamentoRespostaHistorico(comportamentoRespostaDTO);
			comportamentoResposta.setResposta(this);
			
			this.comportamentos.add(comportamentoResposta);
			
		}
		return comportamentos;
	}
	

	
}
