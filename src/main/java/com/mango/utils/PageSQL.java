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
    private long page_size;
    private long currentPage;
    private long maxPageCount;
    private String nextSql;
    private String cursorValue;

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String nextSQL() {
        return nextSql;
    }

    public void setNextSql(String nextSql) {
        this.nextSql = nextSql;
    }

    private String trackingColumn;

    public PageSQL(Configuration configuration, Connection connection) {
        this.connection=connection;
        this.sql = configuration.getSql();
        this.tablename = getDaTable();
        this.page_size = Integer.parseInt(configuration.getPageSize());
        this.trackingColumn = configuration.getTracking_column();
        this.total=getTotal();
        this.pageInit();
    }

    public void getFileInfo(){

    }

    private void pageInit(){
        this.cursor=1L;
        this.currentPage=1L;
        this.maxPageCount=this.total/this.page_size + 1L;
    }

    public PageSQL(String sql){
        this.sql = sql;
    }

    private String getDaTable(){
        String tablename=null;
        String pattern = "(from\\s*\\w*)";
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(this.sql);
        if (matcher.find()) {
            tablename = matcher.group(0).split("\\s")[1];
            System.out.println(tablename);
        }
        return tablename;
    }

    private long getTotal(){
        String sql = "select count(*) from \"%s\" ";
        sql = String.format(sql,this.tablename);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        long total=0L;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public boolean hasNext() {
        return this.currentPage <= this.maxPageCount;
    }

    public void next(){
        String sql = "select * from \"%s\" order by %s limit %d offset %d";
        if (this.currentPage == this.maxPageCount){
            long offset = this.total%this.page_size;
            sql = String.format(sql,this.tablename, this.trackingColumn, offset, this.cursor);
            this.cursor += offset;
            this.currentPage += 1L;
            String cursorValueSql = "select id from \"%s\" order by id desc limit 1";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try{
                preparedStatement = connection.prepareStatement(cursorValueSql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    this.cursorValue = resultSet.getString(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            sql = String.format(sql,this.tablename, this.trackingColumn, this.page_size, this.cursor);
            this.cursor += this.page_size;
            this.currentPage += 1L;
        }
        setNextSql(sql);
    }

    public void update() {
        String sql = "select count(*) from \"%s\" where %s > %s";
        sql = String.format(sql, this.tablename, this.trackingColumn, this.cursorValue);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = "select * from ttt";
        PageSQL pg = new PageSQL(sql);
        pg.getDaTable();
    }
}
