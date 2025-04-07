package com.example.managersms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.managersms.R;
import com.example.managersms.models.BlacklistEntry;
import java.util.List;

public class BlacklistAdapter extends RecyclerView.Adapter<BlacklistAdapter.BlacklistViewHolder> {
    private List<BlacklistEntry> blacklist;
    private Context context;
    private OnBlacklistClickListener listener;

    public interface OnBlacklistClickListener {
        void onItemClick(BlacklistEntry entry);
    }

    public BlacklistAdapter(List<BlacklistEntry> blacklist, Context context, OnBlacklistClickListener listener) {
        this.blacklist = blacklist;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BlacklistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blacklist, parent, false);
        return new BlacklistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlacklistViewHolder holder, int position) {
        BlacklistEntry entry = blacklist.get(position);
        holder.phoneNumber.setText(entry.getPhoneNumber());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(entry));
    }

    @Override
    public int getItemCount() {
        return blacklist.size();
    }

    public static class BlacklistViewHolder extends RecyclerView.ViewHolder {
        TextView phoneNumber;
        public BlacklistViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneNumber = itemView.findViewById(R.id.blacklist_phone);
        }
    }
}