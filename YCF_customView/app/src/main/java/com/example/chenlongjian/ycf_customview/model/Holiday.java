package com.example.chenlongjian.ycf_customview.model;

/**
 * Created by chenlongjian on 2016/6/12.
 */
public class Holiday {
    private Long id;
    private String date;
    private Boolean isHoliday;
    private String name;
    private Integer holidayType;

    public Holiday() {
    }

    public Holiday(Long id) {
        this.id = id;
    }

    public Holiday(Long id, String date, Boolean isHoliday, String name, Integer holidayType) {
        this.id = id;
        this.date = date;
        this.isHoliday = isHoliday;
        this.name = name;
        this.holidayType = holidayType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHolidayType() {
        return holidayType;
    }



    public void setHolidayType(Integer holidayType) {
        this.holidayType = holidayType;
    }
}
