package kphai.noobswe.com.laofood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    // Explicit
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fabBtn;

    TextView textViewTopic, textViewDetail;
    ImageView imvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(getString(R.string.detail));

        //InitialWidget
        textViewTopic = (TextView) findViewById(R.id.tvTopic);
        textViewDetail = (TextView) findViewById(R.id.tvDetail);
        imvShow = (ImageView) findViewById(R.id.imvShow);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);

        checkData();

        fabBtn.setOnClickListener(fabClickListener);

    } // end of onCreate----------------------------------------------------------------------------

    private void checkData() {

        Intent intent = getIntent();

        if (intent.getStringExtra("imb_sai_oua") != null) {
            imvShow.setImageResource(R.drawable.btn_sai_oua);
            textViewTopic.setText(getText(R.string.sai_oua));
            textViewDetail.setText(getText(R.string.sai_oua_detail));
        }
        if (intent.getStringExtra("imbShowKhaoNiew") != null) {
            imvShow.setImageResource(R.drawable.btn_khao_niew);
            textViewTopic.setText(getText(R.string.khaoniew));
            textViewDetail.setText(getText(R.string.detail_khao_niew));
        }
        if (intent.getStringExtra("imbShowPapaya") != null) {
            imvShow.setImageResource(R.drawable.btn_papaya);
            textViewTopic.setText(getText(R.string.papaya_salat));
            textViewDetail.setText(getText(R.string.detail_papaya));
        }
        if (intent.getStringExtra("imbShowLarb") != null) {
            imvShow.setImageResource(R.drawable.btn_larb);
            textViewTopic.setText(getText(R.string.larb));
            textViewDetail.setText(getText(R.string.detail_larb));
        }
        if (intent.getStringExtra("imbShowKhaoLarm") != null) {
            imvShow.setImageResource(R.drawable.btn_khao_larm);
            textViewTopic.setText(getText(R.string.khaolarm));
            textViewDetail.setText(getText(R.string.detail_khao_larm));
        }
        if (intent.getStringExtra("imbShowKhaoG") != null) {
            imvShow.setImageResource(R.drawable.btn_khao_g);
            textViewTopic.setText(getText(R.string.khao_g));
            textViewDetail.setText(getText(R.string.detail_khao_g));
        }
        if (intent.getStringExtra("imbShowFer") != null) {
            imvShow.setImageResource(R.drawable.btn_fer);
            textViewTopic.setText(getText(R.string.fer));
            textViewDetail.setText(getText(R.string.detail_fer));
        }
        if (intent.getStringExtra("imb_oc_larm") != null) {
            imvShow.setImageResource(R.drawable.btn_oc_larm);
            textViewTopic.setText(getText(R.string.oc_larm));
            textViewDetail.setText(getText(R.string.oc_larm_detail));
        }
        /*
        *
        * end of condition
        *
        * */

    } // end of Method checkData for show detail when user click someItem from MainActivity

    final View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onShareImage();
        }

    }; // end of OnClick FloatingActionButton for share Detail on App to another App


    // function Share
    public void onShareImage() {
        String tvTopic = textViewTopic.getText().toString();
        String tvDetail = textViewDetail.getText().toString();

        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(imvShow);
        if (bmpUri != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_TEXT, tvTopic + "\n" + tvDetail + "\n" + "Share from App #LaoFood"+ "\n" + "#StudyJams");
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            // Launch sharing dialog for image
            startActivity(Intent.createChooser(shareIntent, "Share on Social"));

        } else {
            Toast.makeText(getApplicationContext(), "share failed", Toast.LENGTH_SHORT).show();
        }
    } // end of Function onShareImage



    // Returns the URI path to the Bitmap displayed in specified ImageView
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "lao_food" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    } // end of Function Return getLocalBitmapUri


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    } //end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(DetailActivity.this, AboutActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // end of onOptionsItemSelected


} //end of Main Class-------------------------------------------------------------------------------
