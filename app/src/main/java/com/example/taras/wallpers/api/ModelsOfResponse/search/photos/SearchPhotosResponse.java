
package com.example.taras.wallpers.api.ModelsOfResponse.search.photos;

import java.util.List;

import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchPhotosResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<PhotoItem> results = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<PhotoItem> getResults() {
        return results;
    }

    public void setResults(List<PhotoItem> results) {
        this.results = results;
    }

}
