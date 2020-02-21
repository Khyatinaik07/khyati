package activity.home.servicepackagelayout2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowservicepackageimageBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.model.api.servicepackage2.ImageResult;
import data.remote.ApiEndPoints;

public class ServicePackageImageAdapter extends RecyclerView.Adapter<ServicePackageImageAdapter.myview> {

    private List<ImageResult> images;
    private String icon;

    public ServicePackageImageAdapter(List<ImageResult> images,String icon) {
        this.images = images;
        this.icon=icon;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowservicepackageimage,parent,false);
        return new ServicePackageImageAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        ImageResult image = images.get(position);
        holder.binding.setImage(image);

        Picasso.with(holder.binding.img.getContext()).load(ApiEndPoints.IMAGE_BASE_URL+image.getImage()).into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowservicepackageimageBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
