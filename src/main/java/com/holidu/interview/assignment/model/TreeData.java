package com.holidu.interview.assignment.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TreeData {

    private Integer tree_id;

    private Integer block_id;

    private LocalDateTime created_at;

    private String status;

    private String spc_common;

    private String address;

    private String zipcode;

    private String state;

    private Double latitude;

    private Double longitude;

    private Double x_sp;

    private Double y_sp;

}
