package com.example.weather;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class TcpServer implements Runnable {

    private final int port;

    public TcpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();                                                                                
                new Thread(new SensorHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SensorHandler implements Runnable {
        private final Socket socket;

        public SensorHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()))
            ) {
                
                String inputLine = in.readLine();
                if (inputLine != null) {

                    String[] inputParts = inputLine.split(",");
                    if (inputParts.length >= 3) {
                        String city ="" ;
                        double temperature= 0;
                        double humidity=0;
                        try{
                            city = inputParts[0].trim();
                            temperature = Double.parseDouble(inputParts[1].trim());
                            humidity = Double.parseDouble(inputParts[2].trim());
                        } catch (Exception e){
                            out.write("Invalid Parameters\n");
                            out.flush();
                            return;
                        }
                        LocalDateTime timestamp = LocalDateTime.now();
                        
                        WeatherReading reading = new WeatherReading(city, temperature, humidity, timestamp);
                        WeatherStorage.updateWeather(city, reading);
                        
                        out.write("Data received successfully\n");
                        out.flush();
                        System.out.println("Received update: " + city + " Temp: " + temperature + " Humidity: " + humidity);
                    }
                    else {
                        out.write("Invalid Parameters\n");
                        out.flush();
                        return;
                    }
                } else {
                    out.write("Invalid Parameters\n");
                    out.flush();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
            try {
                socket.close();
            } catch (IOException ex) {

            }
        }
    }
}

