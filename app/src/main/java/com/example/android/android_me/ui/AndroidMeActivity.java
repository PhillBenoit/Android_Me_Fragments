/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        BodyPartFragment head = new BodyPartFragment();
        head.setPart_list(AndroidImageAssets.getHeads());
        FragmentManager head_manager = getSupportFragmentManager();

        BodyPartFragment body = new BodyPartFragment();
        body.setPart_list(AndroidImageAssets.getBodies());
        FragmentManager body_manager = getSupportFragmentManager();

        BodyPartFragment legs = new BodyPartFragment();
        legs.setPart_list(AndroidImageAssets.getLegs());
        FragmentManager leg_manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            head.setPart_index(1);
            body.setPart_index(1);
            legs.setPart_index(1);
        } else {
            Intent i = getIntent();
            Bundle b = i.getExtras();
            head.setPart_index(b.getInt("HEAD_INDEX"));
            body.setPart_index(b.getInt("BODY_INDEX"));
            legs.setPart_index(b.getInt("LEG_INDEX"));
        }

        head_manager.beginTransaction().add(R.id.head_container, head).commit();
        body_manager.beginTransaction().add(R.id.body_container, body).commit();
        leg_manager.beginTransaction().add(R.id.leg_container, legs).commit();
    }
}
