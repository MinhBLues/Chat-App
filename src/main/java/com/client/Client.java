package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class Client {
    Scanner scanner;
    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream output = null;
    InetAddress ip;
    private String username;

    public Client(String name) {
        try {
            ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, 1234);
            username = name;
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (UnknownHostException ex) {
            log("Client: " + ex.getMessage());
        } catch (IOException e) {
            log("Client: " + e.getMessage());
        }

    }

    public void sendName() {
        try {
            output.writeUTF("1#" + username);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public String getUsername() {
        return this.username;
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
