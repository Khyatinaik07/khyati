package data.model.api.servicepackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import data.remote.ApiEndPoints;

public class Image {

    @SerializedName("image_id")
    @Expose
    private String imageId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("status")
    @Expose
    private String status;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = ApiEndPoints.IMAGE_BASE_URL+image;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
