package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.NotaTecnicaAlvoDTO;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_nota_tecnica_alvo")
public class NotaTecnicaAlvo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "item")
	private Integer item;
	@Column(name = "sub_item")
	private String subItem;
	@Column(name="texto")
	private String texto;
	@Column(name = "deletable")
	private Boolean deletable;
	
	
	
	public NotaTecnicaAlvo() {
		// TODO Auto-generated constructor stub
	}
	

	public NotaTecnicaAlvo(NotaTecnicaAlvoDTO notaTecnica) {
		this.item = notaTecnica.getItem();
		this.subItem=notaTecnica.getSubItem();
		this.texto = notaTecnica.getTexto();
		this.deletable=notaTecnica.getDeletable();
	}
	
	
	public NotaTecnicaAlvo(NotaTecnicaAlvo notaTecnica) {  // para gravação de historico
		this.item = notaTecnica.getItem();
		this.subItem=notaTecnica.getSubItem();
		this.texto = notaTecnica.getTexto();
		this.deletable=notaTecnica.getDeletable();
	}
	

}
