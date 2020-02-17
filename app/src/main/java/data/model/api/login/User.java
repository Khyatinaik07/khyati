package data.model.api.login;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_user")
public class User implements Parcelable,GenericProfileInterface {

    public User()
    {
    }

    @PrimaryKey
    @SerializedName("customer_id")
    @ColumnInfo(name = "customer_id")
    private int customerId;

    @ColumnInfo(name = "firstname")
    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    @ColumnInfo(name = "lastname")
    private String lastname;

    @SerializedName("emailid")
    @ColumnInfo(name = "emailid")
    private String emailid;

    @SerializedName("mobilenumber")
    @ColumnInfo(name = "mobilenumber")
    private String mobilenumber;

    @SerializedName("country_code")
    @ColumnInfo(name = "country_code")
    private String countryCode;

    @SerializedName("profile")
    @ColumnInfo(name = "profile")
    private String profile;

    @SerializedName("password")
    @ColumnInfo(name = "password")
    private String password;

    @SerializedName("country_id")
    @ColumnInfo(name = "country_id")
    private String countryId;

    @SerializedName("state_id")
    @ColumnInfo(name = "state_id")
    private String stateId;

    @SerializedName("city_id")
    @ColumnInfo(name = "city_id")
    private String cityId;

    @SerializedName("zipcode")
    @ColumnInfo(name = "zipcode")
    private String zipcode;

    @SerializedName("latitude")
    @ColumnInfo(name = "latitude")
    private String latitude;

    @SerializedName("longitude")
    @ColumnInfo(name = "longitude")
    private String longitude;

    @SerializedName("street_address")
    @ColumnInfo(name = "street_address")
    private String streetAddress;

    @SerializedName("fcmid")
    @ColumnInfo(name = "fcmid")
    private String fcmid;

    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status;

    @SerializedName("createdate")
    @ColumnInfo(name = "createdate")
    private String createdate;

    @SerializedName("updatedate")
    @ColumnInfo(name = "updatedate")
    private String updatedate;

    @SerializedName("country_name")
    @ColumnInfo(name = "country_name")
    private String countryName;

    @SerializedName("state_name")
    @ColumnInfo(name = "state_name")
    private String stateName;

    @SerializedName("city_name")
    @ColumnInfo(name = "city_name")
    private String cityName;

    protected User(Parcel in) {
        customerId = in.readInt();
        firstname = in.readString();
        lastname = in.readString();
        emailid = in.readString();
        mobilenumber = in.readString();
        countryCode = in.readString();
        profile = in.readString();
        password = in.readString();
        countryId = in.readString();
        stateId = in.readString();
        cityId = in.readString();
        zipcode = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        streetAddress = in.readString();
        fcmid = in.readString();
        status = in.readString();
        createdate = in.readString();
        updatedate = in.readString();
        countryName = in.readString();
        stateName = in.readString();
        cityName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public int getCustomerId() {
        return customerId;
    }

    @Override
    public int getUserCustomerId() {
        return this.customerId;
    }

    @Override
    public String getUserFirstname() {
        return this.firstname;
    }

    @Override
    public String getUserLastname() {
        return this.lastname;
    }

    @Override
    public String getUserEmailid() {
        return this.emailid;
    }

    @Override
    public String getUserMobilenumber() {
        return this.mobilenumber;
    }

    @Override
    public String getUserCountryCode() {
        return this.countryCode;
    }

    @Override
    public String getUserProfile() {
        return this.profile;
    }

    @Override
    public String getUserPassword() {
        return this.password;
    }

    @Override
    public String getUserCountryId() {
        return this.countryId;
    }

    @Override
    public String getUserStateId() {
        return this.stateId;
    }

    @Override
    public String getUserCityId() {
        return this.cityId;
    }

    @Override
    public String getUserZipcode() {
        return this.zipcode;
    }

    @Override
    public String getUserLatitude() {
        return this.latitude;
    }

    @Override
    public String getUserLongitude() {
        return this.longitude;
    }

    @Override
    public String getUserStreetAddress() {
        return this.streetAddress;
    }

    @Override
    public String getUserFcmid() {
        return this.fcmid;
    }

    @Override
    public String getUserStatus() {
        return this.status;
    }

    @Override
    public String getUserCreatedate() {
        return this.createdate;
    }

    @Override
    public String getUserUpdatedate() {
        return this.updatedate;
    }

    @Override
    public String getUserCountryName() {
        return this.countryName;
    }

    @Override
    public String getUserStateName() {
        return this.stateName;
    }

    @Override
    public String getUserCityName() {
        return this.cityName;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getFcmid() {
        return fcmid;
    }

    public void setFcmid(String fcmid) {
        this.fcmid = fcmid;
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

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(customerId);
        parcel.writeString(firstname);
        parcel.writeString(lastname);
        parcel.writeString(mobilenumber);
        parcel.writeString(emailid);
        parcel.writeString(countryCode);
        parcel.writeString(profile);
        parcel.writeString(password);
        parcel.writeString(countryId);
        parcel.writeString(stateId);
        parcel.writeString(cityId);
        parcel.writeString(zipcode);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(streetAddress);
        parcel.writeString(fcmid);
        parcel.writeString(status);
        parcel.writeString(createdate);
        parcel.writeString(updatedate);
        parcel.writeString(countryName);
        parcel.writeString(stateName);
        parcel.writeString(cityName);
    }

}
