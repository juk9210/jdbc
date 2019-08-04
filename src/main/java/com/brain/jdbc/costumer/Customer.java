package com.brain.jdbc.costumer;

import lombok.Data;
import lombok.NonNull;

@Data
public class Customer {
    @NonNull
    private long id;
    @NonNull
    private String firstName;
    @NonNull
    private String surName;
    @NonNull
    private long phone;
    @NonNull
    private String email;
    @NonNull
    private String city;
    @NonNull
    private long discount;
//    private LocalDateTime creationDate;
}
