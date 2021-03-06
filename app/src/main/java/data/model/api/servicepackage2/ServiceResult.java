package data.model.api.servicepackage2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import utils.DataTypeConverter;

@Entity(tableName = "table_service_package")
public class ServiceResult implements Serializable, Parcelable {

    public ServiceResult(){}

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
    private List<ServicePackage> packages = null;
    @TypeConverters(DataTypeConverter.class)
    @SerializedName("images")
    @Expose
    private List<ImageResult> images = null;

    @TypeConverters(DataTypeConverter.class)
    @SerializedName("specification")
    @Expose
    private List<Specification> specification = null;

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

    public List<ServicePackage> getPackages(){
        return packages;
    }

    public void setPackages(List<ServicePackage> packages)
    {
        this.packages=packages;
    }

    public List<ImageResult> getImages() {
        return images;
    }

    public void setImages(List<ImageResult> images) {
        this.images = images;
    }

    public List<Specification> getSpecification() {
        return specification;
    }

    public void setSpecification(List<Specification> specification) {
        this.specification = specification;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(parentId);
        parcel.writeInt(serviceId);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeString(name);
        parcel.writeString(sortOrderList);
        parcel.writeString(status);
    }

    public static final Creator<ServiceResult> CREATOR = new Creator<ServiceResult>() {
        @Override
        public ServiceResult createFromParcel(Parcel in) {
            return new ServiceResult(in);
        }

        @Override
        public ServiceResult[] newArray(int size) {
            return new ServiceResult[size];
        }
    };

    protected ServiceResult(Parcel in){
        parentId = in.readString();
        serviceId = in.readInt();
        name = in.readString();
        title = in.readString();
        subTitle = in.readString();
        sortOrderList = in.readString();
        status = in.readString();
    }
}
