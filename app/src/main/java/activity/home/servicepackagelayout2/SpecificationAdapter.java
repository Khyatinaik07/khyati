package activity.home.servicepackagelayout2;

import android.content.Intent;
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

import activity.home.servicepackagelayout2.specificationdetail.SpecificationDetailActivity;
import data.model.api.servicepackage2.ServiceResult;
import data.model.api.servicepackage2.Specification;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.specmyview>  {

    List<Specification> serviceResults;
    String icon;
    ServiceResult sr;

    public SpecificationAdapter(ServiceResult serviceResult, List<Specification> serviceResults, String icon) {
        this.serviceResults= serviceResults;
        this.icon=icon;
        this.sr=serviceResult;
    }

    @NonNull
    @Override
    public specmyview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowspecification,parent,false);
        return new SpecificationAdapter.specmyview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull specmyview holder, int position) {

        Specification specification = serviceResults.get(position);
        holder.binding.setServiceResult(specification);

        Picasso.with(holder.binding.img.getContext()).load(icon).into(holder.binding.img);
        holder.binding.subtitle.setText(Html.fromHtml(sr.getSubTitle()));
        Picasso.with(holder.binding.img2.getContext()).load(icon).into(holder.binding.img2);

        if (position==0)
        {
            holder.binding.img2.setVisibility(View.GONE);
        }

        if (position > 0)
        {
            holder.binding.subtitle.setVisibility(View.GONE);
            holder.binding.img.setVisibility(View.GONE);
            holder.binding.img2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return serviceResults.size();
    }

    public class specmyview extends RecyclerView.ViewHolder {

        RowspecificationBinding binding;

        public specmyview(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
            binding.conViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), SpecificationDetailActivity.class);
                    i.putExtra("person",binding.person.getText());
                    i.putExtra("amount",binding.price.getText());
                    i.putExtra("time",binding.time.getText());
                    i.putExtra("subtitle",sr.getSubTitle());
                    i.putExtra("icon",icon);
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}
