package com.example.room_database.adapter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.room_database.dao.PersonDao;
import com.example.room_database.model.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
