package com.example.pxiong_subbook;

import java.util.Date;

/**
 * Created by rick on 03/02/2018.
 */

public class Model {
    public String name;
    public String price;
    public String comments;
    public String date;


    public Model(String name, String price, String comments, String date){
        super();
        this.name = name;
        this.price = price;
        this.comments = comments;
        this.date = date;
    }

    public Model(String name, String price, String comments){
        super();
        this.name = name;
        this.price = price;
        this.comments = comments;
    }

    public Model(String name, String price){
        super();
        this.name = name;
        this.price = price;
    }

    public Model(String name){
        super();
        this.name = name;
    }

    public Model(){
        super();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;

    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getComments(){
        return comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public String getDate(){return date;}

    public void setDate(String date){
        this.date = date;
    }

    @Override
    public String toString(){
        return "Name:"+name + "\n" +"Monthly Charged:$"+price + "\n" + "comments:"+comments +
                "\n"+"Date:" + date;
    }
}
