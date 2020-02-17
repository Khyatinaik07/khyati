package activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityMembershipBinding;

import java.util.ArrayList;

import adapter.activity.MembershipAdapter;
import classes.MembershipClass;

public class MembershipActivity extends AppCompatActivity {

    ActivityMembershipBinding binding;
    ArrayList<MembershipClass> list = new ArrayList<>();
    MembershipClass membershipClass;
    MembershipAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_membership);
        setSupportActionBar(binding.toolbar);

        onclick();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Membership");

        setAdapter();
        setData();

    }

    private void onclick()
    {
        binding.member.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getBoolean())
                {
                    Intent i = new Intent(getApplicationContext(), LocationActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"select one package",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(this);
        binding.member.rv.setLayoutManager(l);
        adapter = new MembershipAdapter(this,list);
        binding.member.rv.setAdapter(adapter);
    }

    private void setData()
    {
        membershipClass = new MembershipClass();
        membershipClass.setTitle("One Time Membership");
        list.add(membershipClass);

        membershipClass = new MembershipClass();
        membershipClass.setTitle("3 Month Membership");
        membershipClass.setRs("\u20B9 600");
        membershipClass.setDes("In this membership you have to pay one time then you can get free service for 3 month.");
        list.add(membershipClass);

        membershipClass = new MembershipClass();
        membershipClass.setTitle("6 Month Membership");
        membershipClass.setRs("\u20B9 1200");
        membershipClass.setDes("In this membership you have to pay one time then you can get free service for 6 month.");
        list.add(membershipClass);

        membershipClass = new MembershipClass();
        membershipClass.setTitle("1 Year Membership");
        membershipClass.setRs("\u20B9 2000");
        membershipClass.setDes("In this membership you have to pay one time then you can get free service for 1 year.");
        list.add(membershipClass);

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
