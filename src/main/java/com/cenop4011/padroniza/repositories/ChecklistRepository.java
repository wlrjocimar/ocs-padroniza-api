package com.cenop4011.padroniza.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.models.Tag;

public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {

	
	List<Checklist> findByTagsIn(List<Tag> tags);

}
