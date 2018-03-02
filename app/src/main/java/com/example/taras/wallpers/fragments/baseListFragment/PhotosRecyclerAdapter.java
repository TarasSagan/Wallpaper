package com.example.taras.wallpers.fragments.baseListFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosRecyclerAdapter extends RecyclerView.Adapter<PhotosRecyclerAdapter.ViewHolder> {
    private List<PhotoItem> mValues;
    private final ListFragmentContract.FragmentPresenter mListener;
    private final Context context;

    public void addNewData(List<PhotoItem> newData){
        int positionStart = getItemCount();
        mValues.addAll(newData);
        this.notifyItemRangeInserted(positionStart, getItemCount());
    }

    public PhotosRecyclerAdapter(List<PhotoItem> items, ListFragmentContract.FragmentPresenter listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        bindImages(holder, position);
        bindTextData(holder, position);
        setOnClick(holder, position);
    }

    private void bindImages(final ViewHolder holder, int position){
        Picasso.with(context).load(mValues.get(position).getUrls().getRegular())
                .placeholder(context.getDrawable(R.drawable.ic_image_placeholder))
                .error(context.getDrawable(R.drawable.ic_error))
                .into(holder.imageView);
        Picasso.with(context).load(mValues.get(position).getUser().getProfileImage().getLarge())
                .into(holder.imageViewProfileImage);
    }
    private void bindTextData(final ViewHolder holder, int position){
        holder.textViewProfileName.setText(TextUtils.isEmpty(mValues.get(position).getUser().getLastName())
                ? mValues.get(position).getUser().getFirstName()
                : mValues.get(position).getUser().getFirstName()
                + " "
                + mValues.get(position).getUser().getLastName());

        if (!TextUtils.isEmpty(mValues.get(position).getUser().getTwitterUsername())) {
            holder.textViewProfileOnTwitter.setText("@" + mValues.get(position).getUser().getTwitterUsername());
        }
        holder.buttonLikedByUser.setBackgroundResource(mValues.get(position).isLikedByUser()
                ? R.drawable.ic_like_from_user
                : R.drawable.ic_like);
        holder.textViewLike.setText(Long.toString(mValues.get(position).getLikes()));
    }

    private void setOnClick(final ViewHolder holder, int position){
        holder.imageView.setOnClickListener(v -> mListener.onShowPhotoDetails(holder.mItem));
        holder.textViewProfileName.setOnClickListener(v -> mListener.onShowUser(holder.mItem));
        holder.imageViewProfileImage.setOnClickListener(v -> mListener.onShowUser(holder.mItem));
        holder.buttonDownload.setOnClickListener(v -> mListener.onDownload(holder.mItem));
        holder.buttonLikedByUser.setOnClickListener(v -> {
            holder.buttonLikedByUser.setBackgroundResource(mValues.get(position).isLikedByUser()
                    ? R.drawable.ic_like
                    : R.drawable.ic_like_from_user);
            mListener.onLike(holder.mItem, position);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.imageViewProfileImage) ImageView imageViewProfileImage ;
        @BindView(R.id.textViewLike) TextView textViewLike ;
        @BindView(R.id.textViewProfileName) TextView textViewProfileName ;
        @BindView(R.id.textViewProfileOnTwitter) TextView textViewProfileOnTwitter ;
        @BindView(R.id.buttonDownload) Button buttonDownload ;
        @BindView(R.id.buttonLikedByUser) Button buttonLikedByUser ;
        public PhotoItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
