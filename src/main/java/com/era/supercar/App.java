package com.era.supercar;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

	public static void main(String[] args) throws IOException {

        SpringApplication.run(App.class, args);
        
        System.out.println( "RUN PROGRAM" );
        
        try
        {
            DBConnection connection = new DBConnection();
            Thread cloud = new Thread(new ServerWorker(connection));
            cloud.start();

            //new Thread(new ServerWorker2(connection)).start();
        }
        catch(Exception e) {e.printStackTrace();}

	}
}
