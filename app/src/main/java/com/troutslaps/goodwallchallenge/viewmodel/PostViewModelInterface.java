package com.troutslaps.goodwallchallenge.viewmodel;

import android.databinding.Bindable;
import android.view.View;

/**
 * Created by duchess on 16/02/2017.
 */

public interface PostViewModelInterface {
    String getAuthorProfilePhoto();

    String getAuthorName();

    String getCommentBody();

    String getCommentTime();

    View.OnClickListener onAuthorNameClicked();
}
