package com.example.kc.gpdriverless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

    }

    public void btnAssign(View btnAssign){
        TextView txtHostName=(TextView)findViewById(R.id.txtHostName);
        TextView txtPassword=(TextView)findViewById(R.id.txtPassword);
        TextView txtHostIP=(TextView)findViewById(R.id.txtHostIP);

        TextView txtdestX=(TextView)findViewById(R.id.txtDestX);
        TextView txtdestY=(TextView)findViewById(R.id.txtDestY);


        AuthenticationInfo.setHostNameStatic(txtHostName.getText().toString());
        AuthenticationInfo.setHostIPStatic(txtHostIP.getText().toString());
        AuthenticationInfo.setPasswordStatic(txtPassword.getText().toString());
        AuthenticationInfo.setDestXCoordinate(txtdestX.getText().toString());
        AuthenticationInfo.setDestYCoordinate(txtdestY.getText().toString());
        System.out.println( AuthenticationInfo.getDestXCoordinate()+ " ------------------------- " +AuthenticationInfo.getDestYCoordinate());

        Toast.makeText(PreferencesActivity.this,"Ayarlandı ",Toast.LENGTH_SHORT).show();

        String testH=AuthenticationInfo.getHostNameStatic()+AuthenticationInfo.getHostIPStatic()+AuthenticationInfo.getPasswordStatic();
    }
}
