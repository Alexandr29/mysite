package com.nixsolutions.service.impl;

public class Role {
    private Long id;
    private String name;

    public Role(String name) {
        this(null, name);
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
