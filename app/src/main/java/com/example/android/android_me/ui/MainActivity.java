package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.GridLayoutManager;
//import android.widget.GridLayout;
//import android.widget.GridView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
//import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by User1 on 9/9/2017.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int head_index, body_index, leg_index;

    private boolean horizontal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            horizontal = true;

            Button next_button = (Button) findViewById(R.id.master_list_next_button);
            next_button.setVisibility(View.GONE);

            GridView grid = (GridView) findViewById(R.id.images_grid_view);
            grid.setNumColumns(2);

            if (savedInstanceState == null) {
                FragmentManager manager = getSupportFragmentManager();

                BodyPartFragment head = new BodyPartFragment();
                head.setPart_list(AndroidImageAssets.getHeads());

                BodyPartFragment body = new BodyPartFragment();
                body.setPart_list(AndroidImageAssets.getBodies());

                BodyPartFragment legs = new BodyPartFragment();
                legs.setPart_list(AndroidImageAssets.getLegs());

                manager.beginTransaction().add(R.id.head_container, head).commit();
                manager.beginTransaction().add(R.id.body_container, body).commit();
                manager.beginTransaction().add(R.id.leg_container, legs).commit();
            }
        }
        else horizontal = false;

    }

    @Override
    public void OnImageSelected(int position) {
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        int part_type = position / 12;
        int index = position % 12;

        if (!horizontal) {

            switch (part_type) {
                case 0:
                    head_index = index;
                    break;
                case 1:
                    body_index = index;
                    break;
                case 2:
                    leg_index = index;
                    break;
            }

            Bundle b = new Bundle();
            b.putInt("HEAD_INDEX", head_index);
            b.putInt("BODY_INDEX", body_index);
            b.putInt("LEG_INDEX", leg_index);

            final Intent i = new Intent(this, AndroidMeActivity.class);
            i.putExtras(b);

            Button next_button = (Button) findViewById(R.id.master_list_next_button);
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(i);
                }
            });

        } else {
            int container = -1;
            BodyPartFragment fragment = new BodyPartFragment();
            fragment.setPart_index(index);

            switch (part_type) {
                case 0:
                    container = R.id.head_container;
                    fragment.setPart_list(AndroidImageAssets.getHeads());
                    break;
                case 1:
                    container = R.id.body_container;
                    fragment.setPart_list(AndroidImageAssets.getBodies());
                    break;
                case 2:
                    container = R.id.leg_container;
                    fragment.setPart_list(AndroidImageAssets.getLegs());
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(container, fragment).commit();
        }
    }
}
