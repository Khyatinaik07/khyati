package activity.home.servicepackage;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowspecificationBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.model.api.servicepackage.ServiceResult;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.specmyview>  {

    List<ServiceResult> serviceResults;
    String icon;

    public SpecificationAdapter(List<ServiceResult> serviceResults,String icon) {
        this.serviceResults= serviceResults;
        this.icon=icon;
    }

    @NonNull
    @Override
    public specmyview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowspecification,parent,false);
        return new SpecificationAdapter.specmyview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull specmyview holder, int position) {
        ServiceResult data2 = serviceResults.get(position);
        holder.binding.setServiceResult(data2);

       /* Specification specification = data2.getSpecification().get(position);
        holder.binding.setSpecification(specification);  */

        Picasso.with(holder.binding.img.getContext()).load(icon).into(holder.binding.img);
        holder.binding.subtitle.setText(Html.fromHtml(data2.getSubTitle()));

        if (position==0)
        {
            holder.binding.img2.setVisibility(View.GONE);
        }

        if (position > 0)
        {
            Picasso.with(holder.binding.img2.getContext()).load(icon).into(holder.binding.img2);
            holder.binding.subtitle.setVisibility(View.GONE);
            holder.binding.img.setVisibility(View.GONE);
            holder.binding.img2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void updateList(List<ServiceResult> list) {
        this.serviceResults = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return serviceResults.size();
    }

    public class specmyview extends RecyclerView.ViewHolder{

        RowspecificationBinding binding;

        public specmyview(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
