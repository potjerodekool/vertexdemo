package org.platonos.vertexdemo.web;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.platonos.vertexdemo.db.CodeListRepro;
import org.platonos.vertexdemo.model.CodeList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Codelist service
 */
public class CodeListService {

    private final CodeListRepro codeListRepro;

    public CodeListService(final CodeListRepro codeListRepro) {
        this.codeListRepro = codeListRepro;
    }

    public void handleGetCodeLists(final RoutingContext routingContext) {
        codeListRepro.findCodeList(codelistNames -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json; charset=utf-8");

            final List<CodeList> codeLists = codelistNames
                    .map(object ->
                        new CodeList()
                            .setId(object.getInteger("id"))
                            .setName(object.getString("name"))
                            .setUid(object.getString("uid"))
                    ).collect(Collectors.toList());

            response.end(Json.encodePrettily(codeLists));
        });
    }
}
