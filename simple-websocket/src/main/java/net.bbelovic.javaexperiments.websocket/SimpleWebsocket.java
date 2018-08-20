package net.bbelovic.javaexperiments.websocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;
import static java.util.concurrent.TimeUnit.SECONDS;

@ServerEndpoint("/simple")
public class SimpleWebsocket {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @OnOpen
    public void startClock(Session session) {
        Runnable r = () -> {
            LocalTime now = LocalTime.now();
            String formatted = ISO_LOCAL_TIME.format(now);
            try {
                session.getBasicRemote().sendText(formatted);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        executorService.scheduleAtFixedRate(r, 0, 1, SECONDS);

    }
}