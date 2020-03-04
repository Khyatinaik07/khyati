package activity.home.servicepackagelayout3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowimageBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.model.api.servicepackage2.Image;
import data.remote.ApiEndPoints;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.myview> {

    private List<Image> images;

    public ImageAdapter(List<Image> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowimage,parent,false);
        return new ImageAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Image image = images.get(position);
        holder.binding.setImage(image);

        Picasso.with(holder.binding.img.getContext()).load(ApiEndPoints.IMAGE_BASE_URL+image.getFilepath()).into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowimageBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
