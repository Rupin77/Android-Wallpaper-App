package com.example.wallpaperappassignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE userId=(:userId) and password=(:password)")
    UserEntity login(String userId, String password);

}
