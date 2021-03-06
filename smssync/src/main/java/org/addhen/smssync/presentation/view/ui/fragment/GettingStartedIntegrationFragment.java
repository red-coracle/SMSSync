/*
 * Copyright (c) 2010 - 2015 Ushahidi Inc
 * All rights reserved
 * Contact: team@ushahidi.com
 * Website: http://www.ushahidi.com
 * GNU Lesser General Public License Usage
 * This file may be used under the terms of the GNU Lesser
 * General Public License version 3 as published by the Free Software
 * Foundation and appearing in the file LICENSE.LGPL included in the
 * packaging of this file. Please review the following information to
 * ensure the GNU Lesser General Public License version 3 requirements
 * will be met: http://www.gnu.org/licenses/lgpl.html.
 *
 * If you have questions regarding the use of this file, please contact
 * Ushahidi developers at team@ushahidi.com.
 */

package org.addhen.smssync.presentation.view.ui.fragment;

import com.addhen.android.raiburari.presentation.view.ui.fragment.BaseFragment;

import org.addhen.smssync.R;
import org.addhen.smssync.presentation.view.ui.activity.ListWebServiceActivity;

import butterknife.OnClick;

/**
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class GettingStartedIntegrationFragment extends BaseFragment {

    private static GettingStartedIntegrationFragment mIntegrationFragment;

    public GettingStartedIntegrationFragment() {
        super(R.layout.fragment_getting_started_add_integration, 0);
    }

    public static GettingStartedIntegrationFragment newInstance() {
        if (mIntegrationFragment == null) {
            mIntegrationFragment = new GettingStartedIntegrationFragment();
        }
        return mIntegrationFragment;
    }

    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.custom_web_service)
    void onCustomWebServiceClicked() {
        getActivity().startActivity(ListWebServiceActivity.getIntent(getActivity()));
    }
}
