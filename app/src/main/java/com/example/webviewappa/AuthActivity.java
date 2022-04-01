package com.example.webviewappa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class AuthActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    String name, id;
    private static final String ONESIGNAL_APP_ID = "3e75cb32-c63c-473e-a47e-0a1ca8cf983f";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authactivity);


        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userId = sharedPreferences.getString("id", "");

        if(!userId.equalsIgnoreCase(""))
        {
           intent = new Intent(AuthActivity.this, MainActivity.class);
           startActivity(intent);
        }

        // FB Auth
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("public_profile, email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Loggin done");

                // login ok get access token
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                            if(object != null) {
                                try {
                                    id = object.getString("id");
                                    name = object.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                editor.putString("id", id);
                                editor.putString("name", name);
                                editor.putString("player_name", name);
                                editor.putBoolean("b", false);
                                editor.commit();
                                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                                startActivity(intent);
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                System.out.println("Loggin cancel");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                System.out.println("Loggin error");
            }
        });

    }

}
