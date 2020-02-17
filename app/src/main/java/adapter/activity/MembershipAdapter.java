package adapter.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowmembershipBinding;

import java.util.ArrayList;

import classes.MembershipClass;

public class MembershipAdapter extends RecyclerView.Adapter<MembershipAdapter.myview> {

    Context context;
    ArrayList<MembershipClass> list;
    private int lastSelectedPosition = -1;
    Boolean b = false;

    public MembershipAdapter(Context context, ArrayList<MembershipClass> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowmembership,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        holder.binding.radio.setText(list.get(position).getTitle());
        holder.binding.txtrs.setText(list.get(position).getRs());
        holder.binding.txtdes.setText(list.get(position).getDes());

        if(holder.binding.txtrs.length()==0 || holder.binding.txtrs.equals(""))
        {
            holder.binding.txtrs.setVisibility(View.GONE);
        } else {
            holder.binding.txtrs.setVisibility(View.VISIBLE);
        }
        if(holder.binding.txtdes.length()==0 || holder.binding.txtdes.equals(""))
        {
            holder.binding.txtdes.setVisibility(View.GONE);
        } else {
            holder.binding.txtdes.setVisibility(View.VISIBLE);
        }

        holder.binding.radio.setChecked(position == lastSelectedPosition);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setBoolean(Boolean b)
    {
        this.b=b;
    }
    public Boolean getBoolean()
    {
        return b;
    }

    public class myview extends RecyclerView.ViewHolder  implements View.OnClickListener{

        RowmembershipBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
            binding.radio.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            lastSelectedPosition = getAdapterPosition();
            setBoolean(true);
            notifyItemRangeChanged(0,list.size());
        }
    }
}
