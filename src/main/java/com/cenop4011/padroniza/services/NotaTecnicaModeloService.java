package com.cenop4011.padroniza.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.ItemNotaTecnica;
import com.cenop4011.padroniza.models.NotaTecnicaModelo;
import com.cenop4011.padroniza.models.SubItemNotaTecnica;
import com.cenop4011.padroniza.repositories.NotaTecnicaModeloRepository;

@Service
public class NotaTecnicaModeloService {
	
	
	
	@Autowired
	NotaTecnicaModeloRepository notaTecnicaModeloRepository;
	
	
	
	public NotaTecnicaModelo gravarNotaTecnicaModelo(NotaTecnicaModelo notaTecnicaModelo) {
		
		notaTecnicaModelo.setId(null);
		
		for (ItemNotaTecnica itemNotaTecnica : notaTecnicaModelo.getItens()) {
			
			itemNotaTecnica.setNotaTecnicaModelo(notaTecnicaModelo);
			
			for (SubItemNotaTecnica subItemNotaTecnica : itemNotaTecnica.getSubItens()) {
				
				subItemNotaTecnica.setItemNotaTecnica(itemNotaTecnica);
				
			}
			
		}
		
		
		NotaTecnicaModelo notaTecnicaModeloGravado = notaTecnicaModeloRepository.save(notaTecnicaModelo);
		
		
		return notaTecnicaModeloGravado;
		
	}


	@Transactional("padronizaTransactionManager")   
	public Optional<NotaTecnicaModelo> buscarNotaTecnica(Integer id) {
	    return notaTecnicaModeloRepository.findById(id)
	            .map(nt -> {
	                nt.getItens().forEach(item -> item.getSubItens().size()); // Força a inicialização da lista subItens para cada item por ter um carregamento lazy que é o indicado
	                return nt;
	            });
	}


}
