package activity.home.breifservicesubtype;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowsubtypeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import activity.home.preferedservice.PrefferedServiceActivity;
import data.model.api.homepage.ServiceData;

public class SubTypeServiceAdapter extends RecyclerView.Adapter<SubTypeServiceAdapter.myview> {

    private List<ServiceData> txtlist;

    public SubTypeServiceAdapter(ArrayList<ServiceData> txtlist) {

        this.txtlist=txtlist;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowsubtype,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, int position) {
        ServiceData data = txtlist.get(position);
        holder.binding.setServiceData(data);
        Picasso.with(holder.binding.img.getContext()).load(data.getIcon()).into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return txtlist.size();
    }

    public void setData(List<ServiceData> serviceData)
    {
        this.txtlist = serviceData;
        notifyDataSetChanged();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener{

        RowsubtypeBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), PrefferedServiceActivity.class);
            i.putExtra("name",txtlist.get(getLayoutPosition()).getName());
            i.putExtra("image",txtlist.get(getLayoutPosition()).getIcon());
            i.putExtra("id",txtlist.get(getLayoutPosition()).getServiceId());
            i.putExtra("title",txtlist.get(getLayoutPosition()).getTitle());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(i);
        }
    }
}
