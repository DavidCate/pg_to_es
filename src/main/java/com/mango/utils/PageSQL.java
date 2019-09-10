package com.mango.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageSQL {

    private final static Logger logger = LoggerFactory.getLogger(PageSQL.class);

    private String sql;
    private long cursor;
    private long total;
    private String tablename;
    private int page_size;
    private String trackingColumn;
    private String recordLastRun;

    public PageSQL(String sql, Configuration configuration) {
        this.sql = sql;
        this.tablename = getDaTable(sql);
        this.page_size = Integer.parseInt(configuration.getPageSize());
        this.trackingColumn = configuration.getTracking_column();
        this.recordLastRun = configuration.getRecord_last_run();
    }

    public PageSQL(){}

    private String getDaTable(String sql){
        String tablename=null;
        String pattern = "(from (\\w*))";
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(sql);
        if (matcher.find()) {
            tablename = matcher.group(0).split("\\s")[1];
        }
        return tablename;
    }

    private long getTotal(String tablename, String trackingColumn, String recordLastRun){
        if (null != tablename || "".equals(tablename)){

            String sql = "select count()";
        }

        return 1l;
    }

    public boolean next() {
        return false;
    }

    public String nextSQL() {
        return "";
    }

    public void update() {}

    public static void main(String[] args) {
        String sql = "select * from ttt";
        PageSQL pg = new PageSQL();
        pg.getDaTable(sql);
    }
}
