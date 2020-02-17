package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.ActivityMyProfileFragmentBinding;
import com.example.serviceprovider.databinding.DialogSelectImageBinding;

import activity.home.HomePageTabActivity;
import activity.profile.ActivityAboutMe;
import activity.profile.ActivityBankDetail;
import activity.profile.ActivityIdentityProof;
import activity.profile.ActivityServiceLocation;

public class MyProfileFragment extends Fragment implements View.OnClickListener {

    ActivityMyProfileFragmentBinding binding;
    Context context;
    protected int GALLERY = 1;
    final int MY_CAMERA_REQUEST_CODE = 20;

    public MyProfileFragment(Context context)
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (HomePageTabActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_my_profile_fragment,container,false);
        setListener();
        setData();
        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.uploadImage:
                openImageSelectDialog();
                break;

            case R.id.identity:
                Intent i = new Intent(context, ActivityIdentityProof.class);
                context.startActivity(i);
                break;

            case R.id.aboutme:
                Intent i2 = new Intent(context, ActivityAboutMe.class);
                context.startActivity(i2);
                break;

            case R.id.location:
                Intent i3 = new Intent(context, ActivityServiceLocation.class);
                context.startActivity(i3);
                break;

            case R.id.details:
                Intent i4 = new Intent(context, ActivityBankDetail.class);
                context.startActivity(i4);
                break;
        }
    }

    private void setData()
    {
        binding.profile.name.setText("Khyati Naik");
        binding.profile.address.setText("102, Akshat Complex, Mithakhadi Six Road, Ahmedabad");
        binding.profile.email.setText("khyati@gmail.com");
        binding.profile.mobile.setText("91-5896985748");
        binding.profile.service.setText("Repairing");
    }

    //TODO Image
    private AlertDialog alertImageDialog = null;

    private void openImageSelectDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final DialogSelectImageBinding alertLayout = DataBindingUtil.inflate(inflater, R.layout.dialog_select_image, null, false);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setView(alertLayout.getRoot());
        builder1.setCancelable(true);

        alertLayout.llClickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromcamera();
                alertImageDialog.dismiss();
            }
        });

        alertLayout.llUseGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromgallery();
                alertImageDialog.dismiss();
            }
        });

        alertImageDialog = builder1.create();
        alertImageDialog.show();
    }

    private void fromgallery() {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, GALLERY);
    }

    private void fromcamera() {
        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoCaptureIntent, MY_CAMERA_REQUEST_CODE);
    }

    private void setListener()
    {
        binding.profile.uploadImage.setOnClickListener(this);
        binding.profile.identity.setOnClickListener(this);
        binding.profile.aboutme.setOnClickListener(this);
        binding.profile.location.setOnClickListener(this);
        binding.profile.details.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                Uri select = data.getData();
                Log.i("gallery path",select.getPath());
                binding.profile.uploadImage.setImageURI(select);

            }
            else if (requestCode == MY_CAMERA_REQUEST_CODE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                binding.profile.uploadImage.setImageBitmap(photo);

            }
        }
    }
}