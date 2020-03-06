package activity.home.servicepackagelayout1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowpackagelayoutBinding;

import java.util.List;

import data.model.api.servicepackage2.ServicePackage;
import data.model.api.servicepackage2.Specification;

public class ServicePackagePackageAdapter extends RecyclerView.Adapter<ServicePackagePackageAdapter.myview> {

    private List<ServicePackage> packages;
    public SpectificationDetailAdapter adapter;

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

        StringBuilder sb = new StringBuilder();
        List<Specification> specifications = packages.get(position).getSpecification();
        for (int i=0;i<specifications.size();i++)
        {
            sb.append(specifications.get(i).getTitle());
            sb.append(",");
        }
        String s = sb.toString();
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        holder.binding.specification.setText(s);
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

            LinearLayoutManager l = new LinearLayoutManager(itemView.getContext());
            l.setOrientation(RecyclerView.HORIZONTAL);
        }
    }
}