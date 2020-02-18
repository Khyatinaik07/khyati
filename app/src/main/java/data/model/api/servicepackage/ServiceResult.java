package data.model.api.servicepackage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import utils.DataTypeConverter;

@Entity(tableName = "table_service_package")
public class ServiceResult {

    @PrimaryKey
    @SerializedName("parent_id")
    @Expose
    private int parentId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
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
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @TypeConverters(DataTypeConverter.class)
    @SerializedName("specification")
    @Expose
    private List<Specification> specification = null;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Specification> getSpecification() {
        return specification;
    }

    public void setSpecification(List<Specification> specification) {
        this.specification = specification;
    }
}
