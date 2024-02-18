package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Experience;

public interface ExperienceRepo extends CrudRepository<Experience,Long> ,JpaRepository<Experience,Long>{

}
