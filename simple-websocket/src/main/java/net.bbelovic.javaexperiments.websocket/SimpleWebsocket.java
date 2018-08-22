package net.bbelovic.javaexperiments.websocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.TimeUnit.SECONDS;

@ServerEndpoint("/simple")
public class SimpleWebsocket {
    private static volatile AtomicLong cntId = new AtomicLong(0L);
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> future;

    @OnOpen
    public void startClock(Session session) {
        final var id = cntId.incrementAndGet();
        Runnable r = () -> {
            LocalTime now = LocalTime.now();
            var clientId = "Client-" + id;
            var formatted = DateTimeFormatter.ISO_TIME.format(now);
            try {
                session.getBasicRemote().sendText(clientId +":"+ formatted);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        this.future = executorService.scheduleAtFixedRate(r, 0, 1, SECONDS);
    }

    @OnMessage
    public void stopClock(String message) {
        if ("stop".equals(message)) {
            this.future.cancel(true);
            System.out.println("Stopped..");
        }
    }
}