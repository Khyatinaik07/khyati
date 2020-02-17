package activity.authentication.otpverify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityVerifyOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import activity.home.changePassword.ChangePasswordActivity;
import activity.home.homepagetab.HomePageTabActivity;
import utils.AppConstants;

public class VerifyOTPActivity extends BaseActivity<ActivityVerifyOtpBinding, VerifyOTPViewModel> implements View.OnClickListener, VerifyOTPNevigator {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    ActivityVerifyOtpBinding binding;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    String phoneNumber, otp, code;
    private String verificationCode;
    FirebaseAuth auth;
    private int FROM_SCREEN = 0;
    private boolean isFromForgotPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StartFirebaseLogin();

        FROM_SCREEN = getIntent().getIntExtra(AppConstants.Extras.OTPScreen_type,0);
        if (FROM_SCREEN == AppConstants.OTPScreenType.FORGOTPASSWORD)
        {
            isFromForgotPass =true;
        }

        phoneNumber = getIntent().getStringExtra(AppConstants.Extras.PHONE_OR_EMAIL);
        code = getIntent().getStringExtra(AppConstants.Extras.COUNTRY_CODE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                code + phoneNumber,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                VerifyOTPActivity.this,        // Activity (for callback binding)
                mCallback);// OnVerificationStateChangedCallbacks
        binding.otp.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = binding.otp.otp2.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                SigninWithPhone(credential);
            }
        });
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (FROM_SCREEN == AppConstants.OTPScreenType.FORGOTPASSWORD)
                            {
                                startActivity(new Intent(VerifyOTPActivity.this, ChangePasswordActivity.class));
                            }
                            else {
                                Intent i = new Intent(getApplicationContext(),HomePageTabActivity.class);
                                finish();
                                startActivity(i);
                            }

                        } else {
                            Toast.makeText(VerifyOTPActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(VerifyOTPActivity.this, "verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(VerifyOTPActivity.this, "verification fialed", Toast.LENGTH_SHORT).show();
                Log.e("error", e.toString());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(VerifyOTPActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public VerifyOTPViewModel getViewModel() {
        return new ViewModelProvider(this, mViewModelFactory).get(VerifyOTPViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_verify_otp;
    }

    @Override
    public int getBindingVariable() {
        return com.example.onlineserviceportal.BR.verifyOTPViewModel;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
}
