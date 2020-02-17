package viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import classes.NewLeadClass;

public class NewLeadViewModel extends BaseObservable {

    private NewLeadClass n;

    public NewLeadViewModel(NewLeadClass n)
    {
        this.n=n;
    }

    @Bindable
    public String getName()
    {
        return n.getName();
    }

    @Bindable
    public String getService() { return n.getService();}

    @Bindable
    public String getDate()
    {
        return n.getDate();
    }

    @Bindable
    public String getTime()
    {
        return n.getTime();
    }

    @Bindable
    public String getStatus()
    {
        return n.getStatus();
    }

}