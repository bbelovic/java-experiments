package net.bbelovic.javaexperiments.websocket.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri = new URI("ws://localhost:8080/simple-websocket/simple");

        var listener = new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                System.out.println("connected");
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                StringBuilder sb = new StringBuilder(data);
                System.out.println("data=" + sb.toString() + ", last=" + last);

                return null;
            }
        };

        CompletableFuture<WebSocket> websocket = httpClient.newWebSocketBuilder().buildAsync(uri, listener);
        WebSocket webSocket = websocket.join();
//        webSocket.sendText("", true);


    }
}
