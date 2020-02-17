package adapter.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowservicepackageBinding;

import java.io.Serializable;
import java.util.ArrayList;

import classes.ServicePackageClass;

public class ServicePackageAdapter extends RecyclerView.Adapter<ServicePackageAdapter.myview> {

    Context context;
    ArrayList<ServicePackageClass> list;
    Serializable ii;
    Boolean b=false;
    ServicePackageAdapter adapter = ServicePackageAdapter.this;

    private int lastSelectedPosition = -1;


    public ServicePackageAdapter(Context context, ArrayList<ServicePackageClass> list, Serializable ii) {
        this.context=context;
        this.list=list;
        this.ii=ii;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowservicepackage,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, final int position) {

        if (ii.toString().equalsIgnoreCase("only hairstyle"))
        {
            Drawable[] drawables = holder.binding.txt2.getCompoundDrawables();
            Drawable leftCompoundDrawable = drawables[0];
            Drawable img = context.getResources().getDrawable(R.drawable.hairdryer);
            img.setBounds(leftCompoundDrawable.getBounds());
            holder.binding.txt2.setCompoundDrawables(img, null, null, null);
        }
        if (ii.toString().equalsIgnoreCase("only makeup"))
        {
            Drawable[] drawables = holder.binding.txt2.getCompoundDrawables();
            Drawable leftCompoundDrawable = drawables[0];
            Drawable img = context.getResources().getDrawable(R.drawable.onlymakeup);
            img.setBounds(leftCompoundDrawable.getBounds());
            holder.binding.txt2.setCompoundDrawables(img, null, null, null);
        }
        if (ii.toString().equalsIgnoreCase("birthday party"))
        {
            Drawable[] drawables = holder.binding.txt2.getCompoundDrawables();
            Drawable leftCompoundDrawable = drawables[0];
            Drawable img = context.getResources().getDrawable(R.drawable.round);
            img.setBounds(leftCompoundDrawable.getBounds());
            holder.binding.txt2.setCompoundDrawables(img, null, null, null);
        }
        if (ii.toString().equalsIgnoreCase("basic decoration") ||
                ii.toString().equalsIgnoreCase("premium decoration") ||
                ii.toString().equalsIgnoreCase("only game coordinator"))
        {
            Drawable[] drawables = holder.binding.txt2.getCompoundDrawables();
            Drawable leftCompoundDrawable = drawables[0];
            Drawable img = context.getResources().getDrawable(R.drawable.round);
            img.setBounds(leftCompoundDrawable.getBounds());
            holder.binding.txt2.setCompoundDrawables(img, null, null, null);
        }
        if(ii.toString().equalsIgnoreCase("makeup and hairstyle both"))
        {
            Drawable[] drawables = holder.binding.txt2.getCompoundDrawables();
            Drawable leftCompoundDrawable = drawables[0];
            Drawable img = context.getResources().getDrawable(R.drawable.hairstyle);
            img.setBounds(leftCompoundDrawable.getBounds());
            holder.binding.txt2.setCompoundDrawables(img, null, null, null);
        }

        holder.binding.radio.setText(list.get(position).getPack());
        holder.binding.txt1.setText(list.get(position).getRs());
        holder.binding.txt2.setText(list.get(position).getContent());
        holder.binding.txt3.setText(list.get(position).getExtra1());
        holder.binding.txt4.setText(list.get(position).getExtra2());

        holder.binding.radio.setChecked(position == lastSelectedPosition);
        Log.w("size",String.valueOf(list.size()));
    }

    public void setBoolean(Boolean b)
    {
        this.b=b;
    }
    public Boolean getBoolean()
    {
        return b;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        RowservicepackageBinding binding;
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
            //Log.w("position",String.valueOf(lastSelectedPosition));
            notifyItemRangeChanged(0, list.size());
        }
    }
}
