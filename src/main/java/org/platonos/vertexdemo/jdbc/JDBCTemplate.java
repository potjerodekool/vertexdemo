package org.platonos.vertexdemo.jdbc;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

/**
 * JDBC template
 */
public class JDBCTemplate {

    private final JDBCClient client;

    public JDBCTemplate(final JDBCClient client) {
        this.client = client;
    }

    public JDBCClient getClient() {
        return client;
    }

    public void query(final String sql,
                      final Handler<AsyncResult<ResultSet>> handler) {
        this.client.getConnection(res -> {
            try (final SQLConnection connection = res.result()) {
                if (res.succeeded()) {
                    connection.query(sql, handler);
                }
            }
        });
    }
}
