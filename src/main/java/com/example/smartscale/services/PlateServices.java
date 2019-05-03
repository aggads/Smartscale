//package com.example.smartscale.services;
//
//import com.example.smartscale.controller.PlateController;
//import com.example.smartscale.entities.Plates;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PlateServices {
//
//    private PlateController plateController;
//
//    private Document getTheXml(String url){
//        Document xml = null;
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder;
//            // String output = netclient.getXMLSecure(url, USERNAME, PASSWORD);
//            List<Plates> platesList = plateController.getAll();
//            //
//            builder = factory.newDocumentBuilder();
//            xml = builder.parse(new InputSource(new StringReader(output)));
//            xml.getDocumentElement().normalize();
//        }catch (Exception ex) {
//            System.out.println("Warning XA-001 : "+ ex.getMessage());
//        }
//        return xml;
//    }
//}
