/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package training.lang

import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.ProjectJdkTable
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.ex.ProjectRootManagerEx
import com.intellij.openapi.util.ThrowableComputable
import com.intellij.openapi.wm.ToolWindowAnchor
import training.learn.exceptons.NoSdkException
import java.io.File

abstract class AbstractLangSupport : LangSupport {
    override val defaultProjectName: String
        get() = "LearnProject"

    override fun getProjectFilePath(projectName: String): String {
        return ProjectUtil.getBaseDir() + File.separator + projectName
    }

    override fun getToolWindowAnchor(): ToolWindowAnchor {
        return ToolWindowAnchor.LEFT
    }

    override fun getSdkForProject(project: Project): Sdk? {
        try {
            // Use no SDK if it's a valid for this language
            checkSdk(null, project)
            return null
        }
        catch (e: Throwable) {
        }
        
        return ApplicationManager.getApplication().runReadAction(ThrowableComputable<Sdk, NoSdkException> {
            val sdkOrNull = ProjectJdkTable.getInstance().allJdks.find {
                try {
                    checkSdk(it, project)
                    true
                } catch (e: Throwable) {
                    false
                }
            }
            sdkOrNull ?: throw NoSdkException()
        })
    }

    override fun applyProjectSdk(sdk: Sdk, project: Project) {
        CommandProcessor.getInstance().executeCommand(project, {
            ApplicationManager.getApplication().runWriteAction {

                val rootManager = ProjectRootManagerEx.getInstanceEx(project)
                rootManager.projectSdk = sdk
            }
        }, null, null)
    }

    protected fun checkSdkPresence(sdk: Sdk?) {
        if (sdk == null) {
            throw NoSdkException()
        }
    }

    override fun toString(): String = primaryLanguage
}