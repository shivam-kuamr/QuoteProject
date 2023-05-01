package com.doa;

import com.dbutil.DbUtil;
import com.enity.Quote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteDao {
    private final PreparedStatement displayAllQuotesStmt;
    private final PreparedStatement addQuoteStmt;
    private final PreparedStatement userQuoteStmt;
    private final PreparedStatement editQuoteStmt;
    private final PreparedStatement checkQuoteOfUserStmt;
    private final PreparedStatement deleteQuoteStmt;
    Connection con = null;

    public QuoteDao() throws SQLException {
        con = DbUtil.getConnection();
        String displayAllQuotesSql = "select id, author, quote, user_id, created_at from quotes";
        displayAllQuotesStmt = con.prepareStatement(displayAllQuotesSql);
        String addQuoteSql = "insert into quotes(quote, author,user_id,created_at) values(?,?,?,now())";
        addQuoteStmt = con.prepareStatement(addQuoteSql);
        String userQuoteSql = "select * from quotes where user_id = ?";
        userQuoteStmt = con.prepareStatement(userQuoteSql);
        String editQuoteSql = "update quotes set quote=? ,author = ? ,created_at = now() where id = ? and user_id = ?";
        editQuoteStmt = con.prepareStatement(editQuoteSql);
        String checkQuoteOfUserSql = "select id from quotes where user_id = ?";
        checkQuoteOfUserStmt = con.prepareStatement(checkQuoteOfUserSql);
        String deleteQuoteSql = "delete from quotes where id = ? and user_id =?;";
        deleteQuoteStmt = con.prepareStatement(deleteQuoteSql);
    }

    public void close() throws SQLException {
        displayAllQuotesStmt.close();
        userQuoteStmt.close();
        addQuoteStmt.close();
        editQuoteStmt.close();
        checkQuoteOfUserStmt.close();
        deleteQuoteStmt.close();
    }

    public List<Quote> displayAll() {
        List<Quote> list = new ArrayList<>();
        try (ResultSet rs = displayAllQuotesStmt.executeQuery()) {
            while (rs.next()) {
                Quote q = new Quote();
                q.setId(rs.getInt("id"));
                q.setUserId(rs.getInt("user_id"));
                q.setCreated_at(new Date(rs.getTimestamp("created_at").getTime()));
                q.setAuthor(rs.getString("author"));
                q.setQuote(rs.getString("quote"));
                list.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int add(String quote, String author, int id) throws SQLException {
        addQuoteStmt.setString(1, quote);
        addQuoteStmt.setString(2, author);
        addQuoteStmt.setInt(3, id);
        int cnt = addQuoteStmt.executeUpdate();
        return cnt;
    }

    public List<Quote> userQuotes(int id) throws SQLException {
        List<Quote> list = new ArrayList<>();
        userQuoteStmt.setInt(1, id);
        try (ResultSet rs = userQuoteStmt.executeQuery()) {
            while (rs.next()) {
                Quote q = new Quote();
                q.setId(rs.getInt("id"));
                q.setUserId(rs.getInt("user_id"));
                q.setAuthor(rs.getString("author"));
                q.setCreated_at(new Date(rs.getTimestamp("created_at").getTime()));
                q.setQuote(rs.getString("quote"));
                list.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int edit(String quote, String author, int id, int quoteId) throws SQLException {
        int cnt = 0;
        editQuoteStmt.setString(1, quote);
        editQuoteStmt.setString(2, author);
        editQuoteStmt.setInt(3, quoteId);
        editQuoteStmt.setInt(4,id);
        cnt = editQuoteStmt.executeUpdate();
        return cnt;
    }


    public int delete(int id, int quoteId) throws SQLException {
        int cnt = 0;
        deleteQuoteStmt.setInt(1, quoteId);
        deleteQuoteStmt.setInt(2,id);
        cnt = deleteQuoteStmt.executeUpdate();
        return cnt;
    }

}
