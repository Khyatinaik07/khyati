package data.model.api.servicepackage2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import data.remote.ApiEndPoints;

public class Image {

    @SerializedName("gallery_id")
    @Expose
    private String galleryId;
    @SerializedName("filepath")
    @Expose
    private String filepath;
    @SerializedName("file_type")
    @Expose
    private String fileType;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("status")
    @Expose
    private String status;

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = ApiEndPoints.IMAGE_BASE_URL +filepath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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
