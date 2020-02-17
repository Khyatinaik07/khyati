package data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import data.model.api.homepage.BannerData;
import data.model.api.homepage.ServiceData;
import data.model.api.login.User;

@Dao
public interface ServiceProviderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCustomerProfile(User customerProfile);

    @Query("SELECT * FROM table_user")
    LiveData<User> getAttendeeProfileLive();

    @Query("SELECT * FROM table_user where customer_id = :userid")
    LiveData<User> getUserProfileLive(int userid);

    @Query("SELECT * FROM BannerData")
    LiveData<List<BannerData>> getBaneerData();

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void saveBannerData(List<BannerData> bannerData);

    @Query("SELECT * FROM ServiceData WHERE parentId = :id ORDER BY CASE WHEN :orderByType = 0 THEN sortOrderGrid END ASC, CASE WHEN :orderByType = 1 THEN sortOrderList END ASC")
    LiveData<List<ServiceData>> getServiceLive(int orderByType,String id);

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void saveServiceData(List<ServiceData> serviceData);

    @Query("SELECT * FROM ServiceData WHERE parentId = :id ORDER BY CASE WHEN :order = 0 THEN sortOrderGrid END ASC")
    LiveData<List<ServiceData>> getAllServiceNameLive(int order,String id);
}