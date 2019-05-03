package com.example.smartscale.controller;

import com.example.smartscale.entities.Clients;
import com.example.smartscale.entities.Plates;
import com.example.smartscale.repository.ClientsRepository;
import com.example.smartscale.repository.PlatesRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


@RestController
@RequestMapping("/vehicule")
public class PlateController {

    @Autowired
    private PlatesRepository platesRepository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    RestTemplate restTemplate;

//    @PostMapping("/getPlate")
//    public JSONObject getData(@RequestBody String plateEncoded) throws ParseException {
//        System.out.println( "starting getData function");
//        JSONParser parser = new JSONParser();
//        String secret_key = "sk_b1ed12e38bf6d7783bf724b3";
//        String uri = "https://api.openalpr.com/v2/recognize_bytes?recognize_vehicle=1&country=be&secret_key=" + secret_key;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
//        HttpEntity<byte[]> entity = new HttpEntity( plateEncoded, headers );
//
//        String response = restTemplate.exchange( uri, HttpMethod.POST, entity, String.class ).getBody();
//        System.out.println( "response receive from OCR" );        // Transform into an Object
//        JSONObject json = (JSONObject) parser.parse( response );
//        //And look for the plate number
//        JSONArray resultArray = (JSONArray) json.get( "results" );
//        if (resultArray.size() == 0) {
//            JSONObject emptyPlate = new JSONObject();
//            return emptyPlate;
//        } else {
//            JSONObject resultObject = (JSONObject) resultArray.get( 0 );
//            String plateNumber = (String) resultObject.get( "plate" );
//            System.out.println( plateNumber );
//            JSONObject plateResponse = platesRepository.findByName( plateNumber );
//            if (plateResponse == null) {
//                JSONObject emptyPlate = new JSONObject();
//                return emptyPlate;
//            } else {
//                return plateResponse;
//            }
//        }
//    }
    @PostMapping("/getPlate")
    public JSONObject getData(@RequestBody String plateEncoded) {
        System.out.println( "get data called" );
        return platesRepository.findByName( "1JEP350" );
    }

    @GetMapping("/getall")
    public List<Plates> getAll() {
        return platesRepository.findAll();
    }

    @GetMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getUserWithPlate() {
        return platesRepository.findUserWithPlates();
    }


    @GetMapping("/clientFromPlate/{plate}")
    public List<JSONObject> getUsersNameFromPlate(@PathVariable("plate") String plate)
    {
        return platesRepository.getUsersNameFromPlate(plate);
    }

    @GetMapping("/plateFromClient/{clientId}")
    public List<JSONObject> getUsersIdOfAPlate(@PathVariable("clientId") int clientId)
    {
        return platesRepository.getPlatesFromUserId(clientId);
    }

    @PostMapping(value = "/addPlate", consumes =  APPLICATION_JSON_UTF8_VALUE)
    public String addPlate(@RequestBody Plates plateToAdd) {
        String clientName = plateToAdd.getClient().getName();
        clientsRepository.saveClientIfNotExist(clientName);
        Clients newClient = clientsRepository.findByName(clientName);
        plateToAdd.setClient( newClient );
        platesRepository.savePlateIfNotExist( plateToAdd.getPlate(),
                plateToAdd.getType(),
                plateToAdd.getQuantity(),
                plateToAdd.getDateOrder(),
                newClient);
        //platesRepository.save( plateToAdd );
        return "OK";
    }

    @PostMapping(value= "/test", consumes = APPLICATION_JSON_UTF8_VALUE)
    public String addOrder(@RequestBody Plates platesToAdd) {
        platesRepository.savePlateIfNotExist( platesToAdd.getPlate(),
                platesToAdd.getType(),
                platesToAdd.getQuantity(),
                platesToAdd.getDateOrder(),
                platesToAdd.getClient());
        return "ok";
    }
//    @PostMapping(value = "/addPlate", consumes =  APPLICATION_JSON_UTF8_VALUE)
//    public JSONObject addPlate(@RequestBody Plates plate) {
//        return platesRepository.savePlate(plate);
//}
}

