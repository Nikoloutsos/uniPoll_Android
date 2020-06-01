package com.androiddreamer.unipoll.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.LayoutPollListItemBinding;
import com.androiddreamer.unipoll.view.activity.CompletePollDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PollListSuperUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Context context;
    List<JSONObject> data;


    public PollListSuperUserAdapter(Context context, List<JSONObject> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_poll_list_super_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JSONObject item = data.get(position);

        TextView questionTV = holder.itemView.findViewById(R.id.question_tv);
        TextView expTimeTV = holder.itemView.findViewById(R.id.exp_time_value_tv);

        try {
            String title = item.getString("title");
            String timeEnded = item.getString("time_ended");

            questionTV.setText(title);
            expTimeTV.setText(timeEnded);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, CompletePollDetailActivity.class);
                    intent.putExtra("id", item.getInt("poll_id"));
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

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
