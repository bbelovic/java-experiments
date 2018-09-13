package net.bbelovic.javaexperiments.websocket.client;

import java.net.URI;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.net.http.HttpClient.newHttpClient;

public class SimpleWebsocketClient {

    private final WebSocket webSocket;

    public SimpleWebsocketClient(URI uri) {
        WebSocket.Listener listener = initListener();
        CompletableFuture<WebSocket> webSocketCompletableFuture = newHttpClient().newWebSocketBuilder()
                .buildAsync(uri, listener);
        webSocket = webSocketCompletableFuture.join();
    }

    public void sendText(String text) {
        webSocket.sendText(text, true);
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    private WebSocket.Listener initListener() {
        return new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                System.out.println("connected");
                WebSocket.Listener.super.onOpen(webSocket);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                System.err.println("received: "+ data);
                return WebSocket.Listener.super.onText(webSocket, data, last);
            }
        };
    }
}
