package fr.polytechnice.caddyhop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.Response;
import com.orhanobut.wasp.Wasp;
import com.orhanobut.wasp.WaspError;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.polytechnice.caddyhop.CaddyHop;
import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.data.DataManager;
import fr.polytechnice.caddyhop.models.User;
import fr.polytechnice.caddyhop.network.CaddyHopWaspService;

/**
 * Created by shafiq on 07/06/16.
 */

public class CreateAccountActivity extends AppCompatActivity {

    @BindView(R.id.register_login_input) EditText loginInput;
    @BindView(R.id.register_password_input) EditText mdpInput;
    @BindView(R.id.register_email_input) EditText emailInput;
    @BindView(R.id.register_nom_input) EditText nomInput;
    @BindView(R.id.register_prenom_input) EditText prenomInput;
    
    CaddyHopWaspService service;

    @Inject
    DataManager dataManager;

    private User newUser;

    private CreateAccountActivity createAccountActivity;

    private final String TAG = this.getClass().getSimpleName();

    @OnClick(R.id.register_button) void register() {
        String login = loginInput.getText().toString();
        String mdp = mdpInput.getText().toString();
        String email = emailInput.getText().toString();
        String nom = nomInput.getText().toString();
        String prenom = prenomInput.getText().toString();

        // Check if all field is filled
        if(!login.equals("")
                && !mdp.equals("")
                && !email.equals("")
                && !nom.equals("")
                && !prenom.equals("")) {

            newUser = new User(login, mdp, email, nom, prenom);
            System.out.println("Created user : " + newUser);

            // TODO : migrate to RetroFit service
            service = new Wasp.Builder(createAccountActivity)
                    .setEndpoint(CaddyHopWaspService.ENDPOINT)
                    .build()
                    .create(CaddyHopWaspService.class);

            service.createUser(newUser, new Callback<Long>() {
                @Override
                public void onSuccess(Response response, Long aLong) {
                    if(aLong == -1) {
                        Toast.makeText(createAccountActivity, "Registration failed, please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(createAccountActivity, "Registration succesful.\n Please use the login and password", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(createAccountActivity, LoginActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onError(WaspError error) {

                }
            });

        } else {
            Toast.makeText(createAccountActivity, "Please fill all the details", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        ((CaddyHop)getApplication()).getApplicationComponent().inject(this);

        Log.d(TAG, "onCreate: " + dataManager);
        createAccountActivity = this;
    }
}
