
package com.shilpa.chatserver.Listener;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClientHandler {
    private List<Client> clients= new ArrayList<>();
    public void addClient(Client c){
    clients.add(c);
    }
    public List<Client>getClients(){
      return clients;
}
    public Client getByUserName(String userName){
      for(Client c: clients){
        if(c.getUserName().equalsIgnoreCase(userName)){
           return c;
        }
        
      }
      return null;
    }
    
    public Client getBySocket(Socket socket){
      for(Client c: clients){
        if(c.getSocket().equals(socket)){
           return c;
        }
        
      }
      return null;
    }
    
    public void broadcastMessage(String message) throws IOException{
     for(Client c: clients){
         PrintStream ps= new PrintStream(c.getSocket().getOutputStream());
         ps.println(message);
         
    }
    }

    public void privateMessage(String userName, String message) throws IOException {
    
    Client client= getByUserName(userName);
    if(client!=null){
        PrintStream ps= new PrintStream(client.getSocket().getOutputStream());
         ps.println(message);
    }
    else{
        System.out.println(userName + " not found");
    }
    }
}
