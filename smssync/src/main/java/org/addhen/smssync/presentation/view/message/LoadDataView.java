package org.addhen.smssync.presentation.view.message;


import android.support.annotation.UiThread;

import com.addhen.android.raiburari.presentation.view.UiView;

public interface LoadDataView extends UiView {

    /**
     * Shows a view with a progress bar indicating a loading process.
     */
    @UiThread
    void showLoading();

    /**
     * Hides a loading view.
     */
    @UiThread void hideLoading();

    /**
     * Shows a retry view in case of an error when retrieving data.
     */
    @UiThread void showRetry();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    @UiThread void hideRetry();
}
