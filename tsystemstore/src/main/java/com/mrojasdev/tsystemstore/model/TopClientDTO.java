package com.mrojasdev.tsystemstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopClientDTO {

    private Client client;
    private Long orderCount;
}
