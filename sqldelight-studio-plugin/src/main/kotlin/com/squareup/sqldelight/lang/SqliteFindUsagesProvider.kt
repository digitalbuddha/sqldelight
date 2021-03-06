/*
 * Copyright (C) 2016 Square, Inc.
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
package com.squareup.sqldelight.lang

import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.squareup.sqldelight.psi.SqliteElement.ColumnNameElement
import com.squareup.sqldelight.psi.SqliteElement.SqlStmtNameElement

class SqliteFindUsagesProvider : FindUsagesProvider {
  override fun getWordsScanner() = null
  override fun canFindUsagesFor(psiElement: PsiElement) = psiElement is ColumnNameElement
      || psiElement is SqlStmtNameElement

  override fun getHelpId(psiElement: PsiElement) = null
  override fun getDescriptiveName(element: PsiElement) =
      when (element) {
        is PsiFile -> element.name
        else -> element.text
      }

  override fun getNodeText(element: PsiElement, useFullName: Boolean) = element.parent.text
  override fun getType(element: PsiElement) =
      when (element) {
        is ColumnNameElement -> "sqlite column"
        is SqlStmtNameElement -> "sqlite statement"
        else -> ""
      }
}
