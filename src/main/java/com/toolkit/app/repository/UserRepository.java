package com.toolkit.app.repository;

import com.toolkit.app.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String SELECT_BY_ID_QUERY = "SELECT * from Users where id = ?";
    final String SELECT_ALL_QUERY = "SELECT * from Users";


    public UserDAO findUsers(int userId) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new UserMapper(),
                userId);
    }

    public List<UserDAO> findAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new UserMapper());
    }

    private static final class UserMapper implements RowMapper<UserDAO> {
        public UserDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDAO user = new UserDAO();
            user.setFirstName(rs.getString("FIRSTNAME"));
            user.setLastName(rs.getString("LASTNAME"));
            return user;
        }
    }
}
