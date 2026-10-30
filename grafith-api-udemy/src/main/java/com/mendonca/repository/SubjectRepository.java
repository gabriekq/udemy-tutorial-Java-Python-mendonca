package com.mendonca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendonca.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>  {

}
