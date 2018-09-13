package net.bbelovic.javaexperiments.websocket.client;

import java.net.URI;
import java.util.Scanner;

public class CliApp {
    public static void main(String[] args) throws Exception {

        var uri = new URI("ws://localhost:8080/simple-websocket/echo");
        var client = new SimpleWebsocketClient(uri);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println("sending "+ next);
            client.sendText(next);
        }
    }
}
