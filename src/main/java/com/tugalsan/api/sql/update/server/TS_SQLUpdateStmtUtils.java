package com.tugalsan.api.sql.update.server;

import java.sql.*;
import java.util.*;
import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;

public class TS_SQLUpdateStmtUtils {

    final private static TS_Log d = TS_Log.of(TS_SQLUpdateStmtUtils.class);

    public static TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, List<String> colNames, List params) {
        return update(anchor, sqlStmt, fillStmt -> TS_SQLConnStmtUtils.fill(fillStmt, colNames, params, 0));
    }

    public static TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, String[] colNames, Object[] params) {
        return update(anchor, sqlStmt, fillStmt -> TS_SQLConnStmtUtils.fill(fillStmt, colNames, params, 0));
    }

    public static TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, TGS_RunnableType1<PreparedStatement> fillStmt) {
        return TS_SQLConnWalkUtils.update(anchor, sqlStmt, fillStmt);
    }

    //WARNING: IS SQL SAFE!
    @Deprecated
    public static TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> update(TS_SQLConnAnchor anchor, CharSequence sqlStmt) {
        d.ci("update", sqlStmt);
        return update(anchor, sqlStmt, fill -> {
        });
    }
}
