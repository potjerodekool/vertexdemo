package org.platonos.vertexdemo.config;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import org.platonos.vertexdemo.db.CodeListRepro;
import org.platonos.vertexdemo.jdbc.JDBCTemplate;
import org.platonos.vertexdemo.web.CodeListService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Configuration
 */
public class Config {

    private Vertx vertx;
    private Properties properties;
    private JDBCTemplate jdbcTemplate;

    private CodeListRepro codeListRepro;

    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy));
        initVertex();
        initJDBC();
        initServices();
        initWeb();
    }

    private void destroy() {
        if (jdbcTemplate != null) {
            jdbcTemplate.getClient().close();
        }
    }

    private void initVertex() {
        vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        properties = new Properties();
        try (final InputStream inputStream = getClass().getResourceAsStream("/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initJDBC() {
        final JsonObject config = new JsonObject();
        config.put("url", properties.getProperty("jdbc.url"));
        config.put("driver_class", properties.getProperty("jdbc.driver_class"));
        config.put("user", properties.getProperty("jdbc.user"));
        config.put("password", properties.getProperty("jdbc.password"));
        final JDBCClient jdbcClient = JDBCClient.createShared(vertx, config);
        this.jdbcTemplate = new JDBCTemplate(jdbcClient);
    }

    private void initServices() {
        codeListRepro = new CodeListRepro(jdbcTemplate);
    }

    private void initWeb() {
        final HttpServer server = vertx.createHttpServer();
        final Router router = Router.router(vertx);

        final CodeListService codeListService = new CodeListService(codeListRepro);
        router
            .route(HttpMethod.GET, "/codelists")
            .blockingHandler(codeListService::handleGetCodeLists);

        server.requestHandler(router::accept).listen(8080);
    }

}
