package com.cenop4011.padroniza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.models.GrupoTag;
import com.cenop4011.padroniza.services.GrupoTagService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin("*")
@RequestMapping("grupostag")
@Api(tags = "Gestão de Tags",description = " ")
public class GrupoTagController {
	
	@Autowired
	GrupoTagService grupoTagService;
	
	@GetMapping
	public ResponseEntity<?> todasTags(){
		
		List<GrupoTag> gruposTag =  grupoTagService.buscarGruposTag();
		
		return ResponseEntity.ok().body(gruposTag);
		
		
	}

}
