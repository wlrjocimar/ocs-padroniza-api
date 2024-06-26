package com.cenop4011.padroniza.dtos;

import javax.persistence.Column;

import lombok.Data;

@Data
public class NotaTecnicaAlvoDTO {
	
	
	private Integer item;
	
	private String subItem;
	
	private String childSubItem;
	
	private String sonChildSubItem;
	
	private String texto;
	
	private String ajuda;
	
	private Boolean deletable;

}
