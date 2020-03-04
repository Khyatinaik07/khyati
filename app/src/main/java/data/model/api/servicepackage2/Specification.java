package data.model.api.servicepackage2;

import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import utils.DataTypeConverter;

public class Specification {

    @SerializedName("specification_id")
    @Expose
    private String specificationId;
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("specification")
    @Expose
    private String specification;
    @SerializedName("isdefault")
    @Expose
    private String isdefault;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("status")
    @Expose
    private String status;

    @TypeConverters(DataTypeConverter.class)
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(String specificationId) {
        this.specificationId = specificationId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }


}
