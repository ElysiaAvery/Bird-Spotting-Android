package com.example.guest.aviary.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.aviary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener{
    @Bind(R.id.header) TextView mHeader;
    @Bind(R.id.addText) TextView mAddText;
    @Bind(R.id.sightingButton) Button mSightingButton;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int REQUEST_IMAGE_CAPTURE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Typeface elegantFont = Typeface.createFromAsset(getAssets(), "fonts/AquilineTwo.ttf");
        mHeader.setTypeface(elegantFont);
        Typeface sansFont = Typeface.createFromAsset(getAssets(), "fonts/valencia.otf");
        mAddText.setTypeface(sansFont);
        Typeface regularFont = Typeface.createFromAsset(getAssets(), "fonts/regular.otf");
        mSightingButton.setTypeface(regularFont);

        mSightingButton.setOnClickListener(this);

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final View header = navigationView.getHeaderView(0);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mFirebaseUser != null) {
                    String fireEmail = mFirebaseUser.getEmail();
                    TextView email = (TextView) header.findViewById(R.id.userEmailHeader);
                    email.setText(fireEmail);
                } else {

                }
            }
        };
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mSightingButton) {
            Intent intent = new Intent(MainActivity.this, NewBirdActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_form) {
            Intent intent = new Intent(MainActivity.this, NewBirdActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, BirdsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
