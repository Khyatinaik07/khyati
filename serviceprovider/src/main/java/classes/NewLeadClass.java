package classes;

import androidx.annotation.Nullable;

public class NewLeadClass {

    String name,service,time,date,status;

    public NewLeadClass() {
    }

    public void setName(String name)
    {
        this.name=name;
    }
    @Nullable
    public String getName()
    {
        return name;
    }

    public void setService(@Nullable String service)
    {
        this.service=service;
    }
    @Nullable
    public String getService()
    {
        return service;
    }

    public void setTime(@Nullable String time)
    {
        this.time=time;
    }
    @Nullable
    public String getTime()
    {
        return time;
    }

    public void setDate(@Nullable String date)
    {
        this.date=date;
    }
    @Nullable
    public String getDate()
    {
        return date;
    }

    public void setStatus(@Nullable String status)
    {
        this.status=status;
    }
    @Nullable
    public String getStatus()
    {
        return status;
    }

}
