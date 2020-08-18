package com.jk.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    private Integer id;//	int
    private String name;//	varchar
    private String account;//	varchar
    private String password;//	varchar
    private Integer sex;//	int
    private String birthday;//	varchar
    private Integer typeId;//	int
    private String typeName;

}
