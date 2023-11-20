package com.cenop4011.padroniza.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@IdClass(PosicaoPerguntaId.class)

@Table(name = "tb_posicao_pergunta_historico")
public class PosicaoPerguntaHistorico {


	@JsonIgnore
    @ManyToOne
    @Id
    @JoinColumn(name = "bloco_id")
    private Bloco bloco;

	@JsonIgnore
    @ManyToOne
    @Id
    @JoinColumn(name = "pergunta_id")
    private Pergunta pergunta;

    private int posicao;
    
    @javax.persistence.Transient
    private Integer numeroBloco;

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public Integer getNumeroBloco() {
		return this.bloco.getId();
	}

	public void setNumeroBloco(Integer numeroBloco) {
		this.numeroBloco = numeroBloco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloco, numeroBloco, pergunta, posicao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicaoPerguntaHistorico other = (PosicaoPerguntaHistorico) obj;
		return Objects.equals(bloco, other.bloco) && Objects.equals(numeroBloco, other.numeroBloco)
				&& Objects.equals(pergunta, other.pergunta) && posicao == other.posicao;
	}
    
    
	
	
}
