package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


// posicao da pergunta em relação ao bloco

@Entity
@IdClass(PosicaoPerguntaId.class)
@Data
@Table(name = "tb_posicao_pergunta")
public class PosicaoPergunta {
	
	
	

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
	
	

}
