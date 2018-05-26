package csu.edu.ice.coursetable;

import java.io.Serializable;

/**
 * Created by ice on 2018/1/7.
 */

public class CustomCourse implements Serializable{

    protected int id;
    protected String name;
    protected String address;
    protected int startWeek;
    protected int endWeek;
    protected int startSection;
    protected int endSection;
    protected int weekday;
    protected int userId;//自定义课程userId不为0
    private int backgroundColor;

    public CustomCourse() {
    }

    public CustomCourse(int id, int startSection, int endSection, int weekday) {
        this.id = id;
        this.startSection = startSection;
        this.endSection = endSection;
        this.weekday = weekday;
    }

    public CustomCourse(int id, String name, int startSection, int endSection, int weekday) {
        this.id = id;
        this.name = name;
        this.startSection = startSection;
        this.endSection = endSection;
        this.weekday = weekday;
    }

    public CustomCourse(int id, String name, int startSection, int endSection, int weekday,int backgroundColor) {
        this.id = id;
        this.name = name;
        this.startSection = startSection;
        this.endSection = endSection;
        this.weekday = weekday;
        this.backgroundColor = backgroundColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getAddress() {
        if(address==null)return "";
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getStartSection() {
        return startSection;
    }

    public void setStartSection(int startSection) {
        this.startSection = startSection;
    }

    public int getEndSection() {
        return endSection;
    }

    public void setEndSection(int endSection) {
        this.endSection = endSection;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof CustomCourse)){
            return false;
        }
        if(((CustomCourse) obj).getId() == this.getId()){
            return true;
        }
        return false;
    }

}
