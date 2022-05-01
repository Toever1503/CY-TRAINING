package com.graphql.connection;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
public class CursorUtil {

    private final Base64.Encoder encoder;
    private final Base64.Decoder decoder;

    private String salt;

    public CursorUtil(@Value("${animenews.salt}") String salt) {
        this.encoder = Base64.getEncoder();
        this.decoder = Base64.getDecoder();
        this.salt = encoder.encodeToString(salt.getBytes(StandardCharsets.UTF_8));
    }

    public ConnectionCursor from(Object id) {
        return new DefaultConnectionCursor(base64Encode(id.toString()));
    }

    public String base64Encode(String value) {
        //     encode 3 times to make sure it's long
        // time 1: encode salt
        // time 2: encode value
        // time 3: encode value from 1+2
        return this.encoder.encodeToString(this.salt.concat(
                        this.encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8)))
                .getBytes(StandardCharsets.UTF_8));
    }

    public String base64Decode(String value) {
        String decoded = new String(decoder.decode(value), StandardCharsets.UTF_8);
        return new String(this.decoder.decode(decoded.substring(this.salt.length()).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public <T> ConnectionCursor getFirstCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    public <T> ConnectionCursor getLastCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor();
    }

}
