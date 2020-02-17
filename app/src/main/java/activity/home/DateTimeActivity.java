package activity.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityDateTimeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import adapter.activity.DateAdapter;
import adapter.activity.TimeAdapter;

public class DateTimeActivity extends AppCompatActivity {

    ActivityDateTimeBinding binding;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();
    DateAdapter adapter;
    TimeAdapter timeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_date_time);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Date And Time");

        getDate();
        setAdapter();
    }

    private void getDate()
    {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_WEEK,0);
        Date t = cal.getTime();
        String s = String.valueOf(t);
        String[] f = s.split(" ");
        cal.add(Calendar.DAY_OF_WEEK,1);
        Date t2 = cal.getTime();
        String s2 = String.valueOf(t2);
        String[] f2 = s2.split(" ");
        cal.add(Calendar.DAY_OF_WEEK,1);
        Date t3 = cal.getTime();
        String s3 = String.valueOf(t3);
        String[] f3 = s3.split(" ");
        cal.add(Calendar.DAY_OF_WEEK,1);
        Date t4 = cal.getTime();
        String s4 = String.valueOf(t4);
        String[] f4 = s4.split(" ");
        cal.add(Calendar.DAY_OF_WEEK,1);
        Date t5 = cal.getTime();
        String s5 = String.valueOf(t5);
        String[] f5 = s5.split(" ");
        cal.add(Calendar.DAY_OF_WEEK,1);
        Date t6 = cal.getTime();
        String s6 = String.valueOf(t6);
        String[] f6 = s6.split(" ");
        cal.add(Calendar.DAY_OF_WEEK,1);
        Date t7 = cal.getTime();
        String s7 = String.valueOf(t7);
        String[] f7 = s7.split(" ");

        list.add(f[2]);
        list.add(f2[2]);
        list.add(f3[2]);
        list.add(f4[2]);
        list.add(f5[2]);
        list.add(f6[2]);
        list.add(f7[2]);

        list2.add(f[0]);
        list2.add(f2[0]);
        list2.add(f3[0]);
        list2.add(f4[0]);
        list2.add(f5[0]);
        list2.add(f6[0]);
        list2.add(f7[0]);

        list3.add("9AM - 11AM");
        list3.add("11AM - 1PM");
        list3.add("1PM - 3PM");
        list3.add("3PM - 5PM");
        list3.add("5PM - 7PM");
        list3.add("7PM - 9PM");
    }

    private void setAdapter()
    {
        binding.dt.rv.setLayoutManager(new GridLayoutManager(this,7));
        adapter = new DateAdapter(this,list,list2);
        binding.dt.rv.setAdapter(adapter);

        binding.dt.rvtime.setLayoutManager(new GridLayoutManager(this,2));
        timeAdapter = new TimeAdapter(this,list3);
        binding.dt.rvtime.setAdapter(timeAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
