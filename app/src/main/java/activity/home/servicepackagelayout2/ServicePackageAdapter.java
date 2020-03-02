package activity.home.servicepackagelayout2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowservicepackageBinding;

import java.util.List;

import data.model.api.servicepackage2.ServiceResult;

public class ServicePackageAdapter extends RecyclerView.Adapter<ServicePackageAdapter.myview> {

    private List<ServiceResult> list2;
    private String icon;
    public SpecificationAdapter adapter;

    private LinearLayoutManager layoutManager;

    public ServicePackageAdapter(List<ServiceResult> list2, String icon) {

        this.icon=icon;
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

        ServiceResult data2 = list2.get(position);
        holder.binding.setServiceResult(data2);

        adapter = new SpecificationAdapter(list2.get(position),list2.get(position).getSpecification(),icon);
        holder.binding.specificationrv.setAdapter(adapter);

      //  holder.setData(list2.get(position).getSpecification());

    }

    public void setList2(List<ServiceResult> specifications)
    {
        this.list2 = specifications;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        RowservicepackageBinding binding;
        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);

            layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.specificationrv.setLayoutManager(layoutManager);

            binding.specificationrv.setNestedScrollingEnabled(false);
        }


        @Override
        public void onClick(View view) {
            notifyItemRangeChanged(0, list2.size());
        }
    }
}
