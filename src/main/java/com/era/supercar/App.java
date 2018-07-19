package com.era.supercar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        Thread cloud = new Thread(new CloudWorker());
        cloud.run();

        ArrayList<Thread> threads = new ArrayList<Thread>();

        ServerSocket server = new ServerSocket(330);

        while(true)
        {
            Socket client = server.accept();

            Thread clientWorker = new Thread( new ClientWorker(client) );
            threads.add(clientWorker);
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