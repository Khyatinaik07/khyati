package activity.home.servicepackagelayout3;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowspecificationdetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.model.api.servicepackage2.Specification;

public class SpecificationDetailAdapter extends RecyclerView.Adapter<SpecificationDetailAdapter.myview>{

    private List<Specification> specifications;
    private String icon;
    public ImageAdapter imageAdapter;

    public SpecificationDetailAdapter(List<Specification> specification, String icon) {
        this.specifications = specification;
        this.icon = icon;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowspecificationdetail,parent,false);
        return new SpecificationDetailAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Specification specification = specifications.get(position);
        holder.binding.setServiceResult(specification);
        holder.binding.subtitle.setText(Html.fromHtml(specification.getSpecification()));

        if (specifications.get(position).getImages().size() == 0)
        {
            holder.binding.imgrv.setVisibility(View.GONE);
            Picasso.with(holder.binding.img.getContext()).load(icon).into(holder.binding.img);
        }
        else
        {
            holder.binding.imgrv.setVisibility(View.VISIBLE);
            holder.binding.img.setVisibility(View.GONE);
            imageAdapter = new ImageAdapter(specifications.get(position).getImages());
            holder.binding.imgrv.setAdapter(imageAdapter);
        }

        if (specifications.size() == 0)
        {
            ((ServicePackageLayoutThreeActivity)holder.binding.imgrv.getContext()).isSpecificationAvailable(true);
        }
        else
        {
            ((ServicePackageLayoutThreeActivity)holder.binding.imgrv.getContext()).isSpecificationAvailable(false);
        }

    }

    @Override
    public int getItemCount() {
        return specifications.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowspecificationdetailBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            LinearLayoutManager l = new LinearLayoutManager(itemView.getContext());
            l.setOrientation(RecyclerView.HORIZONTAL);
            binding.imgrv.setLayoutManager(l);
        }
    }

}
