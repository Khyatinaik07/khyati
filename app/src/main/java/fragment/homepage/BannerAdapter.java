package fragment.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowbanneractivityBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.model.api.homepage.BannerData;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.myview> {

    List<BannerData> list1;
    Context context;

    public BannerAdapter(Context context,List<BannerData> list1) {
        this.context=context;
        this.list1=list1;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowbanneractivity,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        BannerData data = list1.get(position);
        holder.binding.setBannerData(data);
        Picasso.with(holder.binding.img.getContext()).load(data.getImage()).into(holder.binding.img);
    }

    public void setData(List<BannerData> bannerData) {
        this.list1 = bannerData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowbanneractivityBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
