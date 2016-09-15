package com.carouselview;

import android.net.Uri;

/**
 * Created by marija.radisavljevic on 9/15/2016.
 */
public class ContactData implements Comparable<ContactData>{
    private Uri uri;
    private String number;
    private String name;

    @Override
    public int compareTo(ContactData fruit) {



      return   name.compareTo(fruit.getName());
    }
    public Uri geturi() {
        return uri;
    }

    public void seturi(Uri bitmap) {
        this.uri = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
