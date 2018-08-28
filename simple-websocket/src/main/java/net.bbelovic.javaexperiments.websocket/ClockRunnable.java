package net.bbelovic.javaexperiments.websocket;

import javax.websocket.Session;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockRunnable implements Runnable {
    private final Session session;
    private final  String clientId;

    public ClockRunnable(Session session, String clientId) {
        this.session = session;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            LocalTime now = LocalTime.now();
            var formatted = DateTimeFormatter.ISO_TIME.format(now);
            try {
                session.getBasicRemote().sendText(clientId + ":" + formatted);
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }
}
