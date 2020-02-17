package activity.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.ActivityRegistrationBinding;

import activity.basic.BaseApp;

public class ActivityRegistration extends BaseApp {

    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        click();
        check();
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

    private void click()
    {
        binding.reg.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.reg.fname2.getText().toString().equals("") ||
                        binding.reg.lname2.getText().toString().equals("") || binding.reg.email2.getText().toString().equals("") ||
                        binding.reg.pass2.getText().toString().equals("") || binding.reg.cpass2.getText().toString().equals("") ||
                        binding.reg.mobile2.getText().toString().equals(""))
                {
                    if (binding.reg.fname2.getText().toString().equals(""))
                    {
                        binding.reg.fname.setError("Require");
                    }
                    if (binding.reg.lname2.getText().toString().equals(""))
                    {
                        binding.reg.lname.setError("Require");
                    }
                    if (binding.reg.email2.getText().toString().equals(""))
                    {
                        binding.reg.email.setError("Require");
                    }
                    if (binding.reg.pass2.getText().toString().equals(""))
                    {
                        binding.reg.pass.setError("Require");
                    }
                    if (binding.reg.cpass2.getText().toString().equals(""))
                    {
                        binding.reg.cpass.setError("Require");
                    }
                    if (binding.reg.mobile2.getText().toString().equals(""))
                    {
                        binding.reg.mobile.setError("Require");
                    }
                }
                else
                {
                    binding.reg.fname.setError(null);
                    binding.reg.lname.setError(null);
                    binding.reg.email.setError(null);
                    binding.reg.pass.setError(null);
                    binding.reg.cpass.setError(null);
                    binding.reg.mobile.setError(null);
                    Intent i = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    public void check() {
        binding.reg.fname2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    binding.reg.fname.setError("Required");
                } else
                    binding.reg.fname.setError(null);
            }
        });

        binding.reg.lname2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    binding.reg.lname.setError("Required");
                } else
                    binding.reg.lname.setError(null);
            }
        });

        binding.reg.email2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = binding.reg.email2.getEditableText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!email.matches(emailPattern)) {
                    binding.reg.email.setError("Email format not valid");
                } else
                    binding.reg.email.setError(null);
            }
        });

        binding.reg.pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.reg.pass2.getText().toString().equals("")) {
                    binding.reg.pass.setError("required");
                } else
                    binding.reg.pass.setError(null);
            }
        });

        binding.reg.cpass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pass = binding.reg.pass2.getText().toString();
                if (!pass.equals(binding.reg.cpass2.getText().toString())) {
                    binding.reg.cpass.setError("Pasword not matches");
                } else
                    binding.reg.cpass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.reg.cpass2.getText().toString().equals("")) {
                    binding.reg.cpass.setError("required");
                }
            }
        });

        binding.reg.mobile2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!(charSequence.length() == 10)) {
                    binding.reg.mobile.setError("Mobile number must have 10 number");
                } else
                    binding.reg.mobile.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

}