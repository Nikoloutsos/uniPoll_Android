package com.androiddreamer.unipoll.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.LayoutPollListItemBinding;
import com.androiddreamer.unipoll.util.JavaUtil;
import com.androiddreamer.unipoll.view.activity.PollDetail;

import org.json.JSONObject;

import java.util.List;

/**
 * Adapter for populating recyclerView in {@link com.androiddreamer.unipoll.view.fragment.PollListFragment}
 */
public class PollListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<JSONObject> data;
    Context context;

    public PollListAdapter(List<JSONObject> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_poll_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = ((ViewHolder) holder);
        LayoutPollListItemBinding binding = viewHolder.binding;
        JSONObject item = data.get(position);

        try{
            binding.textView5.setText(item.getString("title"));

            String tag1 = item.getJSONArray("tags").getString(0);
            String tag2 = item.getJSONArray("tags").getString(1);

            binding.chip.setText(tag1);
            binding.chip2.setText(tag2);

            binding.textView6.setText(item.getString("author"));

            double a = Math.random();
            if(a>0.6){
                binding.badgeNew.setVisibility(View.GONE);
            }

            if(item.getString("author").equalsIgnoreCase("miss jen")){
                binding.icSex.setBackgroundResource(R.drawable.ic_teacher_woman);
            }


        }catch (Exception e){

        }



        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PollDetail.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * ViewHolder holds data for state of cell-row in recyclerView
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public LayoutPollListItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
