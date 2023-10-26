package com.cenop4011.padroniza.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.PersonalBadRequest;

@Component
public class PerguntaDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PerguntaDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PerguntaDTO perguntaDTO = (PerguntaDTO) target;

//        if (perguntaDTO.getTipoResposta() == null || 
//                (perguntaDTO.getTipoResposta() != TipoPerguntaList.NUMERICO && 
//                 perguntaDTO.getTipoResposta() != TipoPerguntaList.CONDICIONAL)) {
//                throw new PersonalBadRequest("tipoResposta deve ser 'NUMERICO' ou 'CONDICIONAL' ");
//            }
        
        

        if(perguntaDTO.getTempoAlerta()!=null &&  perguntaDTO.getTempoAlerta()<1) {
        	throw new PersonalBadRequest("tempoAlerta deve ser maior que 0 ");
        }
        
    }


}
