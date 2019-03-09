package com.example.application;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> rList;
//    private ArrayList<Integer> checkedPositions;
    private boolean[] checkedPositions;

    public MainAdapter(ArrayList<String> rList) {
        this.rList = rList;
        this.checkedPositions = new boolean[rList.size()];
    }

    public ArrayList<String> getrList() {
        return rList;
    }

    public boolean[] getCheckedPositions() {
        return checkedPositions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurant_row,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.rName.setText(rList.get(i));
        checkedPositions[i] = viewHolder.rName.isChecked();
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox rName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rName = itemView.findViewById(R.id.checkBox);
        }
    }
}
