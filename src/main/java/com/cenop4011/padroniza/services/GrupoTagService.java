package com.cenop4011.padroniza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.models.GrupoTag;
import com.cenop4011.padroniza.repositories.GrupoTagRepository;

@Service
public class GrupoTagService {

	
	@Autowired
	GrupoTagRepository grupoTagRepository;
	
	
	
	public List<GrupoTag> buscarGruposTag(){
		
		return grupoTagRepository.findAll();
		
	}
	
}
