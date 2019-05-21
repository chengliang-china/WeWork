package com.wework.base.domain.base;

import java.io.Serializable;

public enum Contract{

    MAN(1,"男"),WOMAN(0,"女");

    private int item;
    private String desc;

    Contract(int item,String desc){
        this.item = item;
        this.desc = desc;
    }

}
