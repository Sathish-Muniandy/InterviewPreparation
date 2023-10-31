package com.example.interviewpreparation.RoomDB.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewpreparation.ApiInterface.ExpensePosition;
import com.example.interviewpreparation.R;
import com.example.interviewpreparation.RoomDB.Data.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private ArrayList<String> StringsList;
    private ArrayList<Boolean> yearsSelected;
    private ArrayList<User> usersList;
    private ArrayList<String> lastnames;
    private int currentPosition = -1;
    private ExpensePosition expensePosition;

    public CategoryAdapter(ExpensePosition expensePosition) {
        this.expensePosition = expensePosition;
    }
    // RecyclerView recyclerView;  
   /* public CategoryAdapter(ArrayList<String> StringsList,
                          ArrayList<Boolean> yearsSelected, ArrayList<User> usersList) {
        this.StringsList = StringsList;
        this.yearsSelected = yearsSelected;
        this.usersList = usersList;
        lastnames = new ArrayList<>();
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.category_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(position == 0) {
            holder.categoryIv.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.travel));
            holder.categoryName.setText("Travel");
        }
        if(position == 1) {
            holder.categoryIv.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.movie));
            holder.categoryName.setText("Movie");
        }
        if(position == 2) {
            holder.categoryIv.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.shopping));
            holder.categoryName.setText("Shopping");
        }
        if(position == 3) {
            holder.categoryIv.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.recharge));
            holder.categoryName.setText("Mobile Recharge");
            holder.viewline.setVisibility(View.GONE);
        }

        holder.categoryItemCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expensePosition.getPosition(position);
            }
        });

        /*String myStringsList = StringsList.get(position);
        holder.month_year.setText(StringsList.get(position));
        holder.month_total.setText("200");
        holder.category.setVisibility(View.GONE);

        if(yearsSelected.get(position)) {
            holder.category.setVisibility(View.VISIBLE);
        }else {
            holder.category.setVisibility(View.GONE);
        }

        holder.month_llt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // StringsList.get(position).setChecked(true);
                currentPosition = position;
                lastnames.clear();
                for(int i=0;i<usersList.size();i++) {
                    if(StringsList.get(position).equalsIgnoreCase(usersList.get(i).getFirstName())) {
                        lastnames.add(usersList.get(i).getLastName());
                    }
                }

                for(int i=0;i<yearsSelected.size();i++) {
                    if(i==position) {
                        yearsSelected.set(i,true);
                    }else {
                        yearsSelected.set(i,false);
                    }
                }
                notifyDataSetChanged();
                //  notifyItemChanged(position);
               *//* for(int i=0;i<StringsList.size();i++) {
                    if(i==position) {
                        StringsList.get(i).setChecked(true);
                    }else {
                        StringsList.get(i).setChecked(false);
                    }
                }*//*
                //   notifyItemChanged(position);
                Log.i("names:",lastnames.toString());
                Toast.makeText(view.getContext(),"click on item: ",Toast.LENGTH_LONG).show();
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        public CircleImageView categoryIv;
        public ConstraintLayout categoryItemCL;
        public View viewline;
        public ViewHolder(View itemView) {
            super(itemView);
            this.categoryIv = (CircleImageView) itemView.findViewById(R.id.categoryIv);
            this.categoryName = (TextView) itemView.findViewById(R.id.categoryName);
            this.viewline = (View) itemView.findViewById(R.id.viewline);
            this.categoryItemCL = (ConstraintLayout) itemView.findViewById(R.id.categoryItemCL);
        }
    }
} 
