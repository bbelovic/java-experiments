package net.bbelovic.javaexperiments.websocket.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri = new URI("ws://localhost:8080/simple-websocket/echo");

        var listener = new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                System.out.println("connected");
                WebSocket.Listener.super.onOpen(webSocket);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                StringBuilder sb = new StringBuilder(data);
                webSocket.request(1);

                return CompletableFuture.completedFuture(data)
                        .thenAccept(System.out::println);
            }
        };

//        CompletableFuture<WebSocket> ws = httpClient.newWebSocketBuilder().buildAsync(uri, listener);

        SimpleWebsocketClient client = new SimpleWebsocketClient(uri);

        WebSocket webSocket = client.getWebSocket();

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println("read: "+ next);

//        while (!webSocket.isInputClosed()) {
            webSocket.sendText(next, true);
//                    .thenAccept(w -> w.sendText("xxx2", true));
            Thread.sleep(100);
//        }
//        WebSocket webSocket = websocket.join();
//        while (!webSocket.isInputClosed()) {
//            Thread.sleep(1000);
//        }
//        webSocket.sendText("xxx", false).thenRun(()-> System.out.println("after text"));


    }
}
