package com.dicoding.course.cataloguemoviempv.model;

import android.view.View;
import android.widget.Toast;

/**
 * Created by isfaaghyth on 1/9/18.
 * github: @isfaaghyth
 */

public class Portfolio
{
    private String title;
    private String desc;
    private String img;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImg() {
        return img;
    }

    public void onClick(View v, Portfolio p) {
        Toast.makeText(v.getContext(), p.getTitle(), Toast.LENGTH_LONG).show();
    }
}
