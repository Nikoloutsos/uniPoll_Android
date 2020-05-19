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
import com.androiddreamer.unipoll.view.activity.PollDetail;
import com.androiddreamer.unipoll.view.fragment.ActivePollListFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Adapter for populating recyclerView in {@link ActivePollListFragment}
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

            String tag1 = item.getString("tag");

            binding.chip.setText(tag1);
            binding.chip2.setVisibility(View.GONE);

            binding.textView6.setText(item.getString("author"));




        }catch (Exception e){

        }



        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(context, PollDetail.class);
                    intent.putExtra("id", item.getInt("id"));
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
