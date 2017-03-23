package org.platonos.vertexdemo.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.platonos.vertexdemo.db.CodeListRepro;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

/**
 * Created by Evert on 22-3-2017.
 * https://github.com/vert-x3/vertx-examples/blob/master/unit-examples/src/test/java/io/vertx/example/unit/test/MyJUnitTest.java
 */
@RunWith(VertxUnitRunner.class)
public class CodeListServiceTest {

    @InjectMocks
    private CodeListService codeListService;

    @Mock
    private CodeListRepro codeListReproMock;

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    private Vertx vertx;

    private HttpServer server;

    @Before
    public void setUp(final TestContext context) {
        MockitoAnnotations.initMocks(this);
        vertx = rule.vertx();
        server = vertx.createHttpServer();
    }

    @After
    public void tearDown(final TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testHandleGetCodeLists(final TestContext context) {
        doAnswer(invocation -> {
                    Consumer<Stream<JsonObject>> consumer = ((InvocationOnMock) invocation).getArgument(0);
                    consumer.accept(Stream.of(new JsonObject().put("id", 1).put("name", "kleuren").put("uid", "colors")));
                    return null;
                }
        ).when(codeListReproMock).findCodeList(any(Consumer.class));

        server.requestHandler(req -> {
            RoutingContextMock routingContext = new RoutingContextMock(req);
            codeListService.handleGetCodeLists(routingContext);
        }).listen(8080, context.asyncAssertSuccess());

        final HttpClient client = vertx.createHttpClient();
        final Async async = context.async();

        client.getNow(8080, "localhost", "/", resp -> {
            resp.bodyHandler(body -> {
                final Map<String,Object> map = new HashMap<>();
                map.put("id", 1);
                map.put("name", "kleuren");
                map.put("uid", "colors");

                final JsonArray expected = new JsonArray(Collections.singletonList(map));
                final JsonArray actual = body.toJsonArray();

                context.assertEquals(expected, actual);
                client.close();
                async.complete();
            });
        });
    }

}

