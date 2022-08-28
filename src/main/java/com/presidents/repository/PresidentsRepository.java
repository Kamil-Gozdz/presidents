package com.presidents.repository;


import com.presidents.model.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //mozna oznaczać, nie trzeba
public interface PresidentsRepository extends JpaRepository<President,Long> {


}
