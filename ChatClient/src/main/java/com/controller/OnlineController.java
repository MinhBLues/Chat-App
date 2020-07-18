package com.controller;

import com.client.Client;
import com.view.OnlineView;

import java.io.IOException;

public class OnlineController {
    Client client;
    OnlineView onlineView;

    public OnlineController(String username) {
        client = new Client(username);
        onlineView = new OnlineView(username);
        client.sendName();
        setTableOnline(client);
    }
    public void show()
    {
        onlineView.setVisible(true);
    }

    public void setTableOnline(final Client client) {
        Runnable target;
        Thread readMsg = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 0;
                    Object[][] online = new Object[50][1];
                    while (true) {
                        String msg = client.getInput().readUTF();
                        int j = 0;
                        for (j = 0; j < i; j++) {
                            if (msg.equals(online[j][0])) break;
                        }
                        if (j == i && !msg.equals(client.getUsername())) {
                            online[i][0] = msg;
                            i++;
                        }
                        onlineView.setTable(online);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readMsg.start();

    }
}
