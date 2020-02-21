package activity.home.servicepackagelayout1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowpackagelayoutBinding;

import java.util.List;

import data.model.api.servicepackage2.ServicePackage;

public class ServicePackagePackageAdapter extends RecyclerView.Adapter<ServicePackagePackageAdapter.myview> {

    List<ServicePackage> packages;

    public ServicePackagePackageAdapter(List<ServicePackage> packages) {
        this.packages=packages;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowpackagelayout,parent,false);
        return new ServicePackagePackageAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        ServicePackage servicePackage = packages.get(position);
        holder.binding.setPackages(servicePackage);
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowpackagelayoutBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
