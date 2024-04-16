package com.studio.rain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntryStatistic {
    private String first_name;
    private String last_name;
    private int birthday;
    private String group;


    public EntryStatistic(String first_name, String last_name, int birthday, String group) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.group = group;
    }
}
