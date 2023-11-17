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

@IdClass(PosicaoBlocoId.class)

@Table(name = "tb_posicao_bloco")
public class PosicaoBloco {
	
	
	


	@JsonIgnore
    @ManyToOne
    @Id
    @JoinColumn(name = "bloco_id")
    private Bloco bloco;

	@JsonIgnore
    @ManyToOne
    @Id
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

    private int posicao;
    
    @javax.persistence.Transient
    private Integer idChecklist;

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public Integer getIdChecklist() {
		return this.checklist.getId();
	}

	public void setIdChecklist(Integer idChecklist) {
		this.idChecklist = idChecklist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloco, checklist, idChecklist, posicao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicaoBloco other = (PosicaoBloco) obj;
		return Objects.equals(bloco, other.bloco) && Objects.equals(checklist, other.checklist)
				&& Objects.equals(idChecklist, other.idChecklist) && posicao == other.posicao;
	}
    
    
    
    

}
