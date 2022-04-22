package com.nestowl.CommenDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nestowl.brokerapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomChatingDio extends BottomSheetDialogFragment {
    private clicks mclik;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_attachement,container, false);

        ImageView gallery = view.findViewById(R.id.BOTTOM_GALLERY);
        ImageView docs = view.findViewById(R.id.BOTTOM_DOCS);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mclik.onBottonClick(1);
                dismiss();
            }
        });
        docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mclik.onBottonClick(2);
                dismiss();
            }
        });

        return view;
    }
    public interface clicks{
        void onBottonClick(Integer id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mclik = (clicks) context;
    }
}
