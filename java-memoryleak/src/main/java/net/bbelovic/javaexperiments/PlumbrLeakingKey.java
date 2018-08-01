package net.bbelovic.javaexperiments;

import java.util.Objects;

import static java.lang.String.format;

public final class PlumbrLeakingKey {
    private final long id;

    public PlumbrLeakingKey(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return format("PlumbrLeakingKey[id=%d]", id);
    }
}
