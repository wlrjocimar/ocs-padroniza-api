package com.cenop4011.padroniza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenop4011.padroniza.models.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {

}
