package com.cenop4011.padroniza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenop4011.padroniza.models.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {

}
