package com.troutslaps.goodwallchallenge.viewmodel;

import android.view.View;

/**
 * Created by duchess on 16/02/2017.
 */

public interface PostViewModelInterface {

    public String getAuthorName();
    public String getCommentBody();
    public String getCommentTime();
    public View.OnClickListener onAuthorNameClicked();
}
