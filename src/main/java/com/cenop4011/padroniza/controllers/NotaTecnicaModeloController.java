package com.cenop4011.padroniza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.ItemNotaTecnica;
import com.cenop4011.padroniza.models.NotaTecnicaModelo;
import com.cenop4011.padroniza.services.NotaTecnicaModeloService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("notas-tecnicas-modelos")
@CrossOrigin("*")
@Api(tags = "Modelos de nota Técnica",description = " ")
public class NotaTecnicaModeloController {
	
	
	@Autowired
	NotaTecnicaModeloService notaTecnicaModeloService;
	
	
	
	@PostMapping
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<NotaTecnicaModelo> gravarModeloNotaTecnica(@RequestBody NotaTecnicaModelo notaTecnicaModelo){
		
		NotaTecnicaModelo notaTecnicaModeloGravado = notaTecnicaModeloService.gravarNotaTecnicaModelo(notaTecnicaModelo);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(notaTecnicaModeloGravado)	;	
	}
	
	
	
	@GetMapping("/{idModelo}")
	public ResponseEntity<NotaTecnicaModelo> buscarModeloNotaTecnica(@PathVariable Integer idModelo ){
		
		NotaTecnicaModelo notaTecnicaModelo = notaTecnicaModeloService.buscarNotaTecnica(idModelo)
		        .orElseThrow(() -> new ObjectNotFoundException("Nota Técnica não encontrada com o ID: " + idModelo));

				
		
		
		
		return ResponseEntity.ok().body(notaTecnicaModelo);
		
	}
		
	
	
	
	

}
