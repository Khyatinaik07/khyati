package data.remote;

import com.example.onlineserviceportal.BuildConfig;

public final class ApiEndPoints {

    private static final String BASE_URL_V1 = BuildConfig.BASE_URL + "";
    public static final String ENDPOINT_SIGN_UP = BuildConfig.BASE_URL + "Register";
    public static final String ENDPOINT_CHANGE_PASSWORD = BASE_URL_V1 + "ChangePassword";
    public static final String ENDPOINT_LOGIN = BASE_URL_V1 + "Login";
    public static final String ENDPOINT_EDIT_PROFILE = BASE_URL_V1 + "updateProfile";
    public static final String ENDPOINT_BANNER_DATA = BASE_URL_V1 + "getServicesBanner";
    public static final String ENDPONT_SERVICE_DATA = BASE_URL_V1 + "getServices";
    public static final String ENDPOINT_SERVICE_PACKAGE_DATA =  BASE_URL_V1 + "getServicePackages";

    //image base url
    public static final String IMAGE_BASE_URL = "http://brss.in/jigar/serviceprovider/assets/General/images/";
}
