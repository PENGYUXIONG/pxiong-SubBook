/*
 * Model
 *
 * Version 1.0
 *
 * January 30, 2018
 *
 * Copyright (c) 2018 Pengyu Xiong CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact pxiong@ualberta.ca
 */
package com.example.pxiong_subbook;

/**
 * Created by rick on 03/02/2018.
 */

public class Model {
    //Create an object consist of following elements
    public String name;
    public String price;
    public String comments;
    public String date;

    //Object can be formed by following constructs
    public Model(String name, String price, String comments, String date){
        super();
        this.name = name;
        this.price = price;
        this.comments = comments;
        this.date = date;
    }

    public Model(String name, String price, String date){
        super();
        this.name = name;
        this.price = price;
        this.date = date;
    }


    //Ask for return value
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
    //The format of the listview item.
    @Override
    public String toString(){
        return "Name:"+name + "\n" +"Monthly Charged:$"+price + "\n" + "comments:"+comments +
                "\n"+"Date:" + date;
    }
}
