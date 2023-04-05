package com.cenop4011.padroniza.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cenop4011.padroniza.dtos.ChecklistDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.models.Linha;
import com.cenop4011.padroniza.repositories.ChecklistRepository;


@Service
public class ChecklistService {
	
	@Autowired
	ChecklistRepository checklistRepository;
	
	@Autowired
	LinhaService linhaService;
	
	
	public Checklist gravarChecklist(ChecklistDTO checklistDTO, Integer codigoLinha) {
		
		Linha linha = linhaService.buscarLinha(codigoLinha);
		
		Checklist checklist = new Checklist(checklistDTO);
		
		checklist.setLinha(linha);
		
		
		checklistRepository.save(checklist);
		return checklist;
		
	}


	public List<Checklist> buscarTodos() {
		
		return checklistRepository.findAll();
		
	}


	public Checklist buscarPorId(Integer idChecklist) {
		Optional<Checklist> checklist = checklistRepository.findById(idChecklist);
		
		return checklist.orElseThrow(()->new ObjectNotFoundException("CheckList não localizado"));
	}
	

}
