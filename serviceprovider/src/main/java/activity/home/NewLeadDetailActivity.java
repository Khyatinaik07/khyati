package activity.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.ActivityNewLeadDetailBinding;

import java.io.Serializable;
import java.util.ArrayList;

import activity.basic.BaseApp;
import classes.NewLeadDetailClass;

public class NewLeadDetailActivity extends BaseApp {

    ActivityNewLeadDetailBinding binding;
    ArrayList<NewLeadDetailClass> list = new ArrayList<>();
    NewLeadDetailClass newLeadDetailClass;
    Serializable i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_new_lead_detail);
        setSupportActionBar(binding.toolbar);

        i = getIntent().getSerializableExtra("status");
        setData();

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    private void setData()
    {
        binding.detail.name.setText("Jash");
        binding.detail.price.setText("\u20B9 699");
        binding.detail.status.setText(i.toString());
        binding.detail.date.setText("6 Nov 2019");
        binding.detail.time.setText("9AM - 11AM");
        binding.detail.pname.setText("Jash Desai");
        binding.detail.address.setText("102, Akshat Complex, Near L.G show room, Mithakhadi Six road, Navrangpura, Ahmedabad");
        binding.detail.personid.setText("1001");
        binding.detail.mincharges.setText("200");
    }
}