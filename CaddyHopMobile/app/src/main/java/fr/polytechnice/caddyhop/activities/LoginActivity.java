package fr.polytechnice.caddyhop.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.polytechnice.caddyhop.CaddyHop;
import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.data.DataManager;
import fr.polytechnice.caddyhop.models.User;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shafiq on 07/06/16.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_login_input) EditText loginInput;
    @BindView(R.id.login_password_input) EditText passwordInput;

    @Inject DataManager dataManager;

    private LoginActivity loginActivity;

    private final String TAG = this.getClass().getSimpleName();

    @OnClick(R.id.login_button) void loginClick() {
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(!login.equals("") && !password.equals("")) {
            final Intent intent = new Intent(this, MainActivity.class);

            dataManager.loginAuth(login, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<Long>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(loginActivity, "Login failed. Please try again", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(final Long aLong) {

                    Log.d(TAG, "Reponse login : " + aLong);

                    if(aLong == -1) {
                        Toast.makeText(loginActivity, "Login failed. Please check login and password", Toast.LENGTH_SHORT).show();
                    } else {
                        dataManager.getUser(aLong).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<User>() {
                            @Override
                            public void onNext(User user) {
                                Log.d(TAG, "User response : " + user);
                                //user.setId(aLong);

                                intent.putExtra("user", user);
                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(loginActivity);
                                prefs.edit().putBoolean("isLoggedIn", true).apply();
                                Gson gson = new Gson();
                                String json = gson.toJson(user);
                                prefs.edit().putString("user", json).apply();
                                dataManager.setUser(user);
                                startActivity(intent);
                            }

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                final Intent intent = new Intent(loginActivity, LoginActivity.class);
                                startActivity(intent);

                            }
                        });

                    }
                }
            });

        } else {
            Toast.makeText(this, "Please enter login and password", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.create_account_button) void clickCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((CaddyHop)getApplication()).getApplicationComponent().inject(this);

        Log.d(TAG, "onCreate: Data Manager: " + dataManager);
        loginActivity = this;
    }

}
