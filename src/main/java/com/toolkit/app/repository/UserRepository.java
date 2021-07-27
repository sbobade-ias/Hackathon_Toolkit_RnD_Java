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
    final String SELECT_LOGIN_QUERY = "SELECT * from users,User_profile " +
            "where users.id=user_profile.id "+
            "and username = ? and password = ? and role = ?";
    final String SELECT_USER_DETAILS = "select * from users,User_profile " +
            "where users.id=user_profile.id " +
            "and users.id = ?";


    public UserDAO findUsers(int userId) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new UserMapper(),
                userId);
    }

    public List<UserDAO> findAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new UserMapper());
    }

    public List<UserDAO> login(String username, String password, String  role)
    {
       List<UserDAO> user = jdbcTemplate.query(SELECT_LOGIN_QUERY,
               new Object[] {username,password,role},new UserMapper());
       return user;
    }

    private static final class LoginMapper implements RowMapper{
        public UserDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDAO user = new UserDAO();
            user.setUserName((rs.getString("UserName")));
            user.setId((rs.getInt("ID")));
            user.setDesignation(rs.getString("Designation"));
            return user;
        }
    }

    private static final class UserMapper implements RowMapper<UserDAO> {
        public UserDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDAO user = new UserDAO();
            user.setUserName((rs.getString("UserName")));
            user.setId((rs.getInt("ID")));
            user.setDesignation(rs.getString("Designation"));
            user.setDepartment(rs.getString("department"));
            user.setTeam(rs.getString("team"));
            user.setChapter(rs.getString("chapter"));
            user.setManager(rs.getString("manager"));
            user.setTotal_exp(rs.getString("total_exp"));
            user.setRelavent_exp(rs.getString("relavent_exp"));
            user.setCertifications(rs.getString("certifications"));
            user.setIas_clients(rs.getString("ias_clients"));
            user.setProjects(rs.getString("projects"));
            user.setAdtech_exp(rs.getString("adtech_exp"));
            user.setAdtech_poc(rs.getString("adtech_poc"));
            user.setAspired_tech(rs.getString("aspired_tech"));
            return user;
        }
    }
}
