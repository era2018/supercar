package com.era.supercar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        Thread cloud = new Thread(new ServerWorker());
        cloud.run();

        ServerSocket server = new ServerSocket(330);

        while(true)
        {
            Socket client = server.accept();

            Thread clientWorker = new Thread( new ClientWorker(client) );

            clientWorker.run();
        }

        //server.close();

        /*
        for(Thread thread : threads)
        {

        }
        */
    }
}