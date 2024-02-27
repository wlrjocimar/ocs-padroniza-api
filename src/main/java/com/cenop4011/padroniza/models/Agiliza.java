package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cenop4011.padroniza.dtos.AgilizaDTO;

import lombok.Data;

@Entity
@Table(name = "tb_agiliza")
@Data
public class Agiliza implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "checklist_id")
	private Integer checklistId;
	
	

	
	
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "agiliza",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BlocoAgiliza> blocosIds = new ArrayList<>();
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "agiliza",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PerguntaAgiliza> perguntasIds = new ArrayList<>();
	
	
	
	
	public Agiliza(AgilizaDTO agilizaDTO) {
		this.checklistId=agilizaDTO.getChecklistId();
		this.perguntasIds = adicionarPerguntasIds(agilizaDTO.getPerguntasIds());
		this.blocosIds=adicionarBlocosIds(agilizaDTO.getBlocosIds());
	}

public Agiliza() {
	// TODO Auto-generated constructor stub
}


	private List<BlocoAgiliza> adicionarBlocosIds(List<Integer> blocosIds2) {

		for (Integer i : blocosIds2) {
			BlocoAgiliza blocoAgiliza = new BlocoAgiliza();
			blocoAgiliza.setBlocoId(i);
			blocoAgiliza.setAgiliza(this);
			blocosIds.add(blocoAgiliza);
			
			
		}
		
		return this.blocosIds;
	}




	private List<PerguntaAgiliza> adicionarPerguntasIds(List<Integer> perguntasIds2) {
		for (Integer i : perguntasIds2) {
			PerguntaAgiliza perguntaAgiliza = new PerguntaAgiliza();
			perguntaAgiliza.setPerguntaId(i);;
			perguntaAgiliza.setAgiliza(this);
			perguntasIds.add(perguntaAgiliza);
			
			
		}
		
		return this.perguntasIds;
	}
	
	
	
	
	
	

}
