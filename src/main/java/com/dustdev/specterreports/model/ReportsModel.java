package com.dustdev.specterreports.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportsModel {

    private Integer id;
    private String player, autor, motivo;
    private Long data;

}
