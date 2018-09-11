package net.bbelovic.javaexperiments.websocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoWebsocket {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected");
    }
    @OnMessage
    public String echo(String message) {
        return "echo " + message;
    }
}
