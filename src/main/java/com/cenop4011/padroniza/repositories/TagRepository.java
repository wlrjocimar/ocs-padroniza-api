package com.cenop4011.padroniza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenop4011.padroniza.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
