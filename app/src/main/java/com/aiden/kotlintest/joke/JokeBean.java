package com.aiden.kotlintest.joke;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JokeBean implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    private String ct;
    private String text;
    private String title;

    protected JokeBean(Parcel in) {
        id = in.readLong();
        ct = in.readString();
        text = in.readString();
        title = in.readString();
    }

    @Generated(hash = 1466211504)
    public JokeBean(Long id, String ct, String text, String title) {
        this.id = id;
        this.ct = ct;
        this.text = text;
        this.title = title;
    }

    @Generated(hash = 46550206)
    public JokeBean() {
    }

    public static final Creator<JokeBean> CREATOR = new Creator<JokeBean>() {
        @Override
        public JokeBean createFromParcel(Parcel in) {
            return new JokeBean(in);
        }

        @Override
        public JokeBean[] newArray(int size) {
            return new JokeBean[size];
        }
    };

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(ct);
        dest.writeString(text);
        dest.writeString(title);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
