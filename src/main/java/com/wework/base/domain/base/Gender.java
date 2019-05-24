package com.wework.base.domain.base;

import java.io.Serializable;

public enum Gender{

    MAN(1,"男"),WOMAN(0,"女");

    private int item;
    private String desc;

    Gender(int item,String desc){
        this.item = item;
        this.desc = desc;
    }

}
