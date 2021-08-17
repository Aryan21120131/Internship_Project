package com.example.internship_project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoClass {

    @Insert
    void insertAllData(UserModel model);

    @Query("select * from crew")
    List<UserModel> getAllData();

    @Delete
    void deleteCrew(UserModel model);

    @Update
    void updateCrew(UserModel model);

    @Query("update crew SET name= :name,agency= :agency,wikipedia = :wikipedia,status= :status,image= :image,id= :id where 'key'= :key")
    void update(String name,String agency,String wikipedia,String status,String image,String id,int key);
}
