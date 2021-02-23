package com.example.todolist;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class TaskActivity  extends AppCompatDialogFragment{
    EditText editTextTask;
    TaskDialogLestienr lestienr;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.activiey_add_task,null);
        builder.setView(view).setTitle("إضافة المهمة ").setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
         .setPositiveButton("إضافة المهمة ", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                String Task =editTextTask.getText().toString();
                lestienr.applyTexts(Task);

             }
         });
        editTextTask=view.findViewById(R.id.editTextTask);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            lestienr= (TaskDialogLestienr)context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+"must implement TaskDialogLestienr");
        }
    }

    public interface  TaskDialogLestienr{
        void applyTexts(String Tasktext);



    }

}