package com.cenop4011.padroniza.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AgilizaDTO {
	
	
	
	
	private Integer checklistId;
	
	
	private List<Integer> blocosIds = new ArrayList<>();
	
	private List<Integer> perguntasIds = new ArrayList<>();
	

}
