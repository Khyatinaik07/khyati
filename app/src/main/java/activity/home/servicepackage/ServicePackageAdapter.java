package activity.home.servicepackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowservicepackageBinding;

import java.util.List;

import data.model.api.servicepackage.Image;
import data.model.api.servicepackage.Specification;

public class ServicePackageAdapter extends RecyclerView.Adapter<ServicePackageAdapter.myview> {

    List<Image> list;
    List<Specification> list2;

    public ServicePackageAdapter(List<Image> list, List<Specification> list2) {

        this.list=list;
        this.list2=list2;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowservicepackage,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myview holder, final int position) {
        //Specification data2 = list2.get(position);
        //holder.binding.setSpecification(data2);
    }

    public void setList2(List<Specification> specifications)
    {
        this.list2 = specifications;
        notifyDataSetChanged();
    }

    public void setList(List<Image> images)
    {
        this.list=images;
        notifyDataSetChanged();
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
        }

        @Override
        public void onClick(View view) {
            notifyItemRangeChanged(0, list.size());
        }
    }
}
