package com.example.interviewpreparation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Application;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.interviewpreparation.ApiInterface.ExpensePosition;
import com.example.interviewpreparation.RoomDB.Adapter.CategoryAdapter;
import com.example.interviewpreparation.RoomDB.Adapter.ExpenseAdapter;
import com.example.interviewpreparation.RoomDB.Data.ExpenseUser;
import com.example.interviewpreparation.RoomDB.RoomDBViewModel.ExpenseViewModel;
import com.example.interviewpreparation.databinding.ActivityAddExpenseBinding;
import com.example.interviewpreparation.databinding.ActivityExpenseBinding;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity implements ExpensePosition {

    ActivityAddExpenseBinding viewBinding;
    ExpenseViewModel expenseViewModel;
    Application application;

    void addUser() {
        ExpenseUser user = new ExpenseUser(0,1,100,"23-JAN-2023","JAN-2023");
        expenseViewModel.addExpenseUser(user);
        Toast.makeText(this,"insert",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityAddExpenseBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

int j = 1;
        for(int i=1;i<=5;i++) {
            j*=i;
        }
        Log.i("Total:",String.valueOf(j));
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        addUser();
        expenseViewModel.readUser();
        expenseViewModel.read().observe(this,expenseUsers -> {
            Log.i("datas:",expenseUsers.toString());
        });

        viewBinding.categoryCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.categoryRv.setVisibility(View.VISIBLE);
            }
        });
        viewBinding.categoryRv.setAdapter(new CategoryAdapter(this));
        viewBinding.categoryRv.setLayoutManager(new LinearLayoutManager(this));

        viewBinding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        AddExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                Log.i("DATE:",dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void getPosition(int position) {
        viewBinding.categoryRv.setVisibility(View.GONE);
        if(position == 0) {
            viewBinding.expenseImg.setImageDrawable(getResources().getDrawable(R.drawable.travel));
            viewBinding.expenseItem.setText("Travel");
        }
        if(position == 1) {
            viewBinding.expenseImg.setImageDrawable(getResources().getDrawable(R.drawable.movie));
            viewBinding.expenseItem.setText("Movie");
        }
        if(position == 2) {
            viewBinding.expenseImg.setImageDrawable(getResources().getDrawable(R.drawable.shopping));
            viewBinding.expenseItem.setText("Shopping");
        }
        if(position == 3) {
            viewBinding.expenseImg.setImageDrawable(getResources().getDrawable(R.drawable.recharge));
            viewBinding.expenseItem.setText("Mobile Recharge");
        }
        Toast.makeText(this,String.valueOf(position),Toast.LENGTH_LONG).show();
    }
}