package com.example.chenlongjian.ycf_customview.model;

import java.util.Calendar;

/**
 * Created by chenlongjian on 2016/6/12.
 */
public class MonthLimitWrap {

    private Calendar uplimitDate;

    private Calendar lowlimitDate;

    private int monthcount;

    public Calendar getUplimitDate() {
        return uplimitDate;
    }

    public void setUplimitDate(Calendar uplimitDate) {
        this.uplimitDate = uplimitDate;
    }

    public Calendar getLowlimitDate() {
        return lowlimitDate;
    }

    public void setLowlimitDate(Calendar lowlimitDate) {
        this.lowlimitDate = lowlimitDate;
    }

    public int getMonthcount() {
        return monthcount;
    }

    public void setMonthcount(int monthcount) {
        this.monthcount = monthcount;
    }
}
