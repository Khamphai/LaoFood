package kphai.noobswe.com.laofood;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ImageButton imb_sai_oua,
            imbShowKhaoNiew,
            imbShowPapaya,
            imbShowLarb,
            imbShowKhaoLarm,
            imbShowKhaoG,
            imbShowFer,
            imb_oc_larm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();
        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources()
                    .updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_main);

        initInstances();
        clickListener();

    } // end of onCreate----------------------------------------------------------------------------

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));

    } // end of Method initInstances

    private void clickListener() {

        //InitialWidget
        imb_sai_oua = (ImageButton)
                findViewById(R.id.imb_show_sai_oua);
        imbShowKhaoNiew = (ImageButton)
                findViewById(R.id.imb_show_khao_niew);
        imbShowPapaya = (ImageButton)
                findViewById(R.id.imb_show_papaya);
        imbShowLarb = (ImageButton)
                findViewById(R.id.imb_show_larb);
        imbShowKhaoLarm = (ImageButton)
                findViewById(R.id.imb_show_khao_larm);
        imbShowKhaoG = (ImageButton)
                findViewById(R.id.imb_show_khao_g);
        imbShowFer = (ImageButton)
                findViewById(R.id.imb_show_fer);
        imb_oc_larm = (ImageButton)
                findViewById(R.id.imb_show_oc_larm);


        imb_sai_oua.setOnClickListener(this);
        imbShowKhaoNiew.setOnClickListener(this);
        imbShowPapaya.setOnClickListener(this);
        imbShowLarb.setOnClickListener(this);
        imbShowKhaoLarm.setOnClickListener(this);
        imbShowKhaoG.setOnClickListener(this);
        imbShowFer.setOnClickListener(this);
        imb_oc_larm.setOnClickListener(this);

    } // end of Method clickListener


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        switch (v.getId()) {
            case R.id.imb_show_sai_oua:
                intent.putExtra("imb_sai_oua", getString(R.string.sai_oua));
                startActivity(intent);
                break;
            case R.id.imb_show_khao_niew:
                intent.putExtra("imbShowKhaoNiew", getString(R.string.khaoniew));
                startActivity(intent);
                break;
            case R.id.imb_show_papaya:
                intent.putExtra("imbShowPapaya", getString(R.string.papaya_salat));
                startActivity(intent);
                break;
            case R.id.imb_show_larb:
                intent.putExtra("imbShowLarb", getString(R.string.larb));
                startActivity(intent);
                break;
            case R.id.imb_show_khao_larm:
                intent.putExtra("imbShowKhaoLarm", getString(R.string.khaolarm));
                startActivity(intent);
                break;
            case R.id.imb_show_khao_g:
                intent.putExtra("imbShowKhaoG", getString(R.string.khao_g));
                startActivity(intent);
                break;
            case R.id.imb_show_fer:
                intent.putExtra("imbShowFer", getString(R.string.fer));
                startActivity(intent);
                break;
            case R.id.imb_show_oc_larm:
                intent.putExtra("imb_oc_larm", getString(R.string.oc_larm));
                startActivity(intent);
                break;
            default:
                Toast.makeText(MainActivity.this, "Please Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }   //Handled OnClicked

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } //end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_change_language) {
            showChangeLanguageDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    } //end of onOptionsItemSelected

    private void showChangeLanguageDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.language_dialog, null);
        dialogBuilder.setView(dialogView);

        final Spinner spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);

        dialogBuilder.setTitle(getResources().getString(R.string.lang_dialog_title));
        dialogBuilder.setIcon(R.drawable.ic_language_teal_500_18dp);
        dialogBuilder.setMessage(getResources().getString(R.string.lang_dialog_message));
        dialogBuilder.setPositiveButton(getResources()
                        .getString(R.string.change),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        int languageType = spinner1.getSelectedItemPosition();
                        switch(languageType) {
                            case 0: //English
                                PreferenceManager
                                        .getDefaultSharedPreferences(getApplicationContext())
                                        .edit().putString("LANG", "en").apply();
                                setLangRecreate("en");
                                return;
                            case 1: //Lao
                                PreferenceManager
                                        .getDefaultSharedPreferences(getApplicationContext())
                                        .edit().putString("LANG", "lo").apply();
                                setLangRecreate("lo");
                                return;
                            default: //By default set to english
                                PreferenceManager
                                        .getDefaultSharedPreferences(getApplicationContext())
                                        .edit().putString("LANG", "en").apply();
                                setLangRecreate("en");
                                return;
                        }
                    }
                });
        dialogBuilder.setNegativeButton(getResources()
                        .getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //pass
                    }
                });
        AlertDialog b = dialogBuilder.create();
        b.show();
    } // end of Method showChangeLanguageDialog

    public void setLangRecreate(String language) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources()
                .updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
        recreate();
    } // end of Method setLangRecreate


} // end of Main Class------------------------------------------------------------------------------
