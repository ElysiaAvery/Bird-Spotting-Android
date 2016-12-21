package com.example.guest.aviary.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.aviary.Constants;
import com.example.guest.aviary.R;
import com.example.guest.aviary.models.Bird;
import com.example.guest.aviary.ui.BirdDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Guest on 12/20/16.
 */
public class FirebaseSightedBirdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 225;
    private static final int MAX_HEIGHT = 225;

    View mView;
    Context mContext;

    public FirebaseSightedBirdViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBird(Bird bird) {
        TextView mNameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView mGenderTextView = (TextView) mView.findViewById(R.id.genderTextView);
        TextView mUserEmailTextView = (TextView) mView.findViewById(R.id.userEmailTextView);
        TextView mAddressTextView = (TextView) mView.findViewById(R.id.addressTextView);
        ImageView mBirdImageView = (ImageView) mView.findViewById(R.id.birdImageView);

        if (!bird.getImageUrl().contains("not_specified")) {
            try {
                Bitmap imageBitmap = decodeFromFirebaseBase64(bird.getImageUrl());
                mBirdImageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Picasso.with(mContext)
                    .load(String.valueOf(bird.getImageUrl()))
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mBirdImageView);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            mNameTextView.setText(bird.getName());
            mGenderTextView.setText(bird.getGender());
            mUserEmailTextView.setText("Spotted By: " + user.getEmail());
            mAddressTextView.setText(bird.getCity() + ", " + bird.getState() + " " + bird.getZip());
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mNameTextView.setText(bird.getName());
        mGenderTextView.setText(bird.getGender());
        mUserEmailTextView.setText("Spotted By: " + user.getEmail());
        mAddressTextView.setText(bird.getCity() + ", " + bird.getState() + " " + bird.getZip());
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Bird> birds = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BIRD_QUERY).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    birds.add(snapshot.getValue(Bird.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BirdDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("birds", Parcels.wrap(birds));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
