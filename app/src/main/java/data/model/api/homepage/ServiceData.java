package data.model.api.homepage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class ServiceData implements Serializable {

    @PrimaryKey
    @SerializedName("service_id")
    @Expose
    private int serviceId;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("sort_order_list")
    @Expose
    private String sortOrderList;
    @SerializedName("sort_order_grid")
    @Expose
    private String sortOrderGrid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdate")
    @Expose
    private String createdate;
    @SerializedName("modifydate")
    @Expose
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
        this.icon = "http://brss.in/jigar/serviceprovider/assets/General/images/"+icon;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = "http://brss.in/jigar/serviceprovider/assets/General/images/"+banner;
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
