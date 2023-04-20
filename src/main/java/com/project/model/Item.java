package com.project.model;
import lombok.Data;

@Data
public class Item {
    private Integer id;
    private String name;
    private String quantity;
    private Double rate;
    private Double amount;
}
