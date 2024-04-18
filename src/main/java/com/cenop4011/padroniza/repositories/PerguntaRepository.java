package com.cenop4011.padroniza.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cenop4011.padroniza.models.Pergunta;



public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {

	List<Pergunta> findByAutomatizavel(boolean b);

	
	
	
	@Query(value="SELECT t0.* FROM ocs_padroniza_dev.tb_pergunta t0 inner join ocs_padroniza_dev.tb_bloco_pergunta t1 on t0.id=t1.pergunta_id inner join ocs_padroniza_dev.tb_checklist_bloco t2 on t1.bloco_id=t2.bloco_id  where t0.automatizavel is true and t2.checklist_id = ?1",nativeQuery = true)
	List<Pergunta> findByAutomatizavelPorChecklist(@Param(value = "idChecklist") Integer idChecklist);
	
	

}
