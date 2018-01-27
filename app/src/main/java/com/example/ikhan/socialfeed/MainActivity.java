package com.example.ikhan.socialfeed;

import android.app.Activity;
import android.content.Intent;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends Activity {

    TextView txtStatus;
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        intializeControls();
        loginWithFB();

    }
    private void intializeControls(){
        callbackManager=CallbackManager.Factory.create();
        txtStatus=(TextView)findViewById(R.id.Status);
        loginButton=(LoginButton)findViewById(R.id.login_button);

    }
    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtStatus.setText("Login Success\n"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Canceled");
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error: "+error.getMessage());

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
