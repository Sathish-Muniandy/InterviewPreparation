package com.example.interviewpreparation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.interviewpreparation.RoomDB.Fragment.AddFragment;
import com.example.interviewpreparation.RoomDB.Fragment.ListFragment;
import com.example.interviewpreparation.TestFragemnt.InterviewAddFragment;
import com.example.interviewpreparation.databinding.ActivityExpenseBinding;

public class ExpenseActivity extends AppCompatActivity {

    ActivityExpenseBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        InterviewAddFragment fragment = new InterviewAddFragment();
       // loadFragment(new InterviewAddFragment());
        loadFragment(fragment);

    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new InterviewAddFragment());
        fragmentTransaction.commit(); // save the changes
    }

}