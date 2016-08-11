package com.shilpa.chatserver.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientListener extends Thread {
    
    private Socket socket;
    private Client client;
    private ClientHandler handler;
    
    public ClientListener(Socket socket, ClientHandler handler) {
        this.socket = socket;
        this.handler = handler;
        
    }
    
    @Override
    public void run() {
        PrintStream ps = null;
        try {
            ps = new PrintStream(socket.getOutputStream());
            ps.println("Welcome to chat server");
            ps.println("Enter your name:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = reader.readLine();
            System.out.println("hello " + name);
            client = new Client(name, socket);
            handler.addClient(client);
            handler.broadcastMessage(client.getUserName() + " has joined the chat");
            while (!isInterrupted()) {
                String msg = reader.readLine();
                String[] tokens= msg.split(";;");
                if(tokens[0].equalsIgnoreCase("pm")){
                    if(tokens.length>2){
                        handler.privateMessage(tokens[1],"(PM) from"+ client.getUserName()+">"+ tokens[2]);
                    }
                }else{
                handler.broadcastMessage(client.getUserName() + "says > " + msg);
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        
    }
}
