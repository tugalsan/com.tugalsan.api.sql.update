package com.tugalsan.api.sql.update.server;

import java.sql.*;
import java.util.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.sql.where.server.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;

public class TS_SQLUpdateExecutor {

    public TS_SQLUpdateExecutor(TS_SQLConnAnchor anchor, CharSequence tableName) {
        this.anchor = anchor;
        this.tableName = tableName;
    }
    final public TS_SQLConnAnchor anchor;
    final public CharSequence tableName;

    public List<TS_SQLUpdateParam> set = TGS_ListUtils.of();
    public TS_SQLWhere where = null;

    private String set_toString() {
        var sj = new StringJoiner(",");
        set.stream().forEachOrdered(pair -> {
            TS_SQLSanitizeUtils.sanitize(pair.name());
            sj.add(pair.name() + " = ?");
        });
        return sj.toString();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("UPDATE ").append(tableName).append(" SET ").append(set_toString());
        if (where != null) {
            sb.append(" ").append(where);
        }
        return sb.toString();
    }

    private TGS_UnionExcuse<Integer> set_fill(PreparedStatement fillStmt, int offset) {
        var wrap = new Object() {
            int nextOffset = offset;
        };
        for (var i = 0; i < set.size(); i++) {
            var u = TS_SQLConnStmtUtils.fill(fillStmt, set.get(i).name(), set.get(i).value(), wrap.nextOffset);
            if (u.isExcuse()) {
                return u;
            }
            wrap.nextOffset = u.value();
        }
        return TGS_UnionExcuse.of(wrap.nextOffset);
    }

    public TGS_UnionExcuse<TS_SQLConnStmtUpdateResult> run() {
        var wrap = new Object() {
            TGS_UnionExcuse<Integer> u_set_fill = null;
        };
        var u_update = TS_SQLUpdateStmtUtils.update(anchor, toString(), fillStmt -> {
            wrap.u_set_fill = set_fill(fillStmt, 0);
            if (wrap.u_set_fill.isExcuse()) {
                return;
            }
            where.fill(fillStmt, wrap.u_set_fill.value());
        });
        if (wrap.u_set_fill.isExcuse()) {
            return wrap.u_set_fill.toExcuse();
        }
        return u_update;
    }
}
