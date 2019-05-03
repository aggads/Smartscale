package com.example.smartscale.repository;

import com.example.smartscale.entities.Clients;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClientsRepository extends JpaRepository<Clients, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO clients (name) SELECT (:name) WHERE NOT EXISTS ( SELECT (name) FROM clients WHERE name = :name )", nativeQuery = true )
    int saveClientIfNotExist(@Param("name") String name);

    @Query(value = "SELECT id, name FROM clients WHERE name = :name", nativeQuery = true)
    Clients findByName(@Param("name") String name);
}
