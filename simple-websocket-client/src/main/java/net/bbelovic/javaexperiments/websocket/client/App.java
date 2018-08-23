package net.bbelovic.javaexperiments.websocket.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri = new URI("");

        httpClient.newWebSocketBuilder().buildAsync(uri, );
    }
}
