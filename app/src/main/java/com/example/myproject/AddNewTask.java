package com.example.myproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myproject.model.todomodel;
import com.example.myproject.utils.databaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewTask";


    private EditText meditText;
    private Button savebtn;
    private databaseHelper mydb;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addnewtask, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meditText = view.findViewById(R.id.addedt);
        savebtn = view.findViewById(R.id.btnsave);
        mydb = new databaseHelper(getActivity());


        savebtn.setEnabled(false);
        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            meditText.setText(task);
            if (task.length() > 0) {
                savebtn.setEnabled(false);
            }
        }
        meditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    savebtn.setEnabled(false);
                    savebtn.setBackgroundColor(Color.GRAY);
                } else {
                    savebtn.setEnabled(true);
                    savebtn.setBackgroundColor(Color.BLUE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final boolean finalIsUpdate = isUpdate;
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = meditText.getText().toString();
                if (finalIsUpdate) {
                    mydb.updateTask(bundle.getInt("id"),text);
                }else {
                    todomodel item=new todomodel();
                    item.setTask(text);
                    item.setStatus(0);
                    mydb.insertTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity=getActivity();
        if (activity instanceof onDialogCloseListner){
            ((onDialogCloseListner)activity).onDialogClose(dialog);
        }
    }
}

