package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class PosicaoBlocoId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Checklist checklist;
	private Bloco bloco;
	
    
	public PosicaoBlocoId(Checklist checklist, Bloco bloco) {
		super();
		this.bloco = bloco;
		this.checklist = checklist;
	}
	public PosicaoBlocoId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
