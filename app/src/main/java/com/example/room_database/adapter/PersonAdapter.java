package com.example.room_database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_database.R;
import com.example.room_database.constants.Constants;
import com.example.room_database.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private Context context;
    private List<Person> mPersonList;

    public PersonAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mPersonList.get(i).getFirstName());
        myViewHolder.email.setText(mPersonList.get(i).getLastName());
    }

    @Override
    public int getItemCount() {
        if(mPersonList == null){
            return 0;
        }
        return mPersonList.size();
    }

    public void setTask(List<Person> personList){
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTask(){
        return mPersonList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email;
        ImageView editImage;

        MyViewHolder(@NonNull final View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.first_name);
            email = itemView.findViewById(R.id.last_name);

//            editImage = itemView.findViewById(R.id.iv)
        }
    }
}
