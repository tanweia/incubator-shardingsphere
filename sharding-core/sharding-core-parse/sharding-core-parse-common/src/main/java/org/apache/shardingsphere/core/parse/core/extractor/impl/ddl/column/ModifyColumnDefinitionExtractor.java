/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.parse.core.extractor.impl.ddl.column;

import com.google.common.base.Optional;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.shardingsphere.core.parse.core.extractor.api.CollectionSQLSegmentExtractor;
import org.apache.shardingsphere.core.parse.core.extractor.util.ExtractorUtils;
import org.apache.shardingsphere.core.parse.core.extractor.util.RuleName;
import org.apache.shardingsphere.core.parse.sql.segment.ddl.column.ColumnDefinitionSegment;
import org.apache.shardingsphere.core.parse.sql.segment.ddl.column.alter.ModifyColumnDefinitionSegment;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * Modify column definition extractor.
 *
 * @author duhongjun
 */
public class ModifyColumnDefinitionExtractor implements CollectionSQLSegmentExtractor {
    
    private final ColumnDefinitionExtractor columnDefinitionExtractor = new ColumnDefinitionExtractor();
    
    @Override
    public final Collection<ModifyColumnDefinitionSegment> extract(final ParserRuleContext ancestorNode, final Map<ParserRuleContext, Integer> parameterMarkerIndexes) {
        Collection<ModifyColumnDefinitionSegment> result = new LinkedList<>();
        for (ParserRuleContext each : ExtractorUtils.getAllDescendantNodes(ancestorNode, RuleName.MODIFY_COLUMN_SPECIFICATION)) {
            Optional<ColumnDefinitionSegment> columnDefinitionSegment = columnDefinitionExtractor.extract(each, parameterMarkerIndexes);
            if (columnDefinitionSegment.isPresent()) {
                ModifyColumnDefinitionSegment modifyColumnDefinitionSegment = new ModifyColumnDefinitionSegment(
                        each.getStart().getStartIndex(), each.getStop().getStopIndex(), null, columnDefinitionSegment.get());
                postExtractColumnDefinition(each, modifyColumnDefinitionSegment, parameterMarkerIndexes);
                result.add(modifyColumnDefinitionSegment);
            }
        }
        return result;
    }
    
    protected void postExtractColumnDefinition(final ParserRuleContext modifyColumnNode, 
                                               final ModifyColumnDefinitionSegment modifyColumnDefinitionSegment, final Map<ParserRuleContext, Integer> parameterMarkerIndexes) {
    }
}
