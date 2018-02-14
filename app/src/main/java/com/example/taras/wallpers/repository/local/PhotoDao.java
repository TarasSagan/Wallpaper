package com.example.taras.wallpers.repository.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;

import com.example.taras.wallpers.repository.Photo;
import java.util.List;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photo")
    List<Photo> getAll();


//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<Photo> loadAllByIds(int[] userIds);

    @Insert
    void insert(List<Photo> list);

    @Insert
    void insert(Photo photo);

    @Insert
    void insertAll(Photo... photos);

//    @Query("DELETE FROM photoDatabase.db")
//    void deleteAll();
}
