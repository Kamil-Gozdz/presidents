package com.presidents.repository;


import com.presidents.model.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository //mozna oznaczać, nie trzeba
public interface PresidentsRepository extends JpaRepository<President,Long> {

   Set<President> findPresidentsByName(@Param("name") String name);
  @Query("SELECT p FROM President p where p.politicalParty = :party")
   Set<President> findPresidentsByPoliticalParty(@Param("party") String party);

}
