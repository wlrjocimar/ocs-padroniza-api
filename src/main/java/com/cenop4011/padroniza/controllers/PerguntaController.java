package com.cenop4011.padroniza.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.exceptions.ViolacaoIntegridadeException;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.services.PerguntaService;
import com.cenop4011.padroniza.validators.PerguntaDTOValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@Api(tags = "Perguntas",description = " ")
@RequestMapping("/perguntas")
@CrossOrigin("*")
public class PerguntaController {
	
	
	@Autowired
	PerguntaService perguntaService;
	
	 @Autowired
	    private PerguntaDTOValidator perguntaDTOValidator;

	    @InitBinder
	    protected void initBinder(WebDataBinder binder) {
	        // Registre o seu Validator aqui
	        if (binder.getTarget() instanceof PerguntaDTO) {
	            binder.addValidators(perguntaDTOValidator);
	        }
	    }
	
	
	@GetMapping("/teste")
	 public ResponseEntity<?> teste(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
		String BBSSOToken = "";

		// Verifica se existem cookies na requisição
		if (cookies != null) {
		    // Itera sobre o array de cookies
		    for (Cookie cookie : cookies) {
		        // Obtém o nome e o valor do cookie
		        String name = cookie.getName();
		        if (name.equals("BBSSOToken")) {
		        	BBSSOToken = cookie.getValue();
		        }
		        String value = cookie.getValue();
		        

		        // Faz algo com o nome e o valor do cookie
		        System.out.println(name + " = " + value);
		    }
		}
		
		System.out.println("Local addrres:" + req.getLocalAddr());
		System.out.println("Remote addres addrres:" + req.getRemoteAddr());
		System.out.println("Remote Host:" + req.getRemoteHost());
		
		return ResponseEntity.ok().body("Local addrres:" + req.getLocalAddr() + " Remote addres addrres:" + req.getRemoteAddr() +  " Remote Host:" + req.getRemoteHost() + " Meu BBSSOToken  : " + BBSSOToken);
		
	}
	
	
	
	@PostMapping
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Pergunta> gravarPergunta(@RequestParam(value="bloco", defaultValue = "0")  Integer idBloco , @Valid   @RequestBody  PerguntaDTO perguntaDTO, HttpServletRequest req){
		

		Pergunta pergunta = perguntaService.gravarPergunta(perguntaDTO, idBloco);
		Pergunta perguntaGravada = perguntaService.buscarPergunta(pergunta.getId());
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pergunta.getId())
                .toUri();

       return ResponseEntity.status(HttpStatus.CREATED).location(location).body(perguntaGravada);
		
		
		
		
	}
	
	
	
	@GetMapping("/{idPergunta}")
	public ResponseEntity<Pergunta> buscarPergunta(@PathVariable Integer idPergunta){
		
			Pergunta pergunta = perguntaService.buscarPergunta(idPergunta);
			return ResponseEntity.ok().body(pergunta);
	}
	
	
	
	@PutMapping("/{idPergunta}")
	
	
	 @ApiImplicitParams({
       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	
	public ResponseEntity<Pergunta> atualizarPergunta(@PathVariable Integer idPergunta, @RequestBody @Valid PerguntaDTO perguntaDTO){
		
		
		Pergunta pergunta = perguntaService.atualizarPergunta(idPergunta, perguntaDTO);
		
		
		return ResponseEntity.ok().body(pergunta);
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<PerguntaDTO>> buscarTodasPerguntas(){
		
			List<Pergunta> perguntas = perguntaService.buscarTodas();
            List<PerguntaDTO> perguntasDTO = perguntas.stream().map(pergunta -> new PerguntaDTO(pergunta)).collect(Collectors.toList());
			return ResponseEntity.ok().body(perguntasDTO);
		
	}
	
	

	@DeleteMapping("/desvincula/{idPergunta}")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> desvincularPergunta(@PathVariable Integer idPergunta,@RequestParam(value="bloco", defaultValue = "0")  Integer idBloco){
		
		
		
			perguntaService.removerPergunta(idPergunta,idBloco);

			 return ResponseEntity.noContent().build();
       
		
		
		
		
	}
	
	
	@PostMapping("/vincularbloco/{idPergunta}/{idBloco}/{posicao}") // o parametro posição ira definir a posição da pergunta em relação ao bloco
	 @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> vincularBloco(@PathVariable Integer idPergunta, @PathVariable Integer idBloco, @PathVariable Integer posicao ){
		
		Pergunta pergunta = perguntaService.vincularBloco(idPergunta,idBloco,posicao);
		
		
		return ResponseEntity.ok().body(pergunta);
		
	}
	
	
	
	
	
	@DeleteMapping("/{idPergunta}")
	 @ApiImplicitParams({
       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> deletarPergunta(@PathVariable Integer idPergunta){
		
		
		try {
			perguntaService.deletarPergunta(idPergunta);

			 return ResponseEntity.noContent().build();
			
		} catch (DataIntegrityViolationException e) {
		    throw new ViolacaoIntegridadeException("A pergunta pode estar sendo utilizada em algum bloco já, neste caso somente poderá excluir se desvincular antes");
		}
			
      
		
		
		
		
	}

}
