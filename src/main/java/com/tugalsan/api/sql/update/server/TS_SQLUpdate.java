package com.tugalsan.api.sql.update.server;

import java.util.*;
import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.tuple.client.*;
import com.tugalsan.api.sql.conn.server.*;

public class TS_SQLUpdate {

    public TS_SQLUpdate(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLUpdateExecutor(anchor, tableName);
    }
    private final TS_SQLUpdateExecutor executor;

    public TS_SQLUpdateSet set(TGS_RunnableType1<List<TGS_Tuple2<String, Object>>> set) {
        set.run(executor.set);
        return new TS_SQLUpdateSet(executor);
    }
}
