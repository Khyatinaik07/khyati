package fragment.homepage;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowserviceactivityBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.home.allsubservice.SubServiceAllActivity;
import data.model.api.homepage.ServiceData;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.myview>{

    private List<ServiceData> txtlist2;

    public ServiceAdapter(List<ServiceData> txtlist2) {
        this.txtlist2=txtlist2;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowserviceactivity,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, final int position) {
        ServiceData data = txtlist2.get(position);
        holder.binding.setServiceData(data);
        Picasso.with(holder.binding.img.getContext()).load(data.getIcon()).into(holder.binding.img);
    }

    public void setData(List<ServiceData> serviceData)
    {
        this.txtlist2 = serviceData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return txtlist2.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener{

        RowserviceactivityBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), SubServiceAllActivity.class);
            i.putExtra("tbcount",getLayoutPosition());
            i.putExtra("id",txtlist2.get(getLayoutPosition()).getServiceId());
            Log.w("id","service id"+txtlist2.get(getLayoutPosition()).getServiceId());
            view.getContext().startActivity(i);
        }
    }
}
