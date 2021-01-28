package com.thoughtworks.capability.gtb.restfulapidesign.Entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Student {
    @NotNull
    @Min(1)
    private int id;
    private String name;
    private String gender;
    private String note;

    public Student() {
        this.name = "";
        this.gender = "";
        this.note = "";
    }

    public Student(int id, String name, String gender, String note) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.note = note;
    }
}
