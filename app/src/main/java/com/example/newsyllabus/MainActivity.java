package com.example.newsyllabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

/*import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;*/
import com.example.newsyllabus.syllabus.syllabusList;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //private AdView add;
    NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //Code for AdView

      /*  MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        add = findViewById(R.id.adView);
        AdRequest ad = new AdRequest.Builder().build();
        add.loadAd(ad);

        add = new AdView(this);
        add.setAdSize(AdSize.LARGE_BANNER);
        //test ad id is "ca-app-pub-3940256099942544/6300978111"
        add.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        add.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                //Toast.makeText(MainActivity.this,"Ad Loaded",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                add.loadAd(ad);

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });*/


        setSupportActionBar(toolbar);
        nav.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setCheckedItem(R.id.home);


        nav.setNavigationItemSelectedListener(this);



        CardView syllabus = findViewById(R.id.syllabus);
        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, syllabusList.class);
                startActivity(in);
            }
        });
//        CardView question = findViewById(R.id.question);
//        question.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent notes = new Intent(MainActivity.this, qbranches.class);
//                startActivity(notes);
//            }
//        });
//
//        CardView notes = findViewById(R.id.notes);
//        notes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        CardView books = findViewById(R.id.Books);
//        books.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
//            }
//        });

        CardView collage = findViewById(R.id.collage);
        collage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotUrl("https://svcetedu.org");
            }
        });

        CardView student = findViewById(R.id.student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://svceta.org/BeesERP/Login.aspx?ReturnUrl=%2fBeesERP%2fStudentLogin%2fMainStud.aspx");
            }
        });
    }

    private void gotoUrl(String p) {
        Uri uri = Uri.parse(p);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void gotUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent in = new Intent(MainActivity.this, MainActivity.class);
            startActivity(in);
            return true;

        } else if (id == R.id.share) {
            Intent in = new Intent(Intent.ACTION_SEND);
            in.setType("text/plain");
            in.putExtra(Intent.EXTRA_SUBJECT, "SVCET R-20 Syllabus is here");
            in.putExtra(Intent.EXTRA_TEXT, "link:");
            startActivity(Intent.createChooser(in, "Share Via"));
            return true;
        } else if (id == R.id.feedback) {
            Intent in = new Intent(Intent.ACTION_SEND);
            String[] receive = {"rajeevranjan11736@gmail.com"};
            in.putExtra(Intent.EXTRA_EMAIL, receive);
            in.setType("text/html");
            in.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(in, "Send E-Mail"));
            return true;
        } else if (id == R.id.privacy) {
            got("https://pages.flycricket.io/svcet/privacy.html");
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void got(String i) {
        Uri uri = Uri.parse(i);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
