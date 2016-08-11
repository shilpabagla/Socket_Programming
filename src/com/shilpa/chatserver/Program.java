
package com.shilpa.chatserver;

import com.shilpa.chatserver.Listener.ClientHandler;
import com.shilpa.chatserver.Listener.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Program {

    public static void main(String[] args) throws IOException {
          
        int port= 9000;
        try{
            
            ServerSocket server= new ServerSocket(port);
            System.out.println("Server is running at "+port);
            ClientHandler handler= new ClientHandler();
            while(true){
            Socket client= server.accept();
                System.out.println("Connection request from "+ client.getInetAddress().getHostAddress());
                ClientListener listener= new ClientListener(client,handler);
                listener.start();
                
            }
    
    }catch(IOException ioe){
            System.out.println(ioe.getMessage());
            
    }
    }
    
}
