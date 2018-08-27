package net.bbelovic.javaexperiments.websocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

@ServerEndpoint("/simple")
public class SimpleWebsocket {
    private static volatile AtomicLong cntId = new AtomicLong(0L);
    private volatile boolean running;
    private Thread thread;

    @OnOpen
    public void startClock(Session session) {
        final var id = cntId.incrementAndGet();
        System.out.println(String.format("Client %d connected", id));
        var clientId = "Client-" + id;
        running = true;
        Runnable r = () -> {
            while (running) {
                LocalTime now = LocalTime.now();
                var formatted = DateTimeFormatter.ISO_TIME.format(now);
                try {
                    session.getBasicRemote().sendText(clientId + ":" + formatted);
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread = new Thread(new ClockRunnable(session, clientId));
        thread.start();
    }

    @OnMessage
    public void stopClock(String message) {
        if ("stop".equals(message)) {
            this.thread.interrupt();
            System.out.println("Stopped..");
        }
    }
}