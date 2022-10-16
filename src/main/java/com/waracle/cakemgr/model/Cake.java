package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ToString
@AllArgsConstructor
public class Cake {
    @NotBlank(message = "Title is mandatory")
    @Size(min = 3,max = 100 ,message = "Title should have minimum 3 and maximum of 100 characters")
    private String title;
    @NotBlank(message = "Description is mandatory")
    @Size(min = 3,max = 100 ,message = "Description should have minimum 3 and maximum of 100 characters")
    private String desc;
    @NotBlank(message = "Image URL is mandatory")
    @Size(min = 10,max = 300 ,message = "Image URL should have minimum 10 and maximum of 300 characters")
    private String image;
}
