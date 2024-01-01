package com.example.myproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myproject.model.notesmodel;
import com.example.myproject.model.todomodel;
import com.example.myproject.utils.databaseHelper;
import com.example.myproject.utils.notesDatabaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class addnewnotes extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewNotes";
    private EditText addedtobj,Edt_titliobj,Edt_dateobj;
    private Button btn_add_notesobj;
    private notesDatabaseHelper notesdb;

    public static addnewnotes newInstance(){
        return new addnewnotes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addnewnotes, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addedtobj = view.findViewById(R.id.addedt);
        Edt_titliobj=view.findViewById(R.id.Edt_title);
        btn_add_notesobj = view.findViewById(R.id.btn_add_notes);
        Edt_dateobj=view.findViewById(R.id.Edt_date);
        notesdb = new notesDatabaseHelper(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String notes = bundle.getString("notes");
            addedtobj.setText(notes);
            if (notes.length() > 0) {
                btn_add_notesobj.setEnabled(false);
            }
        }

        btn_add_notesobj.setEnabled(false);
        addedtobj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btn_add_notesobj.setEnabled(false);
                    btn_add_notesobj.setBackgroundColor(Color.GRAY);
                } else {
                    btn_add_notesobj.setEnabled(true);
                    btn_add_notesobj.setBackgroundColor(Color.BLUE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Edt_dateobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });

        final boolean finalIsUpdate = isUpdate;


        btn_add_notesobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = addedtobj.getText().toString();
                String title=Edt_titliobj.getText().toString();
                String date=Edt_dateobj.getText().toString();


                if (finalIsUpdate) {
                    notesdb.updateTask(bundle.getInt("id"),notes);
                }else {
                    notesmodel item=new notesmodel();
                    item.setNotes(notes);
                    item.setTitle(title);
                    item.setDate(date);
                    notesdb.insertTask(item);
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

    private void openDateDialog(){
        Calendar calendar=Calendar.getInstance();
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Edt_dateobj.setText(String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));
            }
        }, year, month, day);
        dialog.show();
    }

}

