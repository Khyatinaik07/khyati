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
import com.example.onlineserviceportal.databinding.RowbreifservicelayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.home.breifservicesubtype.SubTypeServiceActivity;
import data.model.api.homepage.ServiceData;

public class BriefServiceAdapter extends RecyclerView.Adapter<BriefServiceAdapter.myview> {


    List<ServiceData> list3;

    public BriefServiceAdapter(List<ServiceData> list3) {
        this.list3 = list3;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowbreifservicelayout, parent, false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, int position) {
        ServiceData data = list3.get(position);
        holder.binding.setServiceData(data);
        Picasso.with(holder.binding.img.getContext()).load(data.getBanner()).into(holder.binding.img);
    }

    public void setData(List<ServiceData> serviceData) {
        this.list3 = serviceData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list3.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener {

        RowbreifservicelayoutBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), SubTypeServiceActivity.class);
            i.putExtra("id",String.valueOf(list3.get(getLayoutPosition()).getServiceId()));
            i.putExtra("name",list3.get(getLayoutPosition()).getName());
            Log.w("adapet id",String.valueOf(list3.get(getLayoutPosition()).getServiceId()));
            view.getContext().startActivity(i);
        }
    }
}
