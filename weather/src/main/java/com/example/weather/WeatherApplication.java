package com.example.weather;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}


	@SuppressWarnings("unused")
    @Bean
    public CommandLineRunner startTcpServer() {
        return args -> {
            // Start TCP server on port 9000 
            Thread tcpServerThread = new Thread(new TcpServer(9000));
            tcpServerThread.setDaemon(true);
            tcpServerThread.start();
        };
    }

}



