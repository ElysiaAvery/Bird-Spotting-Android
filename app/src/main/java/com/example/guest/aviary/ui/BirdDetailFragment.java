package com.example.guest.aviary.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.aviary.Constants;
import com.example.guest.aviary.R;
import com.example.guest.aviary.models.Bird;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ShortBuffer;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    @Bind(R.id.playAudio) Button mPlayAudio;
    @Bind(R.id.stopAudio) Button mStopAudio;
    private static String mFileName = null;
    private MediaRecorder mRecorder = null;

    private static final int REQUEST_IMAGE_CAPTURE = 111;

    private Bird mBird;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    StorageReference storageRef = null;
    MediaPlayer mMediaPlayer;
    Uri downloadUrl;



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
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/birdRecording.3gp";
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(getActivity());
        mAudioButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    startRecording();
                    Log.e("recording", "start");

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopRecording();
                    Log.e("recording", "stop");
                }
                return false;
            }
        });

        mPlayAudio.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        getAudio();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("play", "start");

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mMediaPlayer.release();
                }
                return false;
            }
        });

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

        mNameTextView.setText(mBird.getName() + ", ");
        mFamilyTextView.setText(mBird.getFamily());
        mGenderTextView.setText(mBird.getGender());
        mInfoTextView.setText("Get Further information about the " + mBird.getName() + "...");

        mInfoTextView.setOnClickListener(this);
        mPhotoButton.setOnClickListener(this);
        mStopAudio.setOnClickListener(this);

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

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("this", "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        uploadAudio();
    }

    private void uploadAudio() {
        mProgress.setMessage("uploading audio...");
        mProgress.show();
        StorageReference filepath = mStorage.child("Audio").child("birdRecording.3gp");

        Uri uri = Uri.fromFile(new File(mFileName));

        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mProgress.dismiss();
                downloadUrl = taskSnapshot.getDownloadUrl();
                Log.e("here", downloadUrl.toString());
            }
        });
    }

    private void getAudio() throws IOException {
        String url = downloadUrl.toString() ;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDataSource(url);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.prepare(); // might take long! (for buffering, etc)
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMediaPlayer.start();
            }
        }, 1500);

    }

}