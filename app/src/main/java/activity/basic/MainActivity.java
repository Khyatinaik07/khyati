package activity.basic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityMainBinding;

import javax.inject.Inject;

import activity.authentication.login.LoginActivity;
import activity.home.homepagetab.HomePageTabActivity;
import dagger.android.AndroidInjection;
import data.DataManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected DataManager dataManager;
    private boolean isUserLoggedIn = false;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);
        AndroidInjection.inject(this);
        getSupportActionBar().hide();

        isUserLoggedIn = !dataManager.getUserID().toString().isEmpty();
        Log.e("user id",dataManager.getUserID().toString());

        if (!isUserLoggedIn)
        {
            Disposable disposable =dataManager.clearAllData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Exception {
                            Log.e("database", "All data clear");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("error","App version clear failed",throwable);
                        }
                    });
            compositeDisposable.add(disposable);
        }
        else
        {
            Log.e("navigate","navigate to other screen");
            if (dataManager.getUserID() == 0) {
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                startActivity(new Intent(this, HomePageTabActivity.class));
            }

            // Close splash
            finish();
        }

      /*  Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        },2000);  */

        binding.main.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
