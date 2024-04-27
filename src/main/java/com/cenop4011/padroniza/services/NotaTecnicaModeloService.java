package com.cenop4011.padroniza.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.ChildSubItemNotaTecnica;
import com.cenop4011.padroniza.models.ItemNotaTecnica;
import com.cenop4011.padroniza.models.NotaTecnicaModelo;
import com.cenop4011.padroniza.models.SonChildSubItemNotaTecnica;
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
				
				
				for (ChildSubItemNotaTecnica childSubItemNotaTecnica : subItemNotaTecnica.getChildSubItemNotaTecnicas()) {
					childSubItemNotaTecnica.setSubItemNotaTecnica(subItemNotaTecnica);
					
					for (SonChildSubItemNotaTecnica sonChildSubItemNotaTecnica : childSubItemNotaTecnica.getSonChildSubItemNotaTecnicas()) {
						sonChildSubItemNotaTecnica.setChildSubItemNotaTecnica(childSubItemNotaTecnica);
						
					}
					
				}
				
			}
			
		}
		
		
		NotaTecnicaModelo notaTecnicaModeloGravado = notaTecnicaModeloRepository.save(notaTecnicaModelo);
		
		
		return notaTecnicaModeloGravado;
		
	}


	@Transactional("padronizaTransactionManager")   
	public NotaTecnicaModelo buscarNotaTecnica(Integer id) {
	  
		Optional<NotaTecnicaModelo> notaTecnicaModelo = notaTecnicaModeloRepository.findById(id);
		
		NotaTecnicaModelo notaTecnicaModelo2 = notaTecnicaModelo.orElseThrow(()->new ObjectNotFoundException("Modelo não encontrado"));
		
		notaTecnicaModelo2.getItens().size();
		
		for (ItemNotaTecnica itemNotaTecnica : notaTecnicaModelo2.getItens()) {
			
			itemNotaTecnica.getSubItens().size();
			
			for (SubItemNotaTecnica subItemNotaTecnica : itemNotaTecnica.getSubItens()) {
				
				subItemNotaTecnica.getChildSubItemNotaTecnicas().size();
				
				for (ChildSubItemNotaTecnica childSubItemNotaTecnica : subItemNotaTecnica.getChildSubItemNotaTecnicas()) {
					childSubItemNotaTecnica.getSonChildSubItemNotaTecnicas().size();
					
				}
			}
			
		}
		
		return notaTecnicaModelo2;
		
	}


	@Transactional("padronizaTransactionManager")   
	public List<NotaTecnicaModelo> buscarTodosModelosNotaTecnica() {
		
		List<NotaTecnicaModelo> notasTecnicas=  new ArrayList<>();
		notasTecnicas = notaTecnicaModeloRepository.findAll();
		
		for (NotaTecnicaModelo notaTecnicaModelo : notasTecnicas) {
			notaTecnicaModelo.getItens().size();
			
			for (ItemNotaTecnica itemNotaTecnica : notaTecnicaModelo.getItens()) {
				itemNotaTecnica.getSubItens().size();
				
				for (SubItemNotaTecnica subItemNotaTecnica : itemNotaTecnica.getSubItens()) {
					subItemNotaTecnica.getChildSubItemNotaTecnicas().size();
					
					for (ChildSubItemNotaTecnica childSubItemNotaTecnica : subItemNotaTecnica.getChildSubItemNotaTecnicas()) {
						childSubItemNotaTecnica.getSonChildSubItemNotaTecnicas().size();
						
					}
					
				}
				
			}
			
		}
		  
		  
		  return notasTecnicas;
		            
	}


}
