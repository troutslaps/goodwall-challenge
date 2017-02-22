package com.troutslaps.goodwallchallenge.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scopely.fontain.Fontain;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.databinding.ItemAchievementBinding;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;

import java.util.List;

/**
 * Created by duchess on 12/02/2017.
 */

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.BindingHolder> {

    Context context;
    List<Achievement> achievements;
    private AchievementViewModel.Listener listener;

    public AchievementAdapter(Context context, List<Achievement> achievements) {
        super();
        this.context = context;
        this.listener = (AchievementViewModel.Listener) context;
        this.achievements = achievements;
    }

    @Override
    public AchievementAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAchievementBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent
                .getContext()), R.layout.item_achievement, parent, false);
        binding.setAchievementViewModel(new AchievementViewModel(context, null, listener));
        Fontain.applyFontFamilyToViewHierarchy(binding.getRoot(), Fontain.getFontFamily(Constants
                .View.DefaultFont));
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(AchievementAdapter.BindingHolder holder, int position) {
        ItemAchievementBinding postBinding = holder.binding;
        AchievementViewModel vm = postBinding.getAchievementViewModel();
        vm.setAchievement(achievements.get(position));
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        ItemAchievementBinding binding;

        public BindingHolder(ItemAchievementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
