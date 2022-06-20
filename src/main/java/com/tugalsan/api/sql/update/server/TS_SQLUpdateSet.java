package com.tugalsan.api.sql.update.server;

import com.tugalsan.api.executable.client.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.where.server.*;

public class TS_SQLUpdateSet {

    public TS_SQLUpdateSet(TS_SQLUpdateExecutor executor) {
        this.executor = executor;
    }
    private TS_SQLUpdateExecutor executor;

    public int whereGroupAnd(TGS_ExecutableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.execute();
    }

    public int whereGroupOr(TGS_ExecutableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.execute();
    }

    public int whereConditionAnd(TGS_ExecutableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public int whereConditionOr(TGS_ExecutableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public int whereFirstColumnAsId(long id) {
        return whereConditionAnd(conditions -> {
            conditions.lngEq(
                    TS_SQLConnColUtils.names(executor.anchor, executor.tableName).get(0),
                    id
            );
        });
    }

    public int whereConditionNone() {
        return executor.execute();
    }
}
