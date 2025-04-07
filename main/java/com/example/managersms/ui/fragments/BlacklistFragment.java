package com.example.managersms.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.managersms.R;
import com.example.managersms.adapters.BlacklistAdapter;
import com.example.managersms.data.BlacklistDatabaseHelper;
import com.example.managersms.models.BlacklistEntry;
import java.util.ArrayList;
import java.util.List;

public class BlacklistFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<BlacklistEntry> blacklist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blacklist, container, false);
        recyclerView = view.findViewById(R.id.blacklist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadBlacklist();

        return view;
    }

    private void loadBlacklist() {
        BlacklistDatabaseHelper db = BlacklistDatabaseHelper.getInstance(getContext());
        // Giả định: có phương thức getAllBlacklist() trả về danh sách
        // Ở đây thêm giả lập thủ công:
        blacklist.clear();
        blacklist.add(new BlacklistEntry("0123456789"));
        blacklist.add(new BlacklistEntry("0987654321"));
        BlacklistAdapter adapter = new BlacklistAdapter(blacklist, getContext(), entry -> {
            // Xử lý click: xóa khỏi danh sách hoặc xem lịch sử
        });
        recyclerView.setAdapter(adapter);
    }
}