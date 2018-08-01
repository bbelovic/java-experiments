package net.bbelovic.javaexperiments;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class App<K, V>
{
    private final ValueHolder<K, V> valueHolder;
    private final ScheduledExecutorService executorService;

    private App(ValueHolder<K, V> valueHolder, ScheduledExecutorService executorService) {
        this.valueHolder = valueHolder;
        this.executorService = executorService;
    }

    private void add(K k, V v) {
        valueHolder.put(k, v);
    }

    private void schedule(Runnable action) {
        executorService.scheduleAtFixedRate(
                action, 10, 5, SECONDS);
    }

    public static void main( String[] args )
    {
        System.out.println("App started");

        var valueHolder = new ValueHolder<PlumbrLeakingKey, Value>();
        var app = new App<>(valueHolder, Executors.newScheduledThreadPool(1));
        Runnable r = () -> app.add(new PlumbrLeakingKey(1), new Value(10_000));
        app.schedule(r);
    }
}
