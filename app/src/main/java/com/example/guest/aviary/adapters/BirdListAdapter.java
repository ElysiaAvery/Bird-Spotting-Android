package com.example.guest.aviary.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.aviary.Constants;
import com.example.guest.aviary.R;
import com.example.guest.aviary.models.Bird;
import com.example.guest.aviary.ui.BirdDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/21/16.
 */
public class BirdListAdapter extends FirebaseRecyclerAdapter<Bird, FirebaseSightedBirdViewHolder>{
    private ArrayList<Bird> mBirds = new ArrayList<>();
    private Context mContext;
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;

    public BirdListAdapter(Class<Bird> modelClass, int modelLayout, Class<FirebaseSightedBirdViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBirds.add(dataSnapshot.getValue(Bird.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setIndexInFirebase() {
        for (Bird bird : mBirds) {
            int index = mBirds.indexOf(bird);
            DatabaseReference ref = getRef(index);
            bird.setIndex(Integer.toString(index));
            ref.setValue(bird);
        }
    }

    @Override
    protected void populateViewHolder(final FirebaseSightedBirdViewHolder viewHolder, Bird model, int position) {
        viewHolder.bindBird(model);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BirdDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("birds", Parcels.wrap(mBirds));
                mContext.startActivity(intent);
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BirdDetailActivity.class);
                intent.putExtra(Constants.FIREBASE_BIRD_QUERY, Parcels.wrap(mBirds));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
