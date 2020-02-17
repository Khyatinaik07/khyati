package data.model.api.signUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("emailid")
    @Expose
    private String emailid;

    @SerializedName("mobilenumber")
    @Expose
    private String mobilenumber;

    @SerializedName("country_code")
    @Expose
    private String countryCode;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("fcmid")
    @Expose
    private String fcmid;

//    private boolean isForgotPassword = false;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcmid() {
        return fcmid;
    }

    public void setFcmid(String fcmid) {
        this.fcmid = fcmid;
    }

  /*  public boolean isForgotPassword() {
        return isForgotPassword;
    }

    public RegisterRequest setForgotPassword(boolean forgotPassword) {
        isForgotPassword = forgotPassword;
        return this;
    }*/

}