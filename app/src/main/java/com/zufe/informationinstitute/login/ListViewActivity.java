package com.zufe.informationinstitute.login;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private ListView lv;
    private List<Works_info> wi=new ArrayList<Works_info>();
    private Works_info work;
    private  HashMap<String, Object> map = new HashMap<String, Object>();
    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();/*在数组中存放数据*/

    /*private Handler handler=new Handler(){
       public void handleMessage(android.os.Message msg){
           switch (msg.what){
               case 0:
                   wi=(List<Works_info>)msg.obj;
                   break;
           }
           Toast.makeText(ListViewActivity.this,"size:"+wi.size()+"",Toast.LENGTH_LONG).show();

           for(Works_info w:wi){
               Log.i("name:",w.getWorks_Name());
           }
       };
   };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Bmob.initialize(this, "e788a122fa48655ca7cb33516210ea97");

        lv = (ListView) findViewById(R.id.lv);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/
        //lv.setAdapter(new ArrayAdapter<String>(this,R.layout.listview, getData()));
        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(getApplicationContext(),listItem,//需要绑定的数据
                R.layout.item, new String[]{"ItemImage", "ItemTitle", "ItemText"}, new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemText});

        lv.setAdapter(mSimpleAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                setTitle("你点击了第" + arg2 + "行");//设置标题栏显示点击的行
            }
        });

        final BmobQuery<Works_info> query = new BmobQuery<Works_info>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("Works_Type", "图文类");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法

        query.findObjects(new FindListener<Works_info>() {
            @Override
            public void done(List<Works_info> object, BmobException e) {
                if (e == null) {
                    //toast("查询成功：共"+object.size()+"条数据。");
                    int num = object.size();
                    String numStr = String.valueOf(num);
                    //Toast.makeText(ListViewActivity.this,numStr,Toast.LENGTH_LONG).show();

                   /* Message message = handler.obtainMessage();
                    message.what = 0;
                    message.obj = object;
                    handler.sendMessage(message);*/

                    for(Works_info w:object){
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("ItemImage", R.drawable.icon);//加入图片
                        map.put("ItemTitle",w.getWorks_Name() );
                        map.put("ItemText",w.getWorks_Price());
                        listItem.add(map);
                    }
                    mSimpleAdapter.notifyDataSetChanged();
                    lv.setAdapter(mSimpleAdapter);
                } else {
                    Toast.makeText(ListViewActivity.this, "查询失败！" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_LONG).show();
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });

        Thread th=new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        th.start();
        /*for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.icon);//加入图片
            map.put("ItemTitle", "第"+i+"行");
            map.put("ItemText", "这是第"+i+"行");
            listItem.add(map);
        }*/
        Toast.makeText(ListViewActivity.this,"size！"+wi.size()+"",Toast.LENGTH_LONG).show();
       /* for(Works_info w:wi){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.icon);//加入图片
            map.put("ItemTitle",w.getWorks_Name() );
            map.put("ItemText",w.getWorks_Price());
            listItem.add(map);
        }*/



        /*for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.icon);//加入图片
            map.put("ItemTitle", "第"+i+"行");
            map.put("ItemText", "这是第"+i+"行");
            listItem.add(map);
        }
    */


    }

    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }

}
