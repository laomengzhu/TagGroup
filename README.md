# TagGroup

标签群组，支持文字和程序内部图片；

![Screenshot](https://raw.githubusercontent.com/xiaolifan/TagGroup/master/Screenshot/device-2015-08-28-141717.png)

## 使用（详见app目录）

### 1、TagGroup属性介绍

``` xml
<com.xiaofan.taggroup.TagGroup
        android:id="@+id/tagGroup"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#f08070"
        app:tagBackgroundColor="#ffffff"
        app:tagCornerRadius="6dp"
        app:tagHorizontalPadding="8dp"
        app:tagHorizontalSpace="8dp"
        app:tagStrokeColor="#000000"
        app:tagTextColor="#000000"
        app:tagTextSize="16sp"
        app:tagVerticalPadding="4dp"
        app:tagVerticalSpace="8dp" />
```
tagBackgroundColor：标签背景色；
tagCornerRadius：标签圆角半径；
tagHorizontalPadding：标签水平方向内边距；
tagHorizontalSpace：标签之间的水平间距；
tagStrokeColor：标签的描边颜色；
tagTextColor：标签文字颜色；
tagTextSize：标签文字大小；
tagVerticalPadding：标签竖直方向内边距；
tagVerticalSpace：标签之间的竖直边距。

### 2、添加标签

``` java
ArrayList<Tag> tags = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Tag tag = new Tag();
            tag.setType(Tag.TYPE_TEXT);
            tag.setTagText("tag" + i);
            tags.add(tag);
        }

        Tag tag = new Tag();
        tag.setType(Tag.TYPE_ICON);
        tag.setIconID(R.drawable.icon_apple);
        tags.add(tag);

        tag = new Tag();
        tag.setType(Tag.TYPE_ICON);
        tag.setIconID(R.drawable.icon_banana);
        tags.add(tag);

        tag = new Tag();
        tag.setType(Tag.TYPE_ICON);
        tag.setIconID(R.drawable.icon_pear);
        tags.add(tag);

        tag = new Tag();
        tag.setType(Tag.TYPE_ICON);
        tag.setIconID(R.drawable.icon_more);
        tags.add(tag);

        tagGroup.setTags(tags);
```

### 3、绑定标签点击事件监听器
需要实现TagGroup的OnTagClickListener接口，然后调用setOnTagClickListener设置监听器即可。

## License

    Mozilla Public License, version 2.0