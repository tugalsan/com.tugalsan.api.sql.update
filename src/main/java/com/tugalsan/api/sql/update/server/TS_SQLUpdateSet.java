package com.tugalsan.api.sql.update.server;

import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.where.server.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;

public class TS_SQLUpdateSet {

    public TS_SQLUpdateSet(TS_SQLUpdateExecutor executor) {
        this.executor = executor;
    }
    private final TS_SQLUpdateExecutor executor;

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> whereGroupAnd(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> whereGroupOr(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> whereConditionAnd(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> whereConditionOr(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> whereFirstColumnAsId(long id) {
        var u_names = TS_SQLConnColUtils.names(executor.anchor, executor.tableName);
        if (u_names.isExcuse()) {
            return u_names.toExcuse();
        }
        return whereConditionAnd(conditions -> {
            conditions.lngEq(u_names.value().get(0), id);
        });
    }

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> whereConditionNone() {
        return executor.run();
    }
}
