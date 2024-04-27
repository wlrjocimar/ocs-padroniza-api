package com.cenop4011.padroniza.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChecklistDTO {
	
	
	
	private String nomePersonalizado;
	
	private List<TagDTO> tags = new ArrayList<>();

}
