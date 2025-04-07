package com.example.managersms.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.managersms.R;

public class MessageFragment extends Fragment {
    private EditText edtPhone, edtContent;
    private Button btnSend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        edtPhone = view.findViewById(R.id.edt_phone);
        edtContent = view.findViewById(R.id.edt_message);
        btnSend = view.findViewById(R.id.btn_send_sms);

        btnSend.setOnClickListener(v -> {
            String phone = edtPhone.getText().toString();
            String content = edtContent.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null));
            intent.putExtra("sms_body", content);
            startActivity(intent);
        });

        return view;
    }
}