package activity.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.ActivityLoginBinding;

import activity.basic.BaseApp;
import activity.home.HomePageTabActivity;

public class ActivityLogin extends BaseApp {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().hide();
        registration();
        check();
        click();
        clickfp();

    }

    private void registration()
    {
        binding.login.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityRegistration.class);
                startActivity(i);
            }
        });
    }

    private void click()
    {
        binding.login.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.login.uname2.getText().toString().equals("") || binding.login.pass2.getText().toString().equals(""))
                {
                    binding.login.uname.setError("Require");
                    binding.login.pass.setError("Require");
                }
                else
                {
                    binding.login.uname.setError(null);
                    binding.login.pass.setError(null);
                    Intent i = new Intent(getApplicationContext(), HomePageTabActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void clickfp()
    {
        binding.login.fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }

    private void check()
    {
        binding.login.uname2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = binding.login.uname2.getEditableText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!email.matches(emailPattern)) {
                    binding.login.uname.setError("Email format not valid");
                } else
                    binding.login.uname.setError(null);
            }
        });

        binding.login.pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    binding.login.pass.setError("Required");
                } else
                    binding.login.pass.setError(null);
            }
        });
    }
}