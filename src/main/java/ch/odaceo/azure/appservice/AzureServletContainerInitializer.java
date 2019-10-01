
/*
 * Copyright (C) 2019 Odaceo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.odaceo.azure.appservice;

import java.util.Objects;
import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * This class is reponsible to make sure Azure Servlet Filters are matched
 * before any declared filters.
 * 
 * @author Alexandre GARINO
 * @version 1.0
 */
public class AzureServletContainerInitializer implements ServletContainerInitializer {

    private static final String FILTER_APP_SERVICE_NAME = "AppServiceFilter";

    private static final String FILTER_EASY_AUTH_NAME = "EasyAuthFilter";

    private static final String FILTER_MAPPING_URL_PATTERN = "/*";

    @Override
    public void onStartup(final Set<Class<?>> handlerTypeSet, final ServletContext servletContext)
            throws ServletException {
        applyBeforeAnyMappings(servletContext, FILTER_APP_SERVICE_NAME);
        applyBeforeAnyMappings(servletContext, FILTER_EASY_AUTH_NAME);
    }

    private void applyBeforeAnyMappings(final ServletContext servletContext, String filterName) {
        final FilterRegistration registration = servletContext.getFilterRegistration(filterName);
        if (Objects.nonNull(registration)) {
            registration.addMappingForUrlPatterns(null, false, FILTER_MAPPING_URL_PATTERN);
        }
    }

}