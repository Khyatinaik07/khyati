package data.model.api.homepage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import data.remote.ApiEndPoints;

@Entity
public class ServiceData implements Serializable {

    @PrimaryKey
    @SerializedName("service_id")
    @ColumnInfo(name = "serviceId")
    private int serviceId;

    @SerializedName("parent_id")
    @ColumnInfo(name = "parentId")
    private String parentId;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("sub_title")
    @ColumnInfo(name = "subTitle")
    private String subTitle;

    @SerializedName("type")
    @ColumnInfo(name = "type")
    private String type;

    @SerializedName("icon")
    @ColumnInfo(name = "icon")
    private String icon;

    @SerializedName("banner")
    @ColumnInfo(name = "banner")
    private String banner;

    @SerializedName("sort_order_list")
    @ColumnInfo(name = "sortOrderList")
    private String sortOrderList;

    @SerializedName("sort_order_grid")
    @ColumnInfo(name = "sortOrderGrid")
    private String sortOrderGrid;

    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status;

    @SerializedName("createdate")
    @ColumnInfo(name = "createdate")
    private String createdate;

    @SerializedName("modifydate")
    @ColumnInfo(name = "modifydate")
    private String modifydate;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = ApiEndPoints.IMAGE_BASE_URL +icon;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = ApiEndPoints.IMAGE_BASE_URL+banner;
    }

    public String getSortOrderList() {
        return sortOrderList;
    }

    public void setSortOrderList(String sortOrderList) {
        this.sortOrderList = sortOrderList;
    }

    public String getSortOrderGrid() {
        return sortOrderGrid;
    }

    public void setSortOrderGrid(String sortOrderGrid) {
        this.sortOrderGrid = sortOrderGrid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

}
