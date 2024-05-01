package com.cenop4011.padroniza.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.dtos.RespostaDTO;
import com.cenop4011.padroniza.dtos.TipoComportamentoDTO;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.PersonalBadRequest;
import com.cenop4011.padroniza.models.Diligencia;
import com.cenop4011.padroniza.models.TipoComportamentoResposta;
import com.cenop4011.padroniza.models.ValorComportamentoResposta;
import com.cenop4011.padroniza.repositories.OcorrenciaRepository;
import com.cenop4011.padroniza.services.DiligenciaService;

@Component
public class PerguntaDTOValidator implements Validator {
	
	
	@Autowired
	DiligenciaService diligenciaService;
	@Autowired
	OcorrenciaRepository ocorrenciaRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return PerguntaDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PerguntaDTO perguntaDTO = (PerguntaDTO) target;
        
        
        
        
        
       
        
        
        
        
        for (RespostaDTO respostaDTO : perguntaDTO.getRespostas()) {
        	
        	
        	
        	
        	
        	
        	for (ComportamentoRespostaDTO comportamentoRespostaDTO : respostaDTO.getComportamentos()) {
				
				if(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia()!=null) {
					String nomeOcorrencia=ocorrenciaRepository.buscarComplementoDiligenciaNumeroDetalheOcorrencia(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().getCodigoDetalheOcorrencia());
					if(nomeOcorrencia==null ||nomeOcorrencia.equals("") || nomeOcorrencia.equals(null)) {
						throw new PersonalBadRequest("Nome de ocorrencia nao encontrado para o codigo ocorrencia : " + comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().getCodigoDetalheOcorrencia());
					}
					
				}
				
				
				
				if(comportamentoRespostaDTO.getCodigoTipoComportamento()==2) { //se o comportamento é dligenci verificar se tem todos os atributos da dilig
					
					if(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().getCodigoAgrupadorOcorrencia()==null) {
						throw new PersonalBadRequest("Para resposta padronizada  informou que tera um comportamento de diligencia, porém a diligencia agora necessita de gravar o codigo agrupador como (codigoAgrupadorOcorrencia)");
					}
					
				}
				
				
			}
			
        	
        	
        	
        	
        	
        	if(respostaDTO.getCodigoResposta()==null && perguntaDTO.getTipoResposta().equals(TipoPerguntaList.CONDICIONAL)  ) {
        		throw new PersonalBadRequest("Não informou codigo do valor da resposta em uma das respostas , para pergunta com tipo de resposta condicional");
        	}
        	
        	
        	if(respostaDTO.getNumeroResposta()==null && perguntaDTO.getTipoResposta().equals(TipoPerguntaList.NUMERICO)  ) {
        		throw new PersonalBadRequest("Não informou numero de  resposta para pergunta com tipo de resposta NUMERICO");
        	}
        	
        	
        	for (ComportamentoRespostaDTO comportamentoRespostaDTO : respostaDTO.getComportamentos()) {
        		
        		
        		
//        		if(comportamentoRespostaDTO.getCodigoTipoComportamento()!=null && comportamentoRespostaDTO.getCodigoValorComportamento()==null && comportamentoRespostaDTO.getCodigoTipoComportamento()==2) {
//        			throw new PersonalBadRequest("Se informou codigo do comportamento 2, informe também o codigo do valor desse comportamento ");
//        		}
        		
        		
        		
        		Integer codigoValorComportamento = comportamentoRespostaDTO.getCodigoValorComportamento();
        		
        		ValorComportamentoResposta valorComportamentoResposta = new ValorComportamentoResposta(comportamentoRespostaDTO);
        		
        		// Se codigoTipoComportamentoDTO é 2  então procurar a entidade Diligencia  
        		
        		
        		if(comportamentoRespostaDTO.getCodigoTipoComportamento()==2 && comportamentoRespostaDTO.getCodigoValorComportamento()!=null) {
        			Diligencia diligencia =  diligenciaService.buscarDiligencia(codigoValorComportamento);
        		}
        		
        		
        		if(valorComportamentoResposta.equals(null)) {
        			
        			throw new ObjectNotFoundException("Valor do comportamento não encontrado para o codigoValorComportamento : " + codigoValorComportamento);
        		}
        		
        		
              
        		
        		
        		
				
			}
        	
        	
        	
			
		}
        
        

//        if (perguntaDTO.getTipoResposta() == null || 
//                (perguntaDTO.getTipoResposta() != TipoPerguntaList.NUMERICO && 
//                 perguntaDTO.getTipoResposta() != TipoPerguntaList.CONDICIONAL)) {
//                throw new PersonalBadRequest("tipoResposta deve ser 'NUMERICO' ou 'CONDICIONAL' ");
//            }
        
        

//        if(perguntaDTO.getTempoAlerta()!=null &&  perguntaDTO.getTempoAlerta()<1) {
//        	throw new PersonalBadRequest("tempoAlerta deve ser maior que 0 ");
//        }
        
        
        
        
    }


}
