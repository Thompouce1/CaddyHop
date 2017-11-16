package fr.polytechnice.caddyhop.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.activities.LoginActivity;
import fr.polytechnice.caddyhop.activities.MainActivity;
import fr.polytechnice.caddyhop.models.User;

/**
 * Created by shafiq on 06/06/16.
 *
 * TODO: make the UI better
 */
public class AccountInfoFragment extends Fragment {

    // TODO: use ButterKnife
    User user;
    TextView infoLogin;
    TextView infoEmail;
    TextView infoNom;
    TextView infoPrenom;

    Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_info, null);

        // Create UI components here.
        infoLogin = (TextView) view.findViewById(R.id.info_login);
        infoEmail = (TextView) view.findViewById(R.id.info_email);
        infoNom = (TextView) view.findViewById(R.id.info_nom);
        infoPrenom = (TextView) view.findViewById(R.id.info_prenom);

        logoutButton = (Button) view.findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: use the PreferencesHelper
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                prefs.edit().putBoolean("isLoggedIn", false).apply();
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        user = ((MainActivity)this.getActivity()).getCurrentUser();

        infoLogin.setText(user.getLogin());
        infoEmail.setText(user.getEmail());
        infoNom.setText(user.getNom());
        infoPrenom.setText(user.getPrenom());

        return view;
    }
}
