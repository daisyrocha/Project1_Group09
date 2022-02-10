package com.daclink.drew.sp22.cst438_project01_starter;


import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDoa {

    @Insert
    void registerUser(UserEntity userEntity);

}
