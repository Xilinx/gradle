/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.launcher.exec;

import org.gradle.api.internal.BuildType;
import org.gradle.internal.buildtree.BuildTreeController;
import org.gradle.internal.invocation.BuildAction;
import org.gradle.internal.buildtree.BuildActionRunner;
import org.gradle.internal.session.BuildSessionContext;
import org.gradle.internal.session.BuildSessionActionExecutor;

/**
 * A {@link BuildActionExecuter} responsible for establishing the build tree for a single invocation of a {@link BuildAction}.
 */
public class BuildTreeScopeLifecycleBuildActionExecuter implements BuildSessionActionExecutor {
    @Override
    public BuildActionRunner.Result execute(BuildAction action, BuildSessionContext buildSession) {
        BuildType buildType = action.isRunTasks() ? BuildType.TASKS : BuildType.MODEL;
        if (action.isCreateModel()) {
            // When creating a model, do not use configure on demand or configuration cache
            action.getStartParameter().setConfigureOnDemand(false);
            action.getStartParameter().setConfigurationCache(false);
        }

        try (BuildTreeController buildTree = new BuildTreeController(buildSession.getServices(), buildType)) {
            return buildTree.run(context -> context.execute(action));
        }
    }
}
