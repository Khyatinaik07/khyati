package activity.basic;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import data.DataManager;
import io.reactivex.disposables.CompositeDisposable;
import utils.rx.SchedulerProvider;

public abstract class BaseViewModel<N extends BaseNevigator> extends ViewModel {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;
    private final int mBackgroundColor;
    private final int mEventColor;
    private WeakReference<N> mNavigator;
    private ObservableBoolean isSearchVisible = new ObservableBoolean(false);
    private final ObservableBoolean isEmpty = new ObservableBoolean(false);

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mBackgroundColor = getDataManager().getBackgroundColor();
        this.mEventColor = getDataManager().getEventColor();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getEventColor() {
        return mEventColor;
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void setIsSearchVisible(boolean isSearchVisible) {
        this.isSearchVisible.set(isSearchVisible);
    }

    public ObservableBoolean getIsSearchVisible() {
        return isSearchVisible;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty.set(isEmpty);
    }

}
