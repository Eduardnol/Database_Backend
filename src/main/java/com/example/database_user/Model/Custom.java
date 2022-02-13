package com.example.database_user.Model;

import lombok.Data;

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
