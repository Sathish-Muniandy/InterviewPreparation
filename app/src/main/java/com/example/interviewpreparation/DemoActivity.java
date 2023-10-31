package com.example.interviewpreparation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.interviewpreparation.Interface.FirstInterface;
import com.example.interviewpreparation.Interface.SecondInterface;
import com.example.interviewpreparation.Interface.ThirdInterface;
import com.example.interviewpreparation.basics.Demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener, FirstInterface, SecondInterface, ThirdInterface {

    List<String> list = new ArrayList<String>();
    Collection<String> collection = new ArrayList<String>();
    ArrayList<String> arrayList = new ArrayList<String>();
    HashMap<String,String> hashMap = new HashMap();
    HashMap<String,String> hashMap1 = new HashMap();
    Stack<String> stack = new Stack<>();
    Queue<String> queue = new PriorityQueue<String>();
    Set<String> set = new HashSet<>();
    SharedPreferences preferences;

    void SetdynamicTheme(int theme) {
        switch (theme) {

            case 0:
            DemoActivity.this.setTheme(R.style.BlueTheme);
            break;
            case 1:
            DemoActivity.this.setTheme(R.style.RedTheme);
            break;
            case 2:
            DemoActivity.this.setTheme(R.style.GreenTheme);
            break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme = preferences.getInt("theme",0);
       // ReCallActivity(theme);
        SetdynamicTheme(theme);
        setContentView(R.layout.activity_demo);

        ImageButton r = findViewById(R.id.redBtn);
        ImageButton b = findViewById(R.id.blueBtn);
        ImageButton g = findViewById(R.id.greenBtn);

        r.setOnClickListener(this);
        b.setOnClickListener(this);
        g.setOnClickListener(this);

        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        System.out.println("arraylist:"+arrayList.toString());
        set.add("a");
        set.add("b");
        set.add("a");
        set.add("a");
        set.add("e");
      //  set.remove("e");
        System.out.println("setstr:"+set.toString()+set.size());


        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.remove();
       // queue.peek();
        System.out.println("queuestr:"+queue.toString());

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.indexOf("a");

      //  stack.pop();
        stack.add(1,"h");
        stack.pop();

        System.out.println("liststr:"+stack.toString());
        System.out.println("liststr:"+stack.lastElement());
        System.out.println("liststr:"+stack.indexOf("a"));

        /*hashMap.put("name","sathish");
        hashMap.clear();
        hashMap.putAll(hashMap);
        hashMap.remove("name");
        hashMap.replace("name","muniandy");
        hashMap1 = hashMap;

        list.add("a");
        list.add("b");
        list.subList(0,1);
        list.size();
        list.set(1,"o");
        list.clear();

        arrayList.add("a");
        arrayList.add("b");
        arrayList.subList(0,1);
        arrayList.addAll(list);
        arrayList.subList(0,2);
        arrayList.indexOf("a");
        arrayList.remove(0);
        arrayList.size();
        arrayList.clear();
        arrayList.set(0,"r");
        arrayList.lastIndexOf("a");

        collection.add("a");
        collection.add("b");
        collection.size();
        collection.clear();
        collection.addAll(collection);*/

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blueBtn:
                DemoActivity.this.setTheme(R.style.BlueTheme);
                ReCallActivity(0);
            break;
            case R.id.redBtn:
                Toast.makeText(DemoActivity.this,"Click",Toast.LENGTH_LONG).show();
                DemoActivity.this.setTheme(R.style.RedTheme);
                ReCallActivity(1);
                break;
            case R.id.greenBtn:
                setTheme(R.style.GreenTheme);
                ReCallActivity(2);
            break;
        }
    }

    void ReCallActivity(int theme) {
        preferences.edit().putInt("theme",theme).commit();
        Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}