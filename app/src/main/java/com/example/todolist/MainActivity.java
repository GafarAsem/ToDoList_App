package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TaskActivity.TaskDialogLestienr{

    myAdapter MA;
    ListView listview;
    DataBase DB;
    ArrayList Tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listview=findViewById(R.id.ListView);

        DB =new DataBase(this);

        Update();


    }
    public void openDialog(View view){
        TaskActivity TaskDialog =new TaskActivity();
        TaskDialog.show(getSupportFragmentManager(),"Task dialog");

    }
    public void clickCheckBox(View view){

        final CheckBox cb=view.findViewById(R.id.checkBox);
        //cb.getVerticalScrollbarPosition();
        int black=cb.getSolidColor();
        if(cb.isChecked())
            cb.setTextColor(Color.GRAY);
        else
            cb.setTextColor(Color.WHITE);




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                DB.DeleteData(cb.getText().toString());
                Update();
                Toast.makeText(MainActivity.this, "تم انهاء المهمة", Toast.LENGTH_SHORT).show();
            }
        }, 700);

    }

    public void Update(){
        Tasks=DB.GetAllTasks(DB.Co2);

        MA=new myAdapter(this,Tasks);

        listview.setAdapter(MA);
    }
    @Override
    public void applyTexts(String Tasktext) {
        boolean i=DB.InsertData(Tasktext);
        if(i) {
            Toast.makeText(this, "تم انشاء المهمة", Toast.LENGTH_SHORT).show();
            Update();
        }
        else
            Toast.makeText(this, "عذرا ,لم يتم انشاء المهمة", Toast.LENGTH_SHORT).show();
    }
}

class myAdapter extends ArrayAdapter{

    ArrayList<String>   checkbox;
    public myAdapter (Context c, ArrayList<String>  checkbox){

        super(c ,R.layout.lists,R.id.checkBox, checkbox);
        this.checkbox=checkbox;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        LayoutInflater infalter=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View Lists=infalter.inflate(R.layout.lists,parent,false);
//
//        CheckBox checkBox=Lists.findViewById(R.id.checkBox);
//       // checkBox.setText(this.checkbox[position]);
//        return Lists;
//    }
}