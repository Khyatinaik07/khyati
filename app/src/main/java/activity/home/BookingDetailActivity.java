package activity.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityBookingDetailBinding;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.shreyaspatil.MaterialDialog.interfaces.OnCancelListener;
import com.shreyaspatil.MaterialDialog.interfaces.OnDismissListener;
import com.shreyaspatil.MaterialDialog.interfaces.OnShowListener;
import com.stepstone.apprating.AppRatingDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import classes.BookingDetailClass;

public class BookingDetailActivity extends AppCompatActivity implements View.OnClickListener, OnShowListener, OnCancelListener, OnDismissListener {

    ActivityBookingDetailBinding binding;
    ArrayList<BookingDetailClass> list = new ArrayList<>();
    BookingDetailClass bookingDetailClass;
    Serializable i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_detail);
        setSupportActionBar(binding.toolbar);

        i = getIntent().getSerializableExtra("status");
        if (i.toString().equalsIgnoreCase("pending"))
        {
            binding.detail.cancel.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.detail.cancel.setVisibility(View.GONE);
        }
        if (i.toString().equalsIgnoreCase("completed"))
        {
            binding.detail.rate.setVisibility(View.VISIBLE);
        }
        else
            binding.detail.rate.setVisibility(View.GONE);

        click();
        cancleClick();
        setDate();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("Booking Detail");

    }

    private void click()
    {
        binding.detail.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AppRatingDialog.Builder()
                        .setPositiveButtonText("Submit")
                        .setNegativeButtonText("Cancel")
                        .setNeutralButtonText("Later")
                        .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                        .setTitle("Rate Our Service")
                        .setDescription("Please select some stars and give your feedback")
                        .setCommentInputEnabled(true)
                        .setHint("Please give your feedback here...")
                        .setHintTextColor(R.color.black)
                        .setCommentTextColor(R.color.black)
                        .setCommentBackgroundColor(R.color.lightgrey)
                        .setDefaultRating(0)
                        .setWindowAnimation(R.style.MyDialogFadeAnimation)
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .create(BookingDetailActivity.this)
                        .show();
            }
        });
    }

    private void cancleClick()
    {
        binding.detail.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

    }

    private void showAlert()
    {
        BottomSheetMaterialDialog mBottomSheetDialog = new BottomSheetMaterialDialog.Builder(BookingDetailActivity.this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete this file?")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.delete, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", R.drawable.close, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .build();

        // Show Dialog
        mBottomSheetDialog.show();

    }

    private void setDate()
    {
        /*bookingDetailClass.setName("Khyati Naik");
        bookingDetailClass.setDate("23 Oct 2019");
        bookingDetailClass.setTime("9AM - 11AM");
        bookingDetailClass.setService("Home Paint");
        bookingDetailClass.setSname("Jhon");
        bookingDetailClass.setAddress("Akshat, Mithakhadi six road, Navrangpura");
        bookingDetailClass.setPrice("\u20B9 699");  */

        binding.detail.txtDate.setText("23 Oct 2019");
        binding.detail.txtStatus.setText(i.toString());
        binding.detail.orderid.setText("#123654897458A");
        binding.detail.address.setText("102, Akshat Complex, Near Mithakhadi Road, Navrangpura, Ahmedabad, Gujarat");
        binding.detail.service.setText("Event");
        binding.detail.work.setText("Party Decoration");
        binding.detail.subtotal.setText("\u20B9 699");
        binding.detail.extra.setText("\u20B9 0");
        binding.detail.total.setText("\u20B9 699");
        binding.detail.time.setText("9AM - 11AM");
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

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

    }
}