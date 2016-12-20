package com.example.guest.aviary.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.aviary.Constants;
import com.example.guest.aviary.R;
import com.example.guest.aviary.adapters.FirebaseSightedBirdViewHolder;
import com.example.guest.aviary.models.Bird;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BirdsListActivity extends AppCompatActivity {
    private DatabaseReference mBirdReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds_list);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mBirdReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BIRD_QUERY).child(uid);
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Bird, FirebaseSightedBirdViewHolder>(Bird.class, R.layout.bird_list_item, FirebaseSightedBirdViewHolder.class, mBirdReference) {
            @Override
            protected void populateViewHolder(FirebaseSightedBirdViewHolder viewHolder, Bird model, int position) {
                viewHolder.bindBird(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
