package com.shushan.homework101.HttpHelper.service.entity.homework;

public class Level {
    private int level;
    private float price;
    private String name;
    private String icon;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Level{" +
                "level=" + level +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
