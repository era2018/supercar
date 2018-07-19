package com.era.supercar;

import java.net.Socket;

class ClientWorker implements Runnable
{
    private Socket client;
    
    ClientWorker(Socket client)
    {
        this.client = client;
    }

	public void run() {
		
	}

}