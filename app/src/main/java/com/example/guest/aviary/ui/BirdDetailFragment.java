package com.example.guest.aviary.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.aviary.R;
import com.example.guest.aviary.models.Bird;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

        if(mBird.getImageUrl().equals("not_specified")) {
            Picasso.with(view.getContext())
                    .load("http://borderspringsfarm.com/shop/wp-content/uploads/2013/06/no-image-yet1.jpg")
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mBirdImageView);
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

    @Override
    public void onClick(View v) {
        if(v == mInfoTextView) {
            String webName = mBird.getName().toString().replace(" ", "-");
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.audubon.org/field-guide/bird/" + webName));
            startActivity(webIntent);
        }
    }

}
