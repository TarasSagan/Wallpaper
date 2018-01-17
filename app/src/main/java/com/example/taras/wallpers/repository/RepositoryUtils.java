package com.example.taras.wallpers.repository;

import com.example.taras.wallpers.api.ModelsOfResponse.ResponseRandomPhotos;

import java.util.ArrayList;
import java.util.List;


public class RepositoryUtils {

    public List<Photo> transormResponse(List<ResponseRandomPhotos> responseRandomPhotos){
        List<Photo> list = new ArrayList<>();
        for (int i=0; i<responseRandomPhotos.size(); i++){
            list.add(new Photo(
                    null,
                    responseRandomPhotos.get(i).getId(),
                    responseRandomPhotos.get(i).getWidth(),
                    responseRandomPhotos.get(i).getHeight(),
                    responseRandomPhotos.get(i).getColor(),
                    responseRandomPhotos.get(i).getUrls().getRaw(),
                    responseRandomPhotos.get(i).getUrls().getSmall(),
                    responseRandomPhotos.get(i).getUrls().getThumb()));
        }
        return list;
    }

}
