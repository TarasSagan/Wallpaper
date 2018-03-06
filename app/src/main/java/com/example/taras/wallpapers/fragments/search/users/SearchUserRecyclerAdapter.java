package com.example.taras.wallpapers.fragments.search.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.User;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchUserRecyclerAdapter extends RecyclerView.Adapter<SearchUserRecyclerAdapter.ViewHolder> {
    private final ISearchUsersContract.Presenter listener;
    private final List<User> mValues;
    private final Context context;

    public void addNewData(List<User> newData){
        int positionStart = getItemCount();
        mValues.addAll(newData);
        this.notifyItemRangeInserted(positionStart, getItemCount());
    }


    public SearchUserRecyclerAdapter(List<User> items, ISearchUsersContract.Presenter listener, Context context) {
        mValues = items;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Picasso.with(context).load(mValues.get(position).getProfileImage().getLarge())
                .placeholder(context.getDrawable(R.drawable.ic_user_black_24dp))
                .error(context.getDrawable(R.drawable.ic_error))
                .into(holder.imageProfile);
        holder.textPersonName.setText(TextUtils.isEmpty(mValues.get(position).getLastName())
                ? mValues.get(position).getFirstName()
                : mValues.get(position).getFirstName()
                + " "
                + mValues.get(position).getLastName());
        if (!TextUtils.isEmpty(mValues.get(position).getTwitterUsername())){
            holder.buttonTwitter.setVisibility(View.VISIBLE);
            holder.textPersonTwitter.setText("@" + mValues.get(position).getTwitterUsername());
        }else{ holder.buttonTwitter.setVisibility(View.INVISIBLE);}
        if(!TextUtils.isEmpty(mValues.get(position).getInstagramUsername())){
            holder.buttonInstagram.setVisibility(View.VISIBLE);
            holder.textPersonInstagran.setText("@" + mValues.get(position).getInstagramUsername());
        }else{holder.buttonInstagram.setVisibility(View.INVISIBLE);}
        holder.mView.setOnClickListener(v -> listener.onShowUser(mValues.get(position)));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public User mItem;
        @BindView(R.id.imageProfile) ImageView imageProfile;
        @BindView(R.id.buttonTwitter) Button buttonTwitter;
        @BindView(R.id.buttonInstagram) Button buttonInstagram;
        @BindView(R.id.textPersonName) TextView textPersonName;
        @BindView(R.id.textPersonTwitter) TextView textPersonTwitter;
        @BindView(R.id.textPersonInstagran) TextView textPersonInstagran;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
