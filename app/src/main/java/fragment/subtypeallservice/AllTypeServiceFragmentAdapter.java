package fragment.subtypeallservice;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowsubtypeallfragmentBinding;

import java.util.List;

import activity.home.preferedservice.PrefferedServiceActivity;
import data.model.api.homepage.ServiceData;

public class AllTypeServiceFragmentAdapter extends RecyclerView.Adapter<AllTypeServiceFragmentAdapter.myview> {

    List<ServiceData> list;

    public AllTypeServiceFragmentAdapter( List<ServiceData> list) {

        this.list=list;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowsubtypeallfragment,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, int position) {
        ServiceData data = list.get(position);
        holder.binding.setServiceData(data);
       // holder.binding.txt.setText(list.get(position).getName());
    }

    public void setData(List<ServiceData> serviceData)
    {
        this.list.clear();
        this.list = serviceData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        Log.w("adapter id",list.get(position).getParentId());
        return Integer.valueOf(list.get(position).getParentId());
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener{

        RowsubtypeallfragmentBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), PrefferedServiceActivity.class);
            i.putExtra("name",list.get(getLayoutPosition()).getName());
            i.putExtra("image",list.get(getLayoutPosition()).getIcon());
            i.putExtra("id",list.get(getLayoutPosition()).getServiceId());
            i.putExtra("title",list.get(getLayoutPosition()).getTitle());
            Log.w("adapter id",String.valueOf(list.get(getLayoutPosition()).getServiceId()));
            view.getContext().startActivity(i);
        }
    }
}
