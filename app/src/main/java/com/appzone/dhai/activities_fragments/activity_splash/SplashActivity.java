package com.appzone.dhai.activities_fragments.activity_splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.appzone.dhai.R;
import com.appzone.dhai.activities_fragments.activity_home.activity.HomeActivity;
import com.appzone.dhai.activities_fragments.activity_sign_in.activity.SignInActivity;
import com.appzone.dhai.models.UserModel;
import com.appzone.dhai.preferences.Preferences;
import com.appzone.dhai.singletone.UserSingleTone;
import com.appzone.dhai.tags.Tags;

public class SplashActivity extends AppCompatActivity {

    private FrameLayout fl;
    private Preferences preferences;
    private UserSingleTone userSingleTone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferences = Preferences.getInstance();
        userSingleTone = UserSingleTone.getInstance();
        fl = findViewById(R.id.fl);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade);
        fl.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                String session = preferences.getSession(SplashActivity.this);

                if (session.equals(Tags.session_login))
                {
                    UserModel userModel = preferences.getUserData(SplashActivity.this);
                    userSingleTone.setUserModel(userModel);
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
