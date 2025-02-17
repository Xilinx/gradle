/*
 * Copyright 2021 the original author or authors.
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

package org.gradle.internal.buildtree

import org.gradle.api.internal.BuildType
import org.gradle.internal.invocation.BuildAction
import org.gradle.internal.service.DefaultServiceRegistry
import spock.lang.Specification

class BuildTreeControllerTest extends Specification {
    BuildTreeController state

    def setup() {
        def services = new DefaultServiceRegistry()
        services.add(BuildTreeActionExecutor, Stub(BuildTreeActionExecutor))
        state = new BuildTreeController(services, BuildType.TASKS)
    }

    def "cannot run multiple actions against a tree"() {
        given:
        state.run {
            it.execute(Stub(BuildAction))
        }

        when:
        state.run {
            it.execute(Stub(BuildAction))
        }

        then:
        thrown(IllegalStateException)
    }
}
