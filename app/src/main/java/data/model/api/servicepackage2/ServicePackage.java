package data.model.api.servicepackage2;

import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import utils.DataTypeConverter;

public class ServicePackage {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("title_id")
    @Expose
    private String titleId;
    @SerializedName("selection_type")
    @Expose
    private String selectionType;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @TypeConverters(DataTypeConverter.class)
    @SerializedName("specification")
    @Expose
    private List<Specification> specification = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<Specification> getSpecification() {
        return specification;
    }

    public void setSpecification(List<Specification> specification) {
        this.specification = specification;
    }

}

