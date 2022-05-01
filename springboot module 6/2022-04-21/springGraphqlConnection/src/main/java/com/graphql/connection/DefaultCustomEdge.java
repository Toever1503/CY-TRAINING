package com.graphql.connection;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.AllArgsConstructor;

public class DefaultCustomEdge<T> implements Edge<T> {
    private final T node;

    public DefaultCustomEdge(T node) {
        this.node = node;
    }

    @Override
    public T getNode() {
        return this.node;
    }

    @Override
    public ConnectionCursor getCursor() {
        return null;
    }
}
