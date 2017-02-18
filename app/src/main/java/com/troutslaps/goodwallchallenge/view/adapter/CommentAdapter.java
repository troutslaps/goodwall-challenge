package com.troutslaps.goodwallchallenge.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.scopely.fontain.Fontain;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.databinding.ItemAchievementBinding;
import com.troutslaps.goodwallchallenge.databinding.ItemCommentBinding;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;
import com.troutslaps.goodwallchallenge.viewmodel.CommentViewModel;

import java.util.List;


/**
 * Created by duchess on 12/02/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.BindingHolder> {
    Context context;
    List<Comment> comments;
    CommentViewModel.Listener listener;

    public CommentAdapter(Context context, List<Comment> comments) {
        super();
        this.context = context;
        this.listener = (CommentViewModel.Listener) context;
        this.comments = comments;
    }


    @Override
    public CommentAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCommentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent
                .getContext()), R.layout.item_comment, parent, false);
        Fontain.applyFontFamilyToViewHierarchy(binding.getRoot(), Fontain.getFontFamily("Karla"));
        return new CommentAdapter.BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.BindingHolder holder, int position) {
        ItemCommentBinding postBinding = holder.binding;
        postBinding.setCommentViewModel(new CommentViewModel(comments.get
                (position), context, listener));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        ItemCommentBinding binding;

        public BindingHolder(ItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
