package adapter.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.RownewleadBinding;

import java.util.ArrayList;
import java.util.List;

import activity.home.NewLeadDetailActivity;
import classes.NewLeadClass;
import viewModel.NewLeadViewModel;

public class NewLeadFragmentAdapter extends RecyclerView.Adapter<NewLeadFragmentAdapter.myview> {

    private Context context;
    private List<NewLeadClass> list;

    public NewLeadFragmentAdapter() {
        this.list = new ArrayList<NewLeadClass>();
    }

    @Override
    public myview onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rownewlead, parent, false);
        context=parent.getContext();
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(myview holder, int position) {
        NewLeadClass n = list.get(position);
        holder.setViewModel(new NewLeadViewModel(n));
       /* holder.binding.name.setText(list.get(position).getName());
        holder.binding.servce.setText(list.get(position).getService());
        holder.binding.time.setText(list.get(position).getTime());
        holder.binding.date.setText(list.get(position).getDate());
        holder.binding.status.setText(list.get(position).getStatus());*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewAttachedToWindow(myview holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(myview holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<NewLeadClass> data) {
            this.list.addAll(data);
            notifyDataSetChanged();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener {

        RownewleadBinding binding;

        public myview(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.reject.setOnClickListener(this);
            binding.accept.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.accept:
                    Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show();
                    binding.accept.setVisibility(View.GONE);
                    binding.reject.setVisibility(View.GONE);
                    break;

                case R.id.reject:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Give Reason For cancel order");

                    final FrameLayout frame = new FrameLayout(context);
                    builder.setView(frame);

                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context, "Reason Submitted", Toast.LENGTH_SHORT).show();
                            binding.reject.setVisibility(View.GONE);
                            binding.accept.setVisibility(View.GONE);
                        }
                    });
                    final AlertDialog alertDialog = builder.create();
                    LayoutInflater l = alertDialog.getLayoutInflater();
                    View dialoglayout = l.inflate(R.layout.alertdialogview, frame);
                    alertDialog.show();
                    break;

                case R.id.linear:
                    Intent i = new Intent(context, NewLeadDetailActivity.class);
                    i.putExtra("status", binding.status.getText().toString());
                    context.startActivity(i);
                    break;
            }
        }

        /* package */ void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        /* package */ void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        void setViewModel(NewLeadViewModel viewModel) {
            if (binding != null) {
                binding.setViewModel(viewModel);
            }
        }
    }
}