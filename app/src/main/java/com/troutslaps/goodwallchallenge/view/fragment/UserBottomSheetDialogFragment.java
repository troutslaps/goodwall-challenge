package com.troutslaps.goodwallchallenge.view.fragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.scopely.fontain.Fontain;
import com.scopely.fontain.interfaces.FontFamily;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.databinding.FragmentContactActionsBinding;
import com.troutslaps.goodwallchallenge.viewmodel.UserViewModel;

/**
 * Created by duchess on 19/02/2017.
 */

public class UserBottomSheetDialogFragment extends BottomSheetDialogFragment {

    FragmentContactActionsBinding binding;
    UserViewModel userViewModel;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new
            BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss();
                    }

                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            };


    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout
                .fragment_contact_actions, null, false);
        binding.setUserViewModel(userViewModel);
        FontFamily family = Fontain.getFontFamily("Karla");
        Fontain.applyFontFamilyToViewHierarchy(binding.getRoot(), family);
        dialog.setContentView(binding.getRoot());

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View)
                binding.getRoot().getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }


}
