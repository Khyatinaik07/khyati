package adapter.activity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fragment.MenuFragment;
import fragment.MyProfileFragment;

import fragment.NewLeadFragment;

import activity.home.HomePageTabActivity;

public class TabAdapter extends FragmentPagerAdapter {

    Context context;
    int childCount;

    public TabAdapter(HomePageTabActivity homePageTabActivity, FragmentManager supportFragmentManager, int childCount) {
        super(supportFragmentManager);
        this.context=homePageTabActivity;
        this.childCount=childCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                NewLeadFragment n = new NewLeadFragment(context);
                return n;

            case 1:
                MyProfileFragment n1 = new MyProfileFragment(context);
                return n1;

            case 2:
                MenuFragment n2 = new MenuFragment(context);
                return n2;

                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return childCount;
    }
}
