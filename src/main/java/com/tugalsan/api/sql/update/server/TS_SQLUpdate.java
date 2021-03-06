package com.tugalsan.api.sql.update.server;

import java.util.*;
import com.tugalsan.api.executable.client.*;
import com.tugalsan.api.pack.client.*;
import com.tugalsan.api.sql.conn.server.*;

public class TS_SQLUpdate {

    public TS_SQLUpdate(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLUpdateExecutor(anchor, tableName);
    }
    private TS_SQLUpdateExecutor executor;

    public TS_SQLUpdateSet set(TGS_ExecutableType1<List<TGS_Pack2<String, Object>>> set) {
        set.execute(executor.set);
        return new TS_SQLUpdateSet(executor);
    }
}
