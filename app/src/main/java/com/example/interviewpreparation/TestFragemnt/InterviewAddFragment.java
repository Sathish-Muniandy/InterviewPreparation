package com.example.interviewpreparation.TestFragemnt;

//import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewpreparation.R;
import com.example.interviewpreparation.RoomDB.Adapter.ExpenseAdapter;
import com.example.interviewpreparation.RoomDB.Data.ExpenseChecked;
import com.example.interviewpreparation.RoomDB.Data.User;
import com.example.interviewpreparation.RoomDB.RoomDBViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class InterviewAddFragment extends Fragment {

    UserViewModel userViewModel;
    ArrayList<User> usersList = new ArrayList<>();
    List<User> usersList1 = new ArrayList<>();

    public static InterviewAddFragment newInstance(String param1, String param2) {
        InterviewAddFragment fragment = new InterviewAddFragment();

        return fragment;
    }

    ArrayList<String> years = new ArrayList<>();
    ArrayList<Boolean> yearsSelected = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_interview_add, container, false);
        RecyclerView month_rv = view.findViewById(R.id.month_rv);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.readUser();

        userViewModel.read().observe(getViewLifecycleOwner(),users -> {
          //  Log.i("Retrive:",users.toString());
            usersList = (ArrayList<User>) users;
            for(int i=0;i<usersList.size();i++) {
                if(i==0) {
                    years.add(usersList.get(i).getFirstName());
                    yearsSelected.add(false);
                }else {
                    if(!years.contains(usersList.get(i).getFirstName())) {
                        years.add(usersList.get(i).getFirstName());
                        yearsSelected.add(false);
                    }
                }
            }
            month_rv.setAdapter(new ExpenseAdapter(years,yearsSelected,usersList));
            month_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            /*Log.i("RetriveUsers:",usersList.toString());
            for(int i=0;i<usersList.size();i++) {
                if(i==0) {
                    years.add(usersList.get(i).getFirstName());
                }else {
                    if(!years.contains(usersList.get(i).getFirstName())) {
                        years.add(usersList.get(i).getFirstName());
                    }
                }
            }
            Log.i("dates:",years.toString());*/
        });


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("RetriveUsers:",usersList.toString());
    }
}