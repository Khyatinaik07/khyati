package activity.home.allsubservice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import data.model.api.homepage.ServiceData;
import fragment.subtypeallservice.SubTypeAllServiceFragment;

public class AllServiceAdapter extends FragmentStateAdapter {


    private List<ServiceData> serviceData;
    public String id;

    public AllServiceAdapter ( FragmentManager fm, Lifecycle lifecycle,String id){
        super(fm,lifecycle);
        this.id=id;
    }

   /* @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SubTypeAllServiceFragment homeFragment = new SubTypeAllServiceFragment(myContext);
                return homeFragment;
            case 1:
                SubTypeAllServiceFragment homeFragment2 = new SubTypeAllServiceFragment(myContext);
                return homeFragment2;
            case 2:
                SubTypeAllServiceFragment homeFragment3 = new SubTypeAllServiceFragment(myContext);
                return homeFragment3;
            case 3:
                SubTypeAllServiceFragment homeFragment4 = new SubTypeAllServiceFragment(myContext);
                return homeFragment4;
            case 4:
                SubTypeAllServiceFragment homeFragment5 = new SubTypeAllServiceFragment(myContext);
                return homeFragment5;
            case 5:
                SubTypeAllServiceFragment homeFragment6 = new SubTypeAllServiceFragment(myContext);
                return homeFragment6;
            default:
                return null;
        }
    }  */

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String name = serviceData.get(position).getName();
        String image = serviceData.get(position).getIcon();
        String id= String.valueOf(serviceData.get(position).getServiceId());

        return SubTypeAllServiceFragment.getInstance(name,image,id);
    }

    @Override
    public int getItemCount() {
        return serviceData != null ? serviceData.size() : 0;
    }

    void setSessionData(List<ServiceData> serviceData) {
        this.serviceData = serviceData;
        notifyDataSetChanged();
    }
}
