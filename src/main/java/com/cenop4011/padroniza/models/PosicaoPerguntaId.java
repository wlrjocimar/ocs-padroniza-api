package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class PosicaoPerguntaId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Bloco bloco;
	private Pergunta pergunta;
    
    
    
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicaoPerguntaId other = (PosicaoPerguntaId) obj;
		return Objects.equals(bloco, other.bloco) && Objects.equals(pergunta, other.pergunta);
	}
	@Override
	public int hashCode() {
		return Objects.hash(bloco, pergunta);
	}
	public PosicaoPerguntaId(Bloco bloco, Pergunta pergunta) {
		super();
		this.bloco = bloco;
		this.pergunta = pergunta;
	}
	public PosicaoPerguntaId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    


}