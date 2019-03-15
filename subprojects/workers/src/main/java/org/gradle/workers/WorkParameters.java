/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.workers;

import org.gradle.api.Incubating;

/**
 * Marker interface for parameter objects to {@link WorkAction}s.
 *
 * @since 5.4
 */
@Incubating
public interface WorkParameters {
    /**
     * Used for {@link WorkAction}s without parameters.
     */
    @Incubating
    final class None implements WorkParameters {
        private None() {}
    }
}
