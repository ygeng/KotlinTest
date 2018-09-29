package com.aiden.kotlintest.joke;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JokeListBean implements Parcelable {

    private long allNum;
    private int allPages;
    private int currentPage;
    private int maxResult;
    private List<JokeBean> contentlist;

    protected JokeListBean(Parcel in) {
        allNum = in.readLong();
        allPages = in.readInt();
        currentPage = in.readInt();
        maxResult = in.readInt();
        contentlist = in.createTypedArrayList(JokeBean.CREATOR);
    }

    public static final Creator<JokeListBean> CREATOR = new Creator<JokeListBean>() {
        @Override
        public JokeListBean createFromParcel(Parcel in) {
            return new JokeListBean(in);
        }

        @Override
        public JokeListBean[] newArray(int size) {
            return new JokeListBean[size];
        }
    };

    public long getAllNum() {
        return allNum;
    }

    public void setAllNum(long allNum) {
        this.allNum = allNum;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public List<JokeBean> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<JokeBean> contentlist) {
        this.contentlist = contentlist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(allNum);
        dest.writeInt(allPages);
        dest.writeInt(currentPage);
        dest.writeInt(maxResult);
        dest.writeTypedList(contentlist);
    }
}
