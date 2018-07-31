package net.bbelovic.javaexperiments;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class App
{
    private ValueHolder<LeakingKey, Value> valueHolder = new ValueHolder<>();
    private final ScheduledExecutorService executorService;

    private App(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    private void add() {
        valueHolder.put(new LeakingKey(1), new Value(10_000));
    }

    private void schedule() {
        executorService.scheduleAtFixedRate(
                this::add, 10, 5, SECONDS);
    }

    public static void main( String[] args )
    {
        System.out.println("App started");
        App app = new App(Executors.newScheduledThreadPool(1));
        app.schedule();
    }
}
