package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.ActivityMenuFragmentBinding;

import activity.home.HomePageTabActivity;
import activity.profile.ActivityBankDetail;

public class MenuFragment extends Fragment implements View.OnClickListener {

    ActivityMenuFragmentBinding binding;
    Context context;

    public MenuFragment(Context context) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=(HomePageTabActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_menu_fragment,container,false);
        click();
        return binding.getRoot();
    }

    private void click()
    {
        binding.menu.pan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.pan:
                Intent i = new Intent(context, ActivityBankDetail.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
        }
    }
}