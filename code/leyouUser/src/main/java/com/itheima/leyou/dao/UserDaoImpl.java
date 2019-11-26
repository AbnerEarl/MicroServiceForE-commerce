package com.itheima.leyou.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Map<String, Object>> getUser(String username, String password){

        String sql = "select id as user_id, username, phone, password from tb_user where username = ?";

        return (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql, username);
    }

    public int insertUser(String username, String password){
        final String sql = "insert into tb_user (username, phone, password) values ('"+username+"', '"+username+"', '"+password+"')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean result = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return preparedStatement;
            }
        }, keyHolder)==1;

        if (!result){
            return 0;
        }else {
            return keyHolder.getKey().intValue();
        }
    }
}
