package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_resposta")
@Data
public class Resposta implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "codigo_pergunta")
	private Integer codigoPergunta;
	@Column(name = "resposta")
	private String valorResposta;
	@Column(name="texto_orig_pergunta")
	private String textoOriginalPergunta;
	
	
	
	@ManyToOne
	@JoinColumn(name="estudo_id", referencedColumnName = "id")
    private Estudo estudo;
	

}
