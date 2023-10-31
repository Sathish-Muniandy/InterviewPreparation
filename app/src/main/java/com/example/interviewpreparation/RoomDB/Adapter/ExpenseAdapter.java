package com.example.interviewpreparation.RoomDB.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.interviewpreparation.R;
import com.example.interviewpreparation.RoomDB.Data.User;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{
    private ArrayList<String> StringsList;
    private ArrayList<Boolean> yearsSelected;
    private ArrayList<User> usersList;
    private ArrayList<String> lastnames;
    private int currentPosition = -1;

    // RecyclerView recyclerView;  
    public ExpenseAdapter(ArrayList<String> StringsList,
                          ArrayList<Boolean> yearsSelected, ArrayList<User> usersList) {
        this.StringsList = StringsList;
        this.yearsSelected = yearsSelected;
        this.usersList = usersList;
        lastnames = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.month_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String myStringsList = StringsList.get(position);
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
               /* for(int i=0;i<StringsList.size();i++) {
                    if(i==position) {
                        StringsList.get(i).setChecked(true);
                    }else {
                        StringsList.get(i).setChecked(false);
                    }
                }*/
             //   notifyItemChanged(position);
                Log.i("names:",lastnames.toString());
                Toast.makeText(view.getContext(),"click on item: ",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return StringsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView month_year,category;
        public TextView month_total;
        public LinearLayout month_llt;
        public ViewHolder(View itemView) {
            super(itemView);
            this.month_year = (TextView) itemView.findViewById(R.id.month_year);
            this.category = (TextView) itemView.findViewById(R.id.category);
            this.month_total = (TextView) itemView.findViewById(R.id.month_total);
            this.month_llt = (LinearLayout) itemView.findViewById(R.id.month_llt);
            this.month_llt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int lastPosition = currentPosition;
                    int pos = getAdapterPosition();
                 //   notifyDataSetChanged();
                  //  notifyItemChanged(pos);
                }
            });
        }
    }
} 