package com.cenop4011.padroniza.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.dtos.RespostaDTO;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;
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
	
	
	@Column(name="ativo")
	private Boolean ativo=true;
	
	@Column(name="valor_numerico_resposta")
	private Integer numeroResposta; // atributo somente para perguntas cujo tipo da  resposta é numerico;
	
	@ManyToOne
	@JoinColumn(name = "valor_resposta_id",referencedColumnName = "id")
	private ValorResposta valorResposta;
	
	
	
	
	
	@JsonIgnore
	@ManyToOne
	private PerguntaHistorico pergunta;
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "resposta", cascade = CascadeType.ALL)
	private List<ComportamentoRespostaHistorico> comportamentos = new ArrayList<>();
	
	public RespostaHistorico() {
		
	}
	

	
	
public RespostaHistorico(Resposta resposta,TipoPerguntaList tipoPergunta) {
		
		if(tipoPergunta.equals(TipoPerguntaList.CONDICIONAL)) {
			this.valorResposta=new ValorResposta(resposta); 
		}
		
		this.numeroResposta=resposta.getNumeroResposta();
		this.adicionarComportamento(resposta);
		
	}
	
	
	
	

	
	private List<ComportamentoRespostaHistorico> adicionarComportamento(Resposta resposta) {
		this.setComportamentos(new ArrayList<>());
		
		for (ComportamentoResposta comportamentoResposta : resposta.getComportamentos()) {
			
			ComportamentoRespostaHistorico comportamentoRespostaHistorico = new ComportamentoRespostaHistorico(comportamentoResposta);
			comportamentoRespostaHistorico.setResposta(this);
			
			this.comportamentos.add(comportamentoRespostaHistorico);
			
		}
		return comportamentos;
	}
	

	
}
