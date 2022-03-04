package com.example.database_user.dtos;

import lombok.Data;


/**
 * Custom fields for the person class, you can personalize with selected, name and tagname
 */
@Data
public class Custom {
    private String selected;
    private String name;
    private String tagname;


    public Custom() {
        selected = "";
        name = "";
        tagname = "";
    }
}
