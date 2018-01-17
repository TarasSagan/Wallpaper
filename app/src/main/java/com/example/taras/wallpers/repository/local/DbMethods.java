package com.example.taras.wallpers.repository.local;

import android.content.Context;
import com.example.taras.wallpers.repository.Photo;
import java.util.List;


public class DbMethods {
    public void insertData(List<Photo> list, Context context){
        AppDatabase.getInstance(context).getPhotoDao().insert(list);
    }
}
