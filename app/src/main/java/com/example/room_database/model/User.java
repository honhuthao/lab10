package com.example.room_database.model;


import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class User {
    @PrimaryKey
    private  int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "first_name")
    private String lastName;

//    public Person(String firstName, String lastName){
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }


}
