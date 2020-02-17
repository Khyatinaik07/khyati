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
import com.example.onlineserviceportal.databinding.ActivityServicePackageBinding;

import java.io.Serializable;
import java.util.ArrayList;

import adapter.activity.ServicePackageAdapter;
import classes.ServicePackageClass;

public class ServicePackageActivity extends AppCompatActivity {

    ActivityServicePackageBinding binding;
    ArrayList<ServicePackageClass> list = new ArrayList<>();
    ServicePackageClass servicePackageClass;
    ServicePackageAdapter adapter;
    Serializable i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_service_package);
        setSupportActionBar(binding.toolbar);

        onClick();

        i = getIntent().getSerializableExtra("name");
        getSupportActionBar().setTitle(i.toString());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (i.toString().equalsIgnoreCase("makeup and hairstyle both"))
        {
            setData();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("only hairstyle"))
        {
            setHairstyle();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("only makeup")){
            setMakeup();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("birthday party"))
        {
            setBirthday();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("anniversary party"))
        {
            setBirthday();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("house party"))
        {
            setBirthday();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("basic decoration"))
        {
            setDecoration();
            setAdapter();
        }
        else if (i.toString().equalsIgnoreCase("premium decoration"))
        {
            setDecoration();
            setAdapter();
        }
        else if(i.toString().equalsIgnoreCase("only game coordinator"))
        {
            setOnlyGame();
            setAdapter();
        }
    }

    private void onClick()
    {
        binding.service.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (adapter.getBoolean())
                    {
                        Intent i = new Intent(getApplicationContext(), MembershipActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"select one package",Toast.LENGTH_LONG).show();
                    }
            }
        });
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

    private void setOnlyGame()
    {
        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Only Game Coordinator");
        servicePackageClass.setRs("\t\t\t \u20B9 1599");
        servicePackageClass.setContent("Coordinators will be provided");
        servicePackageClass.setExtra1("no decoration included");
        servicePackageClass.setExtra2("All Game and it's things provided by coordinator");
        list.add(servicePackageClass);
    }

    private void setBirthday()
    {
        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Budget Photographer");
        servicePackageClass.setRs("\t\t\t \u20B9 2999");
        servicePackageClass.setContent("Only Digital Photos");
        servicePackageClass.setExtra1("Number Of Click - Unlimited");
        servicePackageClass.setExtra2("Duration 3 hours");
        list.add(servicePackageClass);

        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Standard Photographer");
        servicePackageClass.setRs("\t\t\t \u20B9 4999");
        servicePackageClass.setContent("Number Of Click - Unlimited");
        servicePackageClass.setExtra1("Edited photos - 60");
        servicePackageClass.setExtra2("Duration 3 hours");
        list.add(servicePackageClass);
    }

    private void setDecoration()
    {
        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Basic Decoration");
        servicePackageClass.setRs("\t\t\t \u20B9 1499");
        servicePackageClass.setContent("200 balloons with ribbons");
        servicePackageClass.setExtra1("1 strip banner");
        servicePackageClass.setExtra2("Only Balloons Decoration");
        list.add(servicePackageClass);

        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Romantic Decoration");
        servicePackageClass.setRs("\t\t\t \u20B9 1799");
        servicePackageClass.setContent("100 normal balloons and 100 heart shape balloons");
        servicePackageClass.setExtra1("15 hanging picture(Hard copy provided by customer)");
        servicePackageClass.setExtra2("1 party popper");
        list.add(servicePackageClass);

        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Premium Decoration");
        servicePackageClass.setRs("\t\t\t \u20B9 2299");
        servicePackageClass.setContent("300 balloons with ribbons");
        servicePackageClass.setExtra1("1 banner and 1 big balloon with filled chocolate");
        servicePackageClass.setExtra2("1 cake table with balloon and ribbons");
        list.add(servicePackageClass);
    }

    private void setData()
    {
        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Basic Beauty Package");
        servicePackageClass.setRs("\t\t\t \u20B9 2000 per person");
        servicePackageClass.setContent("Makeup & simple Hairstyle ");
        servicePackageClass.setExtra1("Simple Professional Makeup");
        servicePackageClass.setExtra2("Basic Hairstyle : Curls,Straightening");
        list.add(servicePackageClass);

        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Basic Beauty Package");
        servicePackageClass.setRs("\t\t\t \u20B9 2000 per person");
        servicePackageClass.setContent("Makeup & simple Hairstyle ");
        servicePackageClass.setExtra1("Simple Professional Makeup");
        servicePackageClass.setExtra2("Basic Hairstyle : Curls,Straightening");
        list.add(servicePackageClass);

        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Premium Beauty Package");
        servicePackageClass.setRs("\t\t\t \u20B9 3000 per person");
        servicePackageClass.setContent("Makeup & Any Hairstyle + Complementary lashes");
        servicePackageClass.setExtra1("Standard Professional Makeup");
        servicePackageClass.setExtra2("Fancy Hairstyle : Buns,Braids");
        list.add(servicePackageClass);

        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("On-camera Beauty Package");
        servicePackageClass.setRs("\t\t\t \u20B9 5000 per person");
        servicePackageClass.setContent("HD Makeup & Any Hairstyle + Complementary lashes");
        servicePackageClass.setExtra1("HD Professional Makeup");
        servicePackageClass.setExtra2("Hairstyle as per your choice");
        list.add(servicePackageClass);
    }

    private void setHairstyle()
    {
        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Prim & Proper Package");
        servicePackageClass.setRs("\t\t\t \u20B9 1500 per person");
        servicePackageClass.setContent("Any Hairstyle ");
        servicePackageClass.setExtra1("Straightening,Curl,Buns and Braids");
        servicePackageClass.setExtra2("Free Draping");
        list.add(servicePackageClass);
    }

    private void setMakeup()
    {
        servicePackageClass = new ServicePackageClass();
        servicePackageClass.setPack("Glow Package");
        servicePackageClass.setRs("\t\t\t \u20B9 1500 per person");
        servicePackageClass.setContent("Complete Professional Makeup ");
        servicePackageClass.setExtra1("Complementary false lashes");
        servicePackageClass.setExtra2("HD & 3D (Chargeable)");
        list.add(servicePackageClass);
    }

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(this);
        binding.service.rv.setLayoutManager(l);
        adapter = new ServicePackageAdapter(this,list,i.toString());
        binding.service.rv.setAdapter(adapter);
    }
}
