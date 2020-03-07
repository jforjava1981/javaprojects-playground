package com.example.springreactivedemo.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Foo {
    @NonNull
    private String name;
    @NonNull
    private Long id;
}
