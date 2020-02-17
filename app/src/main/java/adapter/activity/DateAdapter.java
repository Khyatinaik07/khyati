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
import com.example.onlineserviceportal.databinding.RowdateactivityBinding;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.myview> {

    Context context;
    ArrayList<String> list;
    ArrayList<String> list2;
    int index= -1;

    public DateAdapter(Context context, ArrayList<String> list, ArrayList<String> list2) {
        this.context=context;
        this.list=list;
        this.list2=list2;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdateactivity,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.binding.txt.setText(String.valueOf(list.get(position)));
        holder.binding.txt2.setText(String.valueOf(list2.get(position)));

        holder.binding.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=position;
                notifyDataSetChanged();
            }
        });
        if (index==position)
        {
            holder.binding.relative.setBackgroundColor(Color.LTGRAY);
            holder.binding.txt.setTextColor(Color.WHITE);
            holder.binding.txt2.setTextColor(Color.WHITE);

        }
        else
        {
            holder.binding.relative.setBackgroundColor(Color.WHITE);
            holder.binding.txt.setTextColor(Color.BLACK);
            holder.binding.txt2.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowdateactivityBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
