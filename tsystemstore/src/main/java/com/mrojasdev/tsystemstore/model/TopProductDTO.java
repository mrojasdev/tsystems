package com.mrojasdev.tsystemstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopProductDTO {

    private Product product;
    private Long quantity;
}
