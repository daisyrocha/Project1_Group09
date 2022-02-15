package com.example.fitnessapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {


    private ExerciseInfoJson<MuscleCategory> buttonList;

    public ButtonAdapter(ExerciseInfoJson<MuscleCategory> buttonList) {
        this.buttonList = buttonList;
    }


    @NonNull
    @Override
    public ButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * Here we are creating our views/designs
         * This class helps us create the view for the recyclerview.
         * We will inflate the custom design and pass it list_category because that's the
         * design we want to follow.
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_category,parent,false);
        return new ViewHolder(view);
    }

    /**
     * We set our text to our textview
     * We insert data into the views
     */
    @Override
//    public void onBindViewHolder(@NonNull ButtonAdapter.ViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("ButtonAdapter", buttonList.toString());
//        holder.muscle.setText(buttonList.getResults().get(position).getName());
        holder.muscle.setText("hi");
    }

    /**
     * Creates the number of views
     */
    @Override
    public int getItemCount() {
        return buttonList.getCount();
    }

    /**
     * This class helps us find/fetch the views of custom views
     * We have now obtained the ID of where we are placing our text
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView muscle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            muscle = itemView.findViewById(R.id.muscle);
        }
    }
}
