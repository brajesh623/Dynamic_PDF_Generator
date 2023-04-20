package com.project.model;

import java.util.List;
import lombok.Data;

@Data
public class Details {
    private Integer id;
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private List<Item> items;
}
