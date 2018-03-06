package com.example.taras.wallpapers.repository.local;

import android.content.Context;
import com.example.taras.wallpapers.repository.Photo;
import java.util.List;


public class DbMethods {
    public void insertData(List<Photo> list, Context context){
        AppDatabase.getInstance(context).getPhotoDao().insert(list);
    }
}
