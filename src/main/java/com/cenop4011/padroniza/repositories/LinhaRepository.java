package com.cenop4011.padroniza.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenop4011.padroniza.models.Linha;

public interface LinhaRepository extends JpaRepository<Linha, Integer> {

	Optional<Linha> findByCodigoLinha(Integer codigoLinha);

	

}
