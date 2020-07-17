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
//    public static void main(String[] args){
//        Client client = new Client();
//        client.readMessageThread();
//        client.writeMessageThread();
//    }

    public void sendName() {
        Runnable target;
        Thread sendName = new Thread(new Runnable() {
            public void run() {
                try {
                    output.writeUTF(username);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sendName.start();
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

    public void readMessageThread() {
        Runnable target;
        Thread readMessage = new Thread(new Runnable() {
            public void run() {
                String msg = null;
                do {
                    try {
                        msg = input.readUTF();
                        log(msg);
                    } catch (IOException ex) {
                        log("Read message thread: " + ex.getMessage());
                    }
                } while (!msg.equals("END"));
            }
        });
        readMessage.start();
    }

    public void writeMessageThread() {
        Thread sendMessage = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    String msg = scanner.nextLine();

                    try {
                        output.writeUTF(msg);
                    } catch (IOException ex) {
                        log("Write message thread: " + ex.getMessage());
                    }
                }
            }
        });
        sendMessage.start();
    }

    public void writeMessage(final String msg) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    output.writeUTF(msg);
                } catch (IOException ex) {
                    log("Write message thread: " + ex.getMessage());
                }
            }
        });
        thread.start();
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
