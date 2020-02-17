package adapter.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowtimeactivityBinding;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.myview> {

    Context context;
    ArrayList<String> list;
    int index= -1;

    public TimeAdapter(Context context, ArrayList<String> list3) {
        this.context=context;
        this.list=list3;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtimeactivity,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.binding.txt.setText(String.valueOf(list.get(position)));

        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=position;
                notifyDataSetChanged();
            }
        });
        if (index==position)
        {
            holder.binding.card.setCardBackgroundColor(Color.LTGRAY);
            holder.binding.txt.setTextColor(Color.WHITE);
        }
        else
        {
            holder.binding.card.setCardBackgroundColor(Color.WHITE);
            holder.binding.txt.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowtimeactivityBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}