package activity.home.servicepackagelayout2;

import android.util.Log;
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

import data.model.api.servicepackage2.Image;

public class ServicePackageImageAdapter extends RecyclerView.Adapter<ServicePackageImageAdapter.myview> {

    private List<Image> images;

    public ServicePackageImageAdapter(List<Image> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowservicepackageimage,parent,false);
        return new ServicePackageImageAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Image image = images.get(position);
        holder.binding.setImage(image);

        Picasso.with(holder.binding.img.getContext()).load(image.getImage()).into(holder.binding.img);
        Log.w("image",image.getImage());
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
