package de.allianz.springboot.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Permission {
    TODO_READ("todo:read"),
    TODO_READ_ALL("todo_read_all"),
    TODO_CREATE("todo_create"),
    TODO_UPDATE("todo_update"),
    TODO_DELETE("todo_delete");

    private final String permission;

    Permission(String s) {
        this.permission=s;
    }

    public String getPermission(){
        return permission;
    }
}
