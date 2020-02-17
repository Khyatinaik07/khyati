package activity.home.homepagetab;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fragment.BookingTypeActivity;
import fragment.homepage.HomePageFragment;
import fragment.myprofile.MyProfileFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private Context context;
    int count;

    public TabAdapter(Context context,@NonNull FragmentManager fm,int count) {
        super(fm);
        this.context=context;
        this.count=count;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomePageFragment homeFragment = new HomePageFragment(context);
                return homeFragment;

            case 1:
                BookingTypeActivity homeFragment2 = new BookingTypeActivity(context);
                return homeFragment2;

            case 2:
                MyProfileFragment homeFragment3 = new MyProfileFragment(context);
                return homeFragment3;

            case 3:
                HomePageFragment homeFragment4 = new HomePageFragment(context);
                return homeFragment4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
