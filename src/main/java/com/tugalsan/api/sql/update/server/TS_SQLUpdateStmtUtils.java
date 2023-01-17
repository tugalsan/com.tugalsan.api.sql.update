package com.tugalsan.api.sql.update.server;

import java.sql.*;
import java.util.*;
import com.tugalsan.api.executable.client.*;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.pack.client.*;
import com.tugalsan.api.sql.conn.server.*;

public class TS_SQLUpdateStmtUtils {

    final private static TS_Log d = TS_Log.of(TS_SQLUpdateStmtUtils.class);

    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, List<String> colNames, List params) {
        return update(anchor, sqlStmt, fillStmt -> TS_SQLConnStmtUtils.fill(fillStmt, colNames, params, 0));
    }

    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, String[] colNames, Object[] params) {
        return update(anchor, sqlStmt, fillStmt -> TS_SQLConnStmtUtils.fill(fillStmt, colNames, params, 0));
    }

    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, TGS_ExecutableType1<PreparedStatement> fillStmt) {
        return TS_SQLConnWalkUtils.update(anchor, sqlStmt, fillStmt);
    }

    //WARNING: IS SQL SAFE!
    @Deprecated
    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt) {
        d.ci("update", sqlStmt);
        return update(anchor, sqlStmt, fill -> {
        });
    }
}
