package utils;

public final class AppConstants {

    public static final int PASSWORD_LENGTH = 6;
    public static final String PREF_NAME = "onlineservice_pref";
    public static final String DB_NAME = "onlineService.db";
    public static final String ATTENDEE_FIREBASE_TOPIC = "eventraft_attendee_%d";
    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String ID = "id";


    public @interface StatusCodes {
        int SUCCESS = 200;
        int CREATED = 201;
        int CONFLIT = 409;
        int AUTHORIZATION_FAILED = 401;
        int FORBIDDEN = 403;
        Boolean STATUS_FALSE = false;
        Boolean STATUS_TRUE = true;
    }

    public @interface Extras {
        public static final String PHONE_OR_EMAIL = "extra_phone_or_email";
        public static final String COUNTRY_CODE = "extra_country_code";
        public static final String OTPScreen_type="extra_from_otp_screen";
    }

    public interface OTPScreenType
    {
        int FORGOTPASSWORD = 1;
    }

    public interface ScreenName
    {
        String MY_PROFILE = "my_profile";
        String HOME_PAGE = "home_page";
        String SUB_TYPE_SERVICE = "sub_type_service";
    }
}
