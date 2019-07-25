package com.brain.jdbc;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal cost;
    @NonNull
    private int count;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}