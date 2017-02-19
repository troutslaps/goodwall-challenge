package com.troutslaps.goodwallchallenge.viewmodel;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by duchess on 16/02/2017.
 */

public interface PostViewModelInterface {

    Drawable getProfilePlaceholder();

    String getAuthorProfilePhoto();

    String getAuthorName();

    String getCommentBody();

    String getCommentTime();

    View.OnClickListener onAuthorClicked();
}
