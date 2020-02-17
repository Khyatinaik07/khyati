package activity.home.allsubservice;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;

import java.util.List;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.homepage.ServiceData;
import utils.rx.SchedulerProvider;

public class SubServiceAllViewModel extends BaseViewModel<SubServiceAllNavigator> {

    ObservableBoolean isDataEmpty = new ObservableBoolean();

    public SubServiceAllViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    LiveData<List<ServiceData>> getAllServiceNameLive(int order,String id) {
        return getDataManager().getAllServiceNameLive(order,id);
    }

    void processAllServiceName(List<ServiceData> serviceData,String id)
    {
        getNavigator().onServiceNameAvailable(serviceData,id);
    }

    public ObservableBoolean getIsDataEmpty()
    {
        return isDataEmpty;
    }

    public void setIsDataEmpty(Boolean isEmpty) {
        this.isDataEmpty.set(isEmpty);
    }
}
