package com.mango.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageSQL {

    private final static Logger logger = LoggerFactory.getLogger(PageSQL.class);

    private Connection connection;
    private String sql;
    private long cursor;
    private long total;
    private String tablename;
    private int page_size;
    private int currentPage;
    private long maxPageCount;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private String trackingColumn;
    private String recordLastRun;

    public PageSQL(Configuration configuration, Connection connection) {
        this.connection=connection;
        this.sql = configuration.getSql();
        this.tablename = getDaTable(sql);
        this.page_size = Integer.parseInt(configuration.getPageSize());
        this.trackingColumn = configuration.getTracking_column();
        this.recordLastRun = configuration.getRecord_last_run();
        this.total=getTotal(this.tablename);
        this.pageInit();
    }

    private void pageInit(){
        this.cursor=0;
        this.currentPage=0;
        this.maxPageCount=this.total/Long.parseLong(String.valueOf(this.page_size))+1l;
    }

    public PageSQL(){}

    private String getDaTable(String sql){
        String tablename=null;
        String pattern = "(from\\s*\\w*)";
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(sql);
        if (matcher.find()) {
            tablename = matcher.group(0).split("\\s")[1];
            System.out.println(tablename);
        }
        return tablename;
    }

    private long getTotal(String tablename){
        String sql = "select count(*) from %s　where %s>%s";
        sql = String.format(sql,tablename,trackingColumn,recordLastRun);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        long total=0l;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            total=resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public boolean next() {
        if (this.currentPage<this.maxPageCount){
            return true;
        }else {
            return false;
        }
    }

    public String nextSQL() {
        return "";
    }

    public void update() {

    }

    public static void main(String[] args) {
        String sql = "select * from ttt";
        PageSQL pg = new PageSQL();
        pg.getDaTable(sql);
    }
}
