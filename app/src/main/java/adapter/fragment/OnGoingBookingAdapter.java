package adapter.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowongoingbookingBinding;

import java.util.ArrayList;

import activity.home.BookingDetailActivity;
import classes.OnGoingBookingClass;

public class OnGoingBookingAdapter extends RecyclerView.Adapter<OnGoingBookingAdapter.myview> {

    Context context;
    ArrayList<OnGoingBookingClass> list1;

    public OnGoingBookingAdapter(Context context, ArrayList<OnGoingBookingClass> list1) {
        this.context=context;
        this.list1=list1;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowongoingbooking,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        holder.binding.service.setText(list1.get(position).getService());
        holder.binding.date.setText(list1.get(position).getDate());
        holder.binding.time.setText(list1.get(position).getTime());
        holder.binding.status.setText(list1.get(position).getStatus());
        holder.binding.rs.setText(list1.get(position).getrs());
        holder.binding.name.setText(list1.get(position).getName());
        holder.binding.month.setText(list1.get(position).getMonth());
        holder.binding.year.setText(list1.get(position).getYear());

        if (holder.binding.status.getText().equals("Pending"))
        {
            holder.binding.status.setTextColor(Color.RED);
        }
        else
        {
            holder.binding.status.setTextColor(ContextCompat.getColor(context,R.color.orange));
        }
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener{

        RowongoingbookingBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, BookingDetailActivity.class);
            i.putExtra("status",binding.status.getText().toString());
            context.startActivity(i);
        }
    }
}
