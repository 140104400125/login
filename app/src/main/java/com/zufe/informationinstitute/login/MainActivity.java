package com.zufe.informationinstitute.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    EditText editText_email,editText_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "e788a122fa48655ca7cb33516210ea97");
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
    }
    public void click_register(View view) {
        MyUser bu = new MyUser();
        String email=editText_email.getText().toString();
        String pwd=editText_pwd.getText().toString();
        bu.setUsername("sendi");
        bu.setPassword(pwd);
        bu.setAge(10);
        bu.setNick("1234");
        bu.setEmail(email);//注意：不能用save方法进行注册
        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"注册失败！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void click_login(View view) {

    }
}
