package data.model.api.servicepackage1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import data.model.api.servicepackage2.ServicePackage;
import utils.DataTypeConverter;

@Entity(tableName = "servicePackageResult")
public class ServiceResultOne {

    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @PrimaryKey
    @SerializedName("service_id")
    @Expose
    private int serviceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    @SerializedName("sort_order_list")
    @Expose
    private String sortOrderList;
    @SerializedName("status")
    @Expose
    private String status;
    @TypeConverters(DataTypeConverter.class)
    @SerializedName("packages")
    @Expose
    private List<ServicePackage> packages = null;
    @TypeConverters(DataTypeConverter.class)
    @SerializedName("images")
    @Expose
    private List<ImageOne> images = null;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSortOrderList() {
        return sortOrderList;
    }

    public void setSortOrderList(String sortOrderList) {
        this.sortOrderList = sortOrderList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ServicePackage> getPackages() {
        return packages;
    }

    public void setPackages(List<ServicePackage> packages) {
        this.packages = packages;
    }

    public List<ImageOne> getImages() {
        return images;
    }

    public void setImages(List<ImageOne> images) {
        this.images = images;
    }

}

