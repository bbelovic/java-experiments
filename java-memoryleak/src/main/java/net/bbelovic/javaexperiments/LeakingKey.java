package net.bbelovic.javaexperiments;

public class LeakingKey {
    private final long id;

    public LeakingKey(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("LeakingKey[id=%d]", id);
    }
}
