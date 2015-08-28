package com.xiaofan.taggroup;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaolifan on 2015/8/26.
 */
public class Tag implements Parcelable {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_ICON = 1;

    private int type = TYPE_TEXT;
    private int iconID = -1;
    private String tagText;

    public Tag() {
    }

    public Tag(int type, int iconID, String tagText) {
        this.type = type;
        this.iconID = iconID;
        this.tagText = tagText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(iconID);
        dest.writeString(tagText);
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {

        @Override
        public Tag createFromParcel(Parcel source) {
            Tag tag = new Tag();
            tag.type = source.readInt();
            tag.iconID = source.readInt();
            tag.tagText = source.readString();
            return tag;
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
}
