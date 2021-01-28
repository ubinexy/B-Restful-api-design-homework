package com.thoughtworks.capability.gtb.restfulapidesign.Entity;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String name;

    private List<Integer> members;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<Integer> members) {
        this.name = name;
        this.members = members;
    }
}
