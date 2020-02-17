package activity.home.preferedservice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowprefferedserviceBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.home.ServicePackageActivity;
import data.model.api.homepage.ServiceData;

public class PrefferedServiceAdapter extends RecyclerView.Adapter<PrefferedServiceAdapter.myview> {

    List<ServiceData> list;

    public PrefferedServiceAdapter(List<ServiceData> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowprefferedservice,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, int position) {
        ServiceData data = list.get(position);
        holder.binding.setServiceData(data);
        Picasso.with(holder.binding.img.getContext()).load(list.get(position).getIcon()).into(holder.binding.img);
       // holder.binding.txt.setText(list.get(position).getName());
    }

    public void setData(List<ServiceData> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        RowprefferedserviceBinding binding;
        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ServicePackageActivity.class);
            intent.putExtra("name",binding.txt.getText().toString());
            view.getContext().startActivity(intent);
        }
    }
}