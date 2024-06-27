module com.tugalsan.api.sql.update {
    requires java.sql;
    requires com.tugalsan.api.function;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.sql.sanitize;
    requires com.tugalsan.api.sql.conn;
    requires com.tugalsan.api.sql.where;
    exports com.tugalsan.api.sql.update.server;
}
