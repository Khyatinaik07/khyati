package activity.home.servicepackagelayout1.editpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RoweditservicepackageBinding;

import java.util.List;

import activity.home.servicepackagelayout2.ServicePackageImageAdapter;
import data.model.api.servicepackage2.ServiceResult;

public class EditPackageAdapter  extends RecyclerView.Adapter<EditPackageAdapter.myview>{

    private List<ServiceResult> serviceResults;
    ServicePackageImageAdapter adapter;
    String icon;

    public EditPackageAdapter(List<ServiceResult> serviceResults, String icon) {
        this.serviceResults = serviceResults;
        this.icon = icon;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roweditservicepackage,parent, false);
        return new EditPackageAdapter.myview(view);
    }

    public void setServiceResults(List<ServiceResult> serviceResults)
    {
        this.serviceResults = serviceResults;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        ServiceResult serviceResult = serviceResults.get(position);
        holder.binding.setServiceResult(serviceResult);


    }

    @Override
    public int getItemCount() {
        return serviceResults.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RoweditservicepackageBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }
}