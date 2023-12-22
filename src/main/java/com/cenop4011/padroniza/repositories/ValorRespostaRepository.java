package com.cenop4011.padroniza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenop4011.padroniza.models.ValorResposta;

@Repository
public interface ValorRespostaRepository  extends JpaRepository<ValorResposta, Integer>{

}
