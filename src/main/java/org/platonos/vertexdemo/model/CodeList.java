package org.platonos.vertexdemo.model;

/**
 * Created by Evert on 18-3-2017.
 */
public class CodeList {

    private int id;

    private String name;

    private String uid;

    public int getId() {
        return id;
    }

    public CodeList setId(final int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CodeList setName(final String name) {
        this.name = name;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public CodeList setUid(final String uid) {
        this.uid = uid;
        return this;
    }
}
