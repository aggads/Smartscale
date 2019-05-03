//package com.example.smartscale;
//
//import org.apache.commons.io.FileUtils;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Base64;
//
//public class TestOCR {
//    public static void main(String[] args)
//    {
//        try
//        {
//            //String secret_key = "sk_b1ed12e38bf6d7783bf724b3";
//            String secret_key = "sk_b1ed12e38bf6d7783bf724b3";
//            // Read image file to byte array
//            ClassLoader classLoader = TestOCR.class.getClassLoader();
//            File file = new File(classLoader.getResource("test2.JPG").getFile());
//
//            byte[] data = Files.readAllBytes(file.toPath());
//
//            // Encode file bytes to base64
//            byte[] encoded = Base64.getEncoder().encode(data);
//
//           /* OcrCall ocrcall = new OcrCall();
//            String plateAsString = ocrcall.plateAsString(encoded);
//            System.out.println(plateAsString);*/
//            // Setup the HTTPS connection to api.openalpr.com
//            URL url = new URL("https://api.openalpr.com/v2/recognize_bytes?recognize_vehicle=1&country=us&secret_key=" + secret_key);
//            URLConnection con = url.openConnection();
//            HttpURLConnection http = (HttpURLConnection)con;
//            http.setRequestMethod("POST"); // PUT is another valid option
//            http.setFixedLengthStreamingMode(encoded.length);
//            http.setDoOutput(true);
//
//            // Send our Base64 content over the stream
//            try(OutputStream os = http.getOutputStream()) {
//                os.write(encoded);
//            }
//
//            int status_code = http.getResponseCode();
//            if (status_code == 200)
//            {
//                // Read the response
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        http.getInputStream()));
//                String json_content = "";
//                String inputLine;
//                while ((inputLine = in.readLine()) != null)
//                    json_content += inputLine;
//                in.close();
//
//                System.out.println(json_content);
//            }
//            else
//            {
//                System.out.println("Got non-200 response: " + status_code);
//            }
//
//        }
//        catch (MalformedURLException e)
//        {
//            System.out.println("Bad URL");
//        }
//        catch (IOException e)
//        {
//            System.out.println("Failed to open connection");
//        }
//
//    }
//}
