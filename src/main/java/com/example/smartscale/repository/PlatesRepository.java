package com.example.smartscale.repository;

import com.example.smartscale.entities.Clients;
import com.example.smartscale.entities.Plates;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlatesRepository extends JpaRepository<Plates, Long> {

    //@Query("SELECT p FROM Plates p WHERE p.plate = :plate")
    @Query(value = "SELECT plate, type, quantity, date_order FROM plates WHERE plate = :plate ORDER BY date_order ASC LIMIT 1", nativeQuery = true)
    JSONObject findByName(@Param("plate") String plate);

    @Query(value = "SELECT name, plate FROM clients INNER JOIN plates ON clients.id = plates.client", nativeQuery = true)
    List<JSONObject> findUserWithPlates();

    @Query(value = "SELECT name FROM clients INNER JOIN plates ON clients.id = plates.client WHERE plate = :plate" , nativeQuery = true)
    List<JSONObject> getUsersNameFromPlate(@Param("plate") String plate);

    @Query(value = "SELECT plate FROM plates INNER JOIN clients ON clients.id = plates.client WHERE clients.id = :clientId", nativeQuery = true)
    List<JSONObject> getPlatesFromUserId(@Param("clientId") int clientId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO plates (plate, type, quantity, date_order, client) SELECT :plate, :type, :quantity, :date_order, :client WHERE NOT EXISTS ( SELECT plate, type, quantity, date_order, client FROM plates WHERE plate = :plate AND type = :type AND quantity = :quantity AND date_order = :date_order AND client = :client )", nativeQuery = true )
    int savePlateIfNotExist(@Param("plate") String plate,
                                   @Param("type") String type,
                                   @Param("quantity") String quantity,
                                   @Param("date_order") String date_order,
                                   @Param("client") Clients client);
}
