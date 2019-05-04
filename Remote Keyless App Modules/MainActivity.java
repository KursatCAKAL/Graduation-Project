package com.example.pcName.gpdriverless;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;

import java.io.InputStream;


public class MainActivity extends Activity{

    String menuContent[]={"Run","Stop","Settings","Close","Preferences","Steer","Analytics"};
    public boolean isSucessP=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final MediaPlayer runEngine=MediaPlayer.create(this,R.raw.engine_1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //goPreferences();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        CircleMenu circleMenu=(CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.drive,R.drawable.plus)
                .addSubMenu(Color.parseColor("#a9f550"),R.drawable.run)
                .addSubMenu(Color.parseColor("#77f550"),R.drawable.steer)
                .addSubMenu(Color.parseColor("#50f58d"),R.drawable.stop)
                .addSubMenu(Color.parseColor("#50f5bf"),R.drawable.settings)
                .addSubMenu(Color.parseColor("#50f5f1"),R.drawable.wifi)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        //Toast.makeText(MainActivity.this,"Engine Started "+menuContent[index],Toast.LENGTH_SHORT).show();



                        if(index==1)
                        {
                            runEngine.start();
                            Textend_Run t1=new Textend_Run();
                            try
                            {
                                Toast.makeText(MainActivity.this,"Requesting.. ",Toast.LENGTH_SHORT).show();
                                t1.start();
                                Thread.sleep(1000);

                                if(t1.isSuccess)
                                {
                                    Toast toast = Toast.makeText(MainActivity.this, "Engine Working...", Toast.LENGTH_LONG);
                                    View view = toast.getView();

                                    //To change the Background of Toast
                                    view.setBackgroundColor(Color.TRANSPARENT);
                                    //view.setBackgroundColor(Color.BLUE);
                                    TextView text = (TextView) view.findViewById(android.R.id.message);

                                    //Shadow of the Of the Text Color
                                    text.setShadowLayer(0, 0, 0, Color.BLUE);
                                    text.setTextColor(Color.GREEN);
                                    text.setTextSize(Integer.valueOf(32));
                                    toast.show();

                                }
                                else
                                {
                                    Toast toast = Toast.makeText(MainActivity.this, "Connection Error...", Toast.LENGTH_LONG);
                                    View view = toast.getView();

                                    //To change the Background of Toast
                                    view.setBackgroundColor(Color.TRANSPARENT);
                                    //view.setBackgroundColor(Color.BLUE);
                                    TextView text = (TextView) view.findViewById(android.R.id.message);

                                    //Shadow of the Of the Text Color
                                    text.setShadowLayer(0, 0, 0, Color.BLUE);
                                    text.setTextColor(Color.RED);
                                    text.setTextSize(Integer.valueOf(32));
                                    toast.show();
                                }
                                //runMethod();
                            }

                            catch (Exception e)
                            {
                                Toast.makeText(MainActivity.this,"Bağlantı Başarısız.",Toast.LENGTH_SHORT).show();
                            }



                        }
                        if(index==0)
                        {
                            runEngine.start();
                            Textend_Environment tDrive = new Textend_Environment();
                            try {
                                Toast.makeText(MainActivity.this, "Starting Environment.. ", Toast.LENGTH_SHORT).show();
                                tDrive.start();
                                Thread.sleep(1000);

                                if (tDrive.isSuccess) {
                                    Toast toast = Toast.makeText(MainActivity.this, "Environment Working...", Toast.LENGTH_LONG);
                                    View view = toast.getView();

                                    //To change the Background of Toast
                                    view.setBackgroundColor(Color.TRANSPARENT);
                                    //view.setBackgroundColor(Color.BLUE);
                                    TextView text = (TextView) view.findViewById(android.R.id.message);

                                    //Shadow of the Of the Text Color
                                    text.setShadowLayer(0, 0, 0, Color.BLUE);
                                    text.setTextColor(Color.GREEN);
                                    text.setTextSize(Integer.valueOf(32));
                                    toast.show();

                                } else {
                                    Toast toast = Toast.makeText(MainActivity.this, "Connection Error...", Toast.LENGTH_LONG);
                                    View view = toast.getView();

                                    //To change the Background of Toast
                                    view.setBackgroundColor(Color.TRANSPARENT);
                                    //view.setBackgroundColor(Color.BLUE);
                                    TextView text = (TextView) view.findViewById(android.R.id.message);

                                    //Shadow of the Of the Text Color
                                    text.setShadowLayer(0, 0, 0, Color.BLUE);
                                    text.setTextColor(Color.RED);
                                    text.setTextSize(Integer.valueOf(32));
                                    toast.show();
                                }
                                //runMethod();
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(MainActivity.this,"Bağlantı Başarısız.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(index==4)
                        {
                            startActivity(new Intent(MainActivity.this,ConnectionActivity.class));
                        }
                        if(index==3)
                        {
                            startActivity(new Intent(MainActivity.this,PreferencesActivity.class));
                        }

                    }
                });


    }

    public static void ToastMethod(String gelen){
        //Toast.makeText(MainActivity.this,gelen,Toast.LENGTH_SHORT).show();
    }
    /*
    public void goPreferences(){

        Button preferencesButton=(Button) findViewById(R.id.btnPref);
        preferencesButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PreferencesActivity.class));
            }
        });

    }*/
}

class Textend_Run extends Thread {
    boolean isSuccess=false;
    public void run() {
        try{
            JSch jsch=new JSch();

            //String host=System.getProperty("pcName") + "@192.1.xxxxxwirelesIP";
            //String user=host.substring(0, host.indexOf('@'));
            //host=host.substring(host.indexOf('@')+1);

            String host=System.getProperty(AuthenticationInfo.getHostNameStatic())+"@"+AuthenticationInfo.getHostIPStatic();
            String tPw=AuthenticationInfo.getPasswordStatic();
            System.out.println("----"+host+"-----"+tPw);

            com.jcraft.jsch.Session session=jsch.getSession("pcName", "127.0.0.1", portNumber);

            //com.jcraft.jsch.Session session=jsch.getSession(AuthenticationInfo.getHostNameStatic(), AuthenticationInfo.getHostIPStatic(), 22);


            session.setConfig("StrictHostKeyChecking", "no");
            // If two machines have SSH passwordless logins setup, the following line is not needed:
            //session.setPassword("".getBytes());
            session.setPassword(AuthenticationInfo.getPasswordStatic().getBytes());

            try
            {
                session.connect(500); // Yanlış Giriş Yapılması ihtimaline karşı uyarı mekanizması. //500
                isSuccess=true;
            }
            catch(Exception e)
            {
                //ToastUtils.showToast(getBaseContext(),"test");
                //MainActivity t=new MainActivity();
                //t.ToastMethod("Bağlantı Başarısız.");
                //Application inst=new Application();

                //Context test=getApplicationContext();
                //ToastUtils.showToast(test,"HATAAAAA");
                //ToastUtils.showToast("TEST HATASI");

            }

            if(!session.isConnected())
                throw new ArithmeticException("Bağlantı kaydı başarı ile gerçekleştirilemedi....");


            //String command2="python Desktop/pythonArg.py hellooo world\n";
            //String command="python2 Desktop/drive.py";
            //command="mv ~/Desktop/test.sh ~/Desktop/MoveDest/";
            //command="./test.sh";
            // command=args[1];
            String turningValue="14";
            String coordinateValue="22";

            String destinationX=AuthenticationInfo.getDestXCoordinate();
            String destinationY=AuthenticationInfo.getDestYCoordinate();
            System.out.println(destinationX+" "+destinationY);
            String command="./direct_cryptography_innerSh.sh"+" "+destinationX+" "+destinationY+" "+AuthenticationInfo.getHostNameStatic()+" "+"Parola";

            Channel channel=session.openChannel("exec");
            //((ChannelExec)channel).setCommand("cd Desktop");
            //((ChannelExec)channel).setCommand("python keyboard_controller.py");
            ((ChannelExec)channel).setCommand(command);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();

            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try
                {
                    //Thread.sleep(1000);//KOMUTUN YAKALANABİLMESİ ADINA ÇOK ÖNEMLİDİR.
                }
                catch(Exception ee) {ee.printStackTrace();}
            }
            channel.disconnect();
            session.disconnect();
        }
        catch(Exception e){
            System.out.println(e);
            //MainActivity.ToastMethod();
        }
    }


}
class Textend_Environment extends Thread {
        boolean isSuccess=false;
        public void run() {
            try{
                JSch jsch=new JSch();

                //String host=System.getProperty("pcName") + "@192.1xxxxxxWifeIP";
                //String user=host.substring(0, host.indexOf('@'));
                //host=host.substring(host.indexOf('@')+1);

                String host=System.getProperty(AuthenticationInfo.getHostNameStatic())+"@"+AuthenticationInfo.getHostIPStatic();
                String tPw=AuthenticationInfo.getPasswordStatic();
                System.out.println(host+"       "+tPw);
                //com.jcraft.jsch.Session session=jsch.getSession("pcName", "192.1xxxxxxWifeIP", portNumber);
                com.jcraft.jsch.Session session=jsch.getSession(AuthenticationInfo.getHostNameStatic(), AuthenticationInfo.getHostIPStatic(), 22);


                session.setConfig("StrictHostKeyChecking", "no");
                // If two machines have SSH passwordless logins setup, the following line is not needed:
                //session.setPassword("".getBytes());
                session.setPassword(AuthenticationInfo.getPasswordStatic().getBytes());

                try
                {
                    session.connect(500); // Yanlış Giriş Yapılması ihtimaline karşı uyarı mekanizması. //500
                    isSuccess=true;
                }
                catch(Exception e)
                {
                    //ToastUtils.showToast(getBaseContext(),"test");
                    //MainActivity t=new MainActivity();
                    //t.ToastMethod("Bağlantı Başarısız.");
                    //Application inst=new Application();

                    //Context test=getApplicationContext();
                    //ToastUtils.showToast(test,"HATAAAAA");
                    //ToastUtils.showToast("TEST HATASI");

                }

                if(!session.isConnected())
                    throw new ArithmeticException("Bağlantı başarısız.");



                String command="./environment_cryptography_innerSh.sh";

                Channel channel=session.openChannel("exec");
                //((ChannelExec)channel).setCommand("cd Desktop");
                //((ChannelExec)channel).setCommand("python keyboard_controller.py");
                ((ChannelExec)channel).setCommand(command);

                //channel.setInputStream(System.in);
                channel.setInputStream(null);

                ((ChannelExec)channel).setErrStream(System.err);

                InputStream in=channel.getInputStream();

                channel.connect();
                byte[] tmp=new byte[1024];
                while(true){
                    while(in.available()>0){
                        int i=in.read(tmp, 0, 1024);
                        if(i<0)break;
                        System.out.print(new String(tmp, 0, i));
                    }
                    if(channel.isClosed()){
                        System.out.println("exit-status: "+channel.getExitStatus());
                        break;
                    }
                    try
                    {
                        //Thread.sleep(1000);//KOMUTUN YAKALANABİLMESİ ADINA ÇOK ÖNEMLİDİR.
                    }
                    catch(Exception ee) {ee.printStackTrace();}
                }
                channel.disconnect();
                session.disconnect();
            }
            catch(Exception e){
                System.out.println(e);
                //MainActivity.ToastMethod();
            }
        }


    }
