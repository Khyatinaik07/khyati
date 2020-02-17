package adapter.activity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fragment.BookingTypeActivity;
import fragment.BookingHistoryFragment;
import fragment.OngoingBookingFragment;

public class BookingTypeAdapter extends FragmentPagerAdapter {

    Context context;
    int tabCount;

    public BookingTypeAdapter(BookingTypeActivity bookingTypeActivity, @NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                OngoingBookingFragment f1 = new OngoingBookingFragment(context);
                return f1;

            case 1:
                BookingHistoryFragment f2 = new BookingHistoryFragment(context);
                return f2;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
