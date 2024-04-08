package com.cenop4011.padroniza.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cenop4011.padroniza.dtos.LinhaDTO;
import com.cenop4011.padroniza.models.Linha;
import com.cenop4011.padroniza.services.LinhaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(tags = "Linhas",description = " ")
@RestController
@CrossOrigin(allowCredentials = "true",originPatterns = "*")
@RequestMapping("/linhas")
public class LinhaController {
	
	
	@Autowired
	LinhaService linhaService;
	
	

	@PostMapping
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<LinhaDTO> gravarLinha( @RequestBody @Validated LinhaDTO linhaDTO, HttpServletRequest req){
		

		Linha linha = linhaService.gravarLinha(linhaDTO);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(linha.getId())
                .toUri();

       return ResponseEntity.created(location).build();
		
		
		
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<LinhaDTO>> buscarTodasLinhas(){
		
		List<Linha> linhas = linhaService.buscarTodas();
		
		List<LinhaDTO> linhasDTO = linhas.stream().map(linha -> new LinhaDTO(linha)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(linhasDTO);
	}
	
	@GetMapping("/{codigoLinha}")
	public ResponseEntity<?> buscarlinha(@PathVariable Integer codigoLinha){
		
		Linha linha = linhaService.buscarLinhaLazyLoad(codigoLinha);
		
		
		
		return ResponseEntity.ok().body(linha);
	}
	
	

}
