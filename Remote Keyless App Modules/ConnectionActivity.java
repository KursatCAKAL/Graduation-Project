package com.example.kc.gpdriverless;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.library.PulseView;

public class ConnectionActivity extends AppCompatActivity {

    PulseView pulseView;

    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        pulseView=(PulseView)findViewById(R.id.pv);
        btnConnect=(Button)findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                pulseView.startPulse();

                Toast toast = Toast.makeText(ConnectionActivity.this, "Searching", Toast.LENGTH_LONG);
                View view = toast.getView();

                //To change the Background of Toast
                view.setBackgroundColor(Color.TRANSPARENT);
                //view.setBackgroundColor(Color.BLUE);
                TextView text = (TextView) view.findViewById(android.R.id.message);

                //Shadow of the Of the Text Color
                text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
                text.setTextColor(Color.GREEN);
                text.setTextSize(Integer.valueOf(32));
                toast.show();

                AuthenticationInfo.setHostIPStatic("192.1.xxxxx");
                AuthenticationInfo.setPasswordStatic("TEST");
            }
        });
    }
}
