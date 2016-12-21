package com.example.guest.aviary.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.aviary.Constants;
import com.example.guest.aviary.R;
import com.example.guest.aviary.models.Bird;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirdDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.birdImageView)
    ImageView mBirdImageView;
    @Bind(R.id.nameTextView)
    TextView mNameTextView;
    @Bind(R.id.familyTextView) TextView mFamilyTextView;
    @Bind(R.id.genderTextView) TextView mGenderTextView;
    @Bind(R.id.infoTextView) TextView mInfoTextView;
    @Bind(R.id.photoButton)
    Button mPhotoButton;
    @Bind(R.id.audioButton) Button mAudioButton;

    private static final int REQUEST_IMAGE_CAPTURE = 111;

    private Bird mBird;


    public BirdDetailFragment() {
        // Required empty public constructor
    }

    public static BirdDetailFragment newInstance(Bird restaurant) {
        BirdDetailFragment restaurantDetailFragment = new BirdDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("birds", Parcels.wrap(restaurant));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBird = Parcels.unwrap(getArguments().getParcelable("birds"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_detail, container, false);
        ButterKnife.bind(this, view);

        if(!mBird.getImageUrl().contains("not_specified")) {
            try{
                Bitmap image = decodeFromFirebaseBase64(mBird.getImageUrl());
                mBirdImageView.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Picasso.with(view.getContext())
                    .load(String.valueOf(mBird.getImageUrl()))
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mBirdImageView);
        }

        mNameTextView.setText(mBird.getName());
        mFamilyTextView.setText(mBird.getFamily());
        mGenderTextView.setText(mBird.getGender());
        mInfoTextView.setText("Get Further information about the " + mBird.getName() + "...");

        mInfoTextView.setOnClickListener(this);
        mPhotoButton.setOnClickListener(this);
        mAudioButton.setOnClickListener(this);

        return view;
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onClick(View v) {
        if(v == mInfoTextView) {
            String webName = mBird.getName().toString().replace(" ", "-");
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.audubon.org/field-guide/bird/" + webName));
            startActivity(webIntent);
        } else if(v == mPhotoButton) {
            onLaunchCamera();

        } else if(v == mAudioButton) {

        }
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mBirdImageView.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_BIRD_QUERY)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(mBird.getPushId())
                .child("imageUrl");
        ref.setValue(imageEncoded);
    }

}
