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

package org.addhen.smssync.presentation.presenter.message;

import com.addhen.android.raiburari.domain.exception.DefaultErrorHandler;
import com.addhen.android.raiburari.domain.exception.ErrorHandler;
import com.addhen.android.raiburari.domain.usecase.DefaultSubscriber;
import com.addhen.android.raiburari.presentation.di.qualifier.ActivityScope;
import com.addhen.android.raiburari.presentation.presenter.BasePresenter;
import com.addhen.android.raiburari.presentation.presenter.Presenter;

import org.addhen.smssync.domain.entity.MessageEntity;
import org.addhen.smssync.domain.usecase.message.ListPublishedMessageUsecase;
import org.addhen.smssync.presentation.exception.ErrorMessageFactory;
import org.addhen.smssync.presentation.model.mapper.MessageModelDataMapper;
import org.addhen.smssync.presentation.view.message.ListMessageView;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ushahidi Team <team@ushahidi.com>
 */
@ActivityScope
public class ListPublishedMessagePresenter extends BasePresenter<ListMessageView> {

    private final ListPublishedMessageUsecase mListPublishedMessageUsecase;

    private final MessageModelDataMapper mMessageModelDataMapper;

    private ListMessageView mListMessageView;


    @Inject
    public ListPublishedMessagePresenter(
            @Named("messagePublishList") ListPublishedMessageUsecase listPublishedMessageUsecase,
            MessageModelDataMapper messageModelDataMapper) {
        mListPublishedMessageUsecase = listPublishedMessageUsecase;
        mMessageModelDataMapper = messageModelDataMapper;
    }

    @UiThread
    public void resume() {
        loadMessages();
    }

    @Override
    public void attachView(@NonNull ListMessageView view) {
        super.attachView(view);
        mListPublishedMessageUsecase.unsubscribe();
    }

    public void setView(@NonNull ListMessageView listMessageView) {
        mListMessageView = listMessageView;
    }

    public void loadMessages() {
        mListMessageView.hideRetry();
        mListMessageView.showLoading();
        mListPublishedMessageUsecase.execute(new PublishedMessageSubscriber());
    }

    private void showErrorMessage(ErrorHandler errorHandler) {
        String errorMessage = ErrorMessageFactory.create(mListMessageView.getAppContext(),
                errorHandler.getException());
        mListMessageView.showError(errorMessage);
    }

    private class PublishedMessageSubscriber extends DefaultSubscriber<List<MessageEntity>> {

        @Override
        public void onStart() {
            mListMessageView.hideRetry();
            mListMessageView.showLoading();
        }

        @Override
        public void onComplete() {
            mListMessageView.hideLoading();
        }

        @Override
        public void onNext(List<MessageEntity> filterList) {
            mListMessageView.hideLoading();
            mListMessageView.showMessages(mMessageModelDataMapper.map(filterList));
        }

        @Override
        public void onError(Throwable e) {
            mListMessageView.hideLoading();
            showErrorMessage(new DefaultErrorHandler((Exception) e));
            mListMessageView.showRetry();
        }
    }
}
