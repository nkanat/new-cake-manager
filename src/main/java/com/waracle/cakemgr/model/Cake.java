package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Cake {

    private String title;
    private String desc;
    private String image;
}
