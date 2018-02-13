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

package org.addhen.smssync.domain.usecase.webservice;

import com.addhen.android.raiburari.domain.executor.PostExecutionThread;
import com.addhen.android.raiburari.domain.executor.ThreadExecutor;
import com.addhen.android.raiburari.domain.usecase.Usecase;

import org.addhen.smssync.domain.repository.WebServiceRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Get {@link org.addhen.smssync.domain.entity.WebServiceEntity} use case
 *
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class GetWebServiceUsecase extends Usecase {

    private final WebServiceRepository mWebServiceRepository;

    private final Long mWebServiceId;

    /**
     * Default constructor
     *
     * @param webServiceId         The webService Id
     * @param webServiceRepository The webService repository
     * @param threadExecutor       The thread executor
     * @param postExecutionThread  The post execution thread
     */
    @Inject
    protected GetWebServiceUsecase(Long webServiceId, WebServiceRepository webServiceRepository,
                                   ThreadExecutor threadExecutor,
                                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mWebServiceId = webServiceId;
        mWebServiceRepository = webServiceRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mWebServiceRepository.getEntity(mWebServiceId);
    }
}
