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

package org.apache.shardingsphere.core.parse.sql.statement.dml;

import com.google.common.base.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shardingsphere.core.parse.sql.segment.dml.predicate.WhereSegment;
import org.apache.shardingsphere.core.parse.sql.segment.generic.TableSegment;
import org.apache.shardingsphere.core.parse.sql.statement.generic.TableSegmentsAvailable;
import org.apache.shardingsphere.core.parse.sql.statement.generic.WhereSegmentAvailable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Delete statement.
 *
 * @author zhangliang
 */
@Getter
@Setter
@ToString(callSuper = true)
public final class DeleteStatement extends DMLStatement implements TableSegmentsAvailable, WhereSegmentAvailable {
    
    private final Collection<TableSegment> tables = new LinkedList<>();
    
    private WhereSegment where;
    
    @Override
    public Optional<WhereSegment> getWhere() {
        return Optional.fromNullable(where);
    }
}
