package org.platonos.vertexdemo.db;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import org.platonos.vertexdemo.jdbc.JDBCTemplate;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Codelist repository
 */
public class CodeListRepro {

    private final JDBCTemplate jdbcTemplate;

    public CodeListRepro(final JDBCTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void findCodeList(final Consumer<Stream<JsonObject>> consumer) {
        jdbcTemplate.query("SELECT * FROM codelist", res -> {
            if (res.succeeded()) {
                final ResultSet rs = res.result();
                consumer.accept(rs.getRows().stream());
            }
        });
    }
}
