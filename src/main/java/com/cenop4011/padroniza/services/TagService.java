package com.cenop4011.padroniza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.InstrucaoNormativaDTO;
import com.cenop4011.padroniza.dtos.TagDTO;
import com.cenop4011.padroniza.models.GrupoTag;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.models.Tag;
import com.cenop4011.padroniza.repositories.GrupoTagRepository;
import com.cenop4011.padroniza.repositories.TagRepository;

@Service
public class TagService {
	
	
	@Autowired
	TagRepository tagRepository;
	
	
	@Autowired
	GrupoTagRepository grupoTagRepository;
	
	
	public Integer createTagBelongInGroup(InstrucaoNormativaDTO instrucaoNormativasDTO) {
		
		
		
		
	    GrupoTag grupoTag = grupoTagRepository.findBynomeGrupoTag("IN");
	    List<Tag> tags = grupoTag.getTags();
	    
	   
	        String nomeNovaTag = instrucaoNormativasDTO.getNumeroIn() + "-" + instrucaoNormativasDTO.getItem();
	        
	        boolean tagJaExiste = false;
	        for (Tag existingTag : tags) {
	            if (existingTag.getNomeTag().equals(nomeNovaTag)) {
	                tagJaExiste = true;
	                return existingTag.getId();
	            }
	        }
	        
	        if (!tagJaExiste) {
	            Tag tag = new Tag();
	            tag.setNomeTag(nomeNovaTag);
	            tag.setGrupoTag(grupoTag);
	            
	           Tag tagSalva = tagRepository.save(tag); // depois de salvar associar a tag a pergunta
	           
	            
	          return tagSalva.getId();
	        }
	    
	    
	    return 0;
	}


}
