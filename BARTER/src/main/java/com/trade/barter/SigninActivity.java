package com.trade.barter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.trade.barter.api.ApiManager;
import com.trade.barter.utils.Utils;

public class SigninActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_main);

        getActionBar().hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.signinEmailInput)).getText().toString();
                String password = ((EditText) findViewById(R.id.signinPasswordInput)).getText().toString();

                //dummy credentials
//                email = "marklochrie50265@gmail.com";
//                password = "g00gle";

                password = Utils.md5(password);
                //verify the network connection - if successful login the trader
                if(Utils.checkConnectivity(getApplicationContext())) {
                    new ApiManager(getApplicationContext()).loginTrader(SigninActivity.this, email, password);
                }
            }
        });

        findViewById(R.id.signupBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.sign_up_url))));
            }
        });
    }


    

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
