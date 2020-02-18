package data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import data.local.db.dao.ServiceProviderDao;
import data.model.api.homepage.BannerData;
import data.model.api.homepage.ServiceData;
import data.model.api.login.User;
import data.model.api.servicepackage.ServiceResult;

@Database(entities = {
        User.class,
        BannerData.class,
        ServiceData.class,
        ServiceResult.class
},version = 10,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ServiceProviderDao serviceProviderDao();


}
