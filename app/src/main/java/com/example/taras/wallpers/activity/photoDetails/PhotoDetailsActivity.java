package com.example.taras.wallpers.activity.photoDetails;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.photoDetails.PhotoDetailsResponse;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailsActivity extends MvpActivity<PhotoDetailsContract.View, PhotoDetailsPresenter>
        implements PhotoDetailsContract.View{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.buttonLocation) Button buttonLovation;
    @BindView(R.id.imageViewAppBar) ImageView imageViewAppBar;
    @BindView(R.id.textDALocation) TextView textDALocation;
    @BindView(R.id.textDADescription) TextView textDADescription;
    @BindView(R.id.textDAWH) TextView textDAWH;
    @BindView(R.id.textDAmake) TextView textDAmake;
    @BindView(R.id.textDAmodel) TextView textDAmodel;
    @BindView(R.id.textDAExposureTime) TextView textDAExposureTime;
    @BindView(R.id.textDAAperture) TextView textDAAperture;
    @BindView(R.id.textDAfocalLength) TextView textDAfocalLength;
    @BindView(R.id.textDAiso) TextView textDAiso;
    @BindView(R.id.textDAColor) TextView textDAColor;
    @BindView(R.id.textDADownloadsVal) TextView textDADownloadsVal;
    @BindView(R.id.textDAViewsVal) TextView textDAViewsVal;
    @BindView(R.id.textDALikesVal) TextView textDALikesVal;
    public static final String DETAIL_KEY = "PhotoDetailsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getPresenter().getDetails();
    }

    @NonNull
    @Override
    public PhotoDetailsPresenter createPresenter() {
        return new PhotoDetailsPresenter(getIntent().getStringExtra(DETAIL_KEY));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showDetails(PhotoDetailsResponse details) {
        Picasso.with(this).load(details.getUrls().getRegular())
                .placeholder(this.getDrawable(R.drawable.ic_image_placeholder))
                .error(this.getDrawable(R.drawable.ic_error))
                .into(imageViewAppBar);
        if(!TextUtils.isEmpty(buildLocationString(details))) {
            textDALocation.setText(buildLocationString(details));
        }else {buttonLovation.setVisibility(View.INVISIBLE);}
        if(!TextUtils.isEmpty(details.getDescription())){
            textDADescription.setText(details.getDescription());
        }
        textDAViewsVal.setText(details.getViews());
        textDADownloadsVal.setText(details.getDownloads());
        textDALikesVal.setText(details.getLikes());

        textDAWH.setText(getString(R.string.detailActivity_dimensions) + ": \n" +
                details.getWidth().toString() + " x " + details.getHeight().toString());
        textDAmake.setText(!TextUtils.isEmpty(details.getExif().getMake())
               ? getString(R.string.detailActivity_make) + ": \n" + details.getExif().getMake()
               : getString(R.string.detailActivity_make)+ ": ");
        textDAmodel.setText(!TextUtils.isEmpty(details.getExif().getModel())
               ? getString(R.string.detailActivity_model) + ": \n" + details.getExif().getModel()
               : getString(R.string.detailActivity_model)+ ": ");
        textDAExposureTime.setText(!TextUtils.isEmpty(details.getExif().getExposureTime())
               ? getString(R.string.detailActivity_exposureTime) + ": \n" + details.getExif().getExposureTime() + "s"
               : getString(R.string.detailActivity_exposureTime)+ ": ");
        textDAAperture.setText(!TextUtils.isEmpty(details.getExif().getAperture())
               ? getString(R.string.detailActivity_aperture) + ": \n" + "ƒ/" + details.getExif().getAperture()
               : getString(R.string.detailActivity_aperture)+ ": ");
        textDAfocalLength.setText(!TextUtils.isEmpty(details.getExif().getFocalLength())
               ? getString(R.string.detailActivity_focalLength) + ": \n" + details.getExif().getFocalLength() + "mm"
               : getString(R.string.detailActivity_focalLength)+ ": ");
        textDAiso.setText(!TextUtils.isEmpty(details.getExif().getIso())
               ? getString(R.string.detailActivity_iso) + ": \n" + details.getExif().getIso()
               : getString(R.string.detailActivity_iso)+ ": ");
        textDAColor.setText(!TextUtils.isEmpty(details.getColor())
               ? getString(R.string.detailActivity_color) + ": \n" + details.getColor()
               : getString(R.string.detailActivity_color)+ ": ");



    }
    private String buildLocationString(PhotoDetailsResponse details){

        StringBuilder builder = new StringBuilder();
        if(details.getLocation() != null) {
            if (!TextUtils.isEmpty(details.getLocation().getCity())) {
                builder.append(details.getLocation().getCity());
            }
            if (builder.length() != 0) {
                builder.append(", ");
            }
            if (!TextUtils.isEmpty(details.getLocation().getCountry())) {
                builder.append(details.getLocation().getCountry());
            }
        }
        return builder.toString();
    }

    @Override
    public void showMessage(String info) {
        Snackbar.make(imageViewAppBar, info, Snackbar.LENGTH_LONG).show();
    }
}
