package org.platonos.vertexdemo.web;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RoutingContextMock implements RoutingContext {

    public RoutingContextMock(final HttpServerRequest request) {
        this.request = request;
    }

    @Override
    public HttpServerRequest request() {
        return request;
    }

    @Override
    public HttpServerResponse response() {
        return request.response();
    }

    @Override
    public void next() {
    }

    @Override
    public void fail(final int statusCode) {

    }

    @Override
    public void fail(final Throwable throwable) {

    }

    @Override
    public RoutingContext put(final String key, final Object obj) {
        return null;
    }

    @Override
    public <T> T get(final String key) {
        return null;
    }

    @Override
    public <T> T remove(final String key) {
        return null;
    }

    @Override
    public Map<String, Object> data() {
        return null;
    }

    @Override
    public Vertx vertx() {
        return null;
    }

    @Override
    public String mountPoint() {
        return null;
    }

    @Override
    public Route currentRoute() {
        return null;
    }

    @Override
    public String normalisedPath() {
        return null;
    }

    @Override
    public Cookie getCookie(final String name) {
        return null;
    }

    @Override
    public RoutingContext addCookie(final Cookie cookie) {
        return null;
    }

    @Override
    public Cookie removeCookie(final String name) {
        return null;
    }

    @Override
    public int cookieCount() {
        return 0;
    }

    @Override
    public Set<Cookie> cookies() {
        return null;
    }

    @Override
    public String getBodyAsString() {
        return null;
    }

    @Override
    public String getBodyAsString(final String encoding) {
        return null;
    }

    @Override
    public JsonObject getBodyAsJson() {
        return null;
    }

    @Override
    public JsonArray getBodyAsJsonArray() {
        return null;
    }

    @Override
    public Buffer getBody() {
        return null;
    }

    @Override
    public Set<FileUpload> fileUploads() {
        return null;
    }

    @Override
    public Session session() {
        return null;
    }

    @Override
    public User user() {
        return null;
    }

    @Override
    public Throwable failure() {
        return null;
    }

    @Override
    public int statusCode() {
        return 0;
    }

    @Override
    public String getAcceptableContentType() {
        return null;
    }

    @Override
    public ParsedHeaderValues parsedHeaders() {
        return null;
    }

    @Override
    public int addHeadersEndHandler(final Handler<Void> handler) {
        return 0;
    }

    @Override
    public boolean removeHeadersEndHandler(final int handlerID) {
        return false;
    }

    @Override
    public int addBodyEndHandler(final Handler<Void> handler) {
        return 0;
    }

    @Override
    public boolean removeBodyEndHandler(final int handlerID) {
        return false;
    }

    @Override
    public boolean failed() {
        return false;
    }

    @Override
    public void setBody(final Buffer body) {

    }

    @Override
    public void setSession(final Session session) {

    }

    @Override
    public void setUser(final User user) {

    }

    @Override
    public void clearUser() {

    }

    @Override
    public void setAcceptableContentType(@Nullable final String contentType) {

    }

    @Override
    public void reroute(final HttpMethod method, final String path) {
    }

    @Override
    public List<Locale> acceptableLocales() {
        return null;
    }

    @Override
    public Map<String, String> pathParams() {
        return null;
    }

    @Override
    public String pathParam(final String name) {
        return null;
    }

    private final HttpServerRequest request;
}
