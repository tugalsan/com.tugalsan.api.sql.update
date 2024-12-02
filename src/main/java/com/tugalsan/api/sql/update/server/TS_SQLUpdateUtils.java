package com.tugalsan.api.sql.update.server;

import com.tugalsan.api.tuple.client.*;
import com.tugalsan.api.sql.conn.server.*;

public class TS_SQLUpdateUtils {

    public static TS_SQLUpdate update(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLUpdate(anchor, tableName);
    }

//    public static void test() {
//        TS_SQLUpdateUtils
//                .update(null, "tn")
//                .set(set -> {
//                    set.add(new TGS_Tuple2("ali", "12"));
//                })
//                .whereConditionAnd(conditions -> conditions.lngEq("", 0));
//    }
}
