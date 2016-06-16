package com.example.chenlongjian.ycf_customview.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenlongjian on 16/6/15.
 */
public class PrepaidCard implements Parcelable {

    private int id;
    private String name;
    private int tabId;//标签id
    private int propertyId;//属性id
    private String sort;//排序
    private String shortName;

    public PrepaidCard() {

    }

    protected PrepaidCard(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.tabId = in.readInt();
        this.propertyId = in.readInt();
        this.sort = in.readString();
        this.shortName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.tabId);
        dest.writeInt(this.propertyId);
        dest.writeString(this.sort);
        dest.writeString(this.shortName);

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
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public int getTabId() {
        return tabId;
    }
    public void setTabId(int tabId) {
        this.tabId = tabId;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public int getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    // 必须要创建一个名叫CREATOR的常量。
    public static final Parcelable.Creator<PrepaidCard> CREATOR = new Parcelable.Creator<PrepaidCard>() {
        @Override
        public PrepaidCard createFromParcel(Parcel source) {
            return new PrepaidCard(source);
        }
        //重写createFromParcel方法，创建并返回一个获得了数据的user对象
        @Override
        public PrepaidCard[] newArray(int size) {
            return new PrepaidCard[size];
        }
    };

}
