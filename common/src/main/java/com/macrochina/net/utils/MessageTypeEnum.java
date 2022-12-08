package com.macrochina.net.utils;


public enum MessageTypeEnum {

    FINDUSRPWD("找回用户密码",1,"FINDUSRPWD"),
    FINDADUSRPWD("找回管理员密码",2,"FINDADUSRPWD"),
    USRREG("找回管理员密码",3,"USRREG");
    private String name;
    private String type;
    private int value;

    private MessageTypeEnum(String name,int value,String type){
        this.name=name;
        this.value=value;
        this.type=type;
    }
    public static String getName(int value) {
        for (MessageTypeEnum mt : MessageTypeEnum.values()) {
            if (mt.getValue() == value) {
                return mt.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageTypeEnum{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                '}';
    }}
