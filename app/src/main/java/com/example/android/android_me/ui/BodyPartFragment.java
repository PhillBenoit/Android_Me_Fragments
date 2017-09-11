package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User1 on 9/7/2017.
 */

public class BodyPartFragment extends Fragment {

    private List<Integer> part_list;
    private int part_index;

    public static final String PART_LIST_LABEL = "PART_LIST";
    public static final String PART_INDEX_LABEL = "PART_INDEX";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(PART_INDEX_LABEL) &&
                savedInstanceState.containsKey(PART_LIST_LABEL)) {
            part_list = savedInstanceState.getIntegerArrayList(PART_LIST_LABEL);
            part_index = savedInstanceState.getInt(PART_INDEX_LABEL);
        }


        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView body_part = (ImageView) rootView.findViewById(R.id.body_part_imageview);

        //body_part.setImageResource(AndroidImageAssets.getHeads().get(0));
        if (part_list != null) body_part.setImageResource(part_list.get(part_index));
        else Log.d("TESTBUG", "Body part list is null");

        body_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                part_index++;
                if (part_index == part_list.size()) part_index = 0;
                body_part.setImageResource(part_list.get(part_index));
            }
        });


        return rootView;
    }

    public BodyPartFragment() {

    }

    public int getPart_index() {
        return part_index;
    }

    public void setPart_index(int part_index) {
        this.part_index = part_index;
    }

    public List<Integer> getPart_list() {
        return part_list;
    }

    public void setPart_list(List<Integer> part_list) {
        this.part_list = part_list;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(PART_LIST_LABEL, (ArrayList<Integer>) part_list);
        outState.putInt(PART_INDEX_LABEL, part_index);
    }
}
