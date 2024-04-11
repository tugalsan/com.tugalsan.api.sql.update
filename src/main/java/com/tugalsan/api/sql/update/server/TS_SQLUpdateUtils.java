package com.tugalsan.api.sql.update.server;

import com.tugalsan.api.sql.conn.server.*;

public class TS_SQLUpdateUtils {

    public static TS_SQLUpdate update(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLUpdate(anchor, tableName);
    }
}
