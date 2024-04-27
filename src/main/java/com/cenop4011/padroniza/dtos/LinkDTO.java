package com.cenop4011.padroniza.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;




@Data
public class LinkDTO {
	
	
	
	
	
	private String nomeLink;
	
	private String url;
	
	private Boolean isFile;
	
	private String ajuda;

}
