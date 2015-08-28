package com.xiaofan.taggroup.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xiaofan.taggroup.Tag;
import com.xiaofan.taggroup.TagGroup;
import com.xiaofan.taggroup.TagView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TagGroup.OnTagClickListener {

    private TagGroup tagGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagGroup = (TagGroup) findViewById(R.id.tagGroup);
        tagGroup.setOnTagClickListener(this);

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
    }

    @Override
    public void onTagClick(TagView tagView, Tag tag) {
        Toast.makeText(getApplicationContext(), "you click tag: " + tag, Toast.LENGTH_SHORT).show();
    }
}
