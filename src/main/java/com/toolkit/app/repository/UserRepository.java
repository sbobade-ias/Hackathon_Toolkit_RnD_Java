package com.toolkit.app.repository;

import com.toolkit.app.dao.UserDAO;
import com.toolkit.app.dao.UserSkillsDAO;
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
            "and email = ? and password = ? and role = ?";
    final String SELECT_USER_DETAILS = "select * from users,User_profile " +
            "where users.id=user_profile.id " +
            "and users.id = ?";

    final String GET_USER_SKILLS = "SELECT * FROM USER_SKILLS WHERE user_id = ?";

    final String UPDATE_USER_SKILLS =
            "UPDATE USER_SKILLS " +
            "SET PRIMARY_SKILL = ?, PRIMARY_SKILL_RANKING = ?, SECONDARY_SKILL_1 = ?, SECONDARY_SKILL_1_RANKING = ?, " +
            "SECONDARY_SKILL_2 = ?, SECONDARY_SKILL_2_RANKING = ?, SECONDARY_SKILL_3 = ?, SECONDARY_SKILL_3_RANKING = ? " +
            "WHERE ID = ? AND USER_ID = ?";

    public UserDAO findUsers(int userId) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new UserMapper(),
                userId);
    }

    public List<UserDAO> findAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new UserMapper());
    }

    public List<UserDAO> login(String email, String password, String role) {
        List<UserDAO> user = jdbcTemplate.query(SELECT_LOGIN_QUERY,
                new Object[]{email, password, role}, new UserMapper());
        return user;
    }

    public UserSkillsDAO getUserSkills(int userId) {
        return jdbcTemplate.queryForObject(GET_USER_SKILLS, new Object[]{userId}, new UserSkillsMapper());
    }

    private static final class LoginMapper implements RowMapper {
        public UserDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDAO user = new UserDAO();
            user.setUserName((rs.getString("UserName")));
            user.setId((rs.getInt("ID")));
            user.setDesignation(rs.getString("Designation"));
            return user;
        }
    }

    private static final class UserSkillsMapper implements RowMapper<UserSkillsDAO> {
        public UserSkillsDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserSkillsDAO userSkillsDAO = new UserSkillsDAO();
            userSkillsDAO.setId(rs.getInt("ID"));
            userSkillsDAO.setUserId(rs.getInt("USER_ID"));
            userSkillsDAO.setPrimarySkill(rs.getString("PRIMARY_SKILL"));
            userSkillsDAO.setPrimarySkillRanking(rs.getInt("PRIMARY_SKILL_RANKING"));
            userSkillsDAO.setSecondarySkill1(rs.getString("SECONDARY_SKILL_1"));
            userSkillsDAO.setSecondarySkill1Ranking(rs.getInt("SECONDARY_SKILL_1_RANKING"));
            userSkillsDAO.setSecondarySkill2(rs.getString("SECONDARY_SKILL_2"));
            userSkillsDAO.setSecondarySkill2Ranking(rs.getInt("SECONDARY_SKILL_2_RANKING"));
            userSkillsDAO.setSecondarySkill3(rs.getString("SECONDARY_SKILL_3"));
            userSkillsDAO.setSecondarySkill3Ranking(rs.getInt("SECONDARY_SKILL_3_RANKING"));
            userSkillsDAO.setSecondarySkill4(rs.getString("SECONDARY_SKILL_4"));
            userSkillsDAO.setSecondarySkill4Ranking(rs.getInt("SECONDARY_SKILL_4_RANKING"));
            userSkillsDAO.setSecondarySkill5(rs.getString("SECONDARY_SKILL_5"));
            userSkillsDAO.setSecondarySkill5Ranking(rs.getInt("SECONDARY_SKILL_5_RANKING"));
            userSkillsDAO.setSecondarySkill6(rs.getString("SECONDARY_SKILL_6"));
            userSkillsDAO.setSecondarySkill6Ranking(rs.getInt("SECONDARY_SKILL_6_RANKING"));
            userSkillsDAO.setSecondarySkill7(rs.getString("SECONDARY_SKILL_7"));
            userSkillsDAO.setSecondarySkill7Ranking(rs.getInt("SECONDARY_SKILL_7_RANKING"));
            userSkillsDAO.setSecondarySkill8(rs.getString("SECONDARY_SKILL_8"));
            userSkillsDAO.setSecondarySkill8Ranking(rs.getInt("SECONDARY_SKILL_8_RANKING"));
            userSkillsDAO.setSecondarySkill9(rs.getString("SECONDARY_SKILL_9"));
            userSkillsDAO.setSecondarySkill9Ranking(rs.getInt("SECONDARY_SKILL_9_RANKING"));
            userSkillsDAO.setSecondarySkill10(rs.getString("SECONDARY_SKILL_10"));
            userSkillsDAO.setSecondarySkill10Ranking(rs.getInt("SECONDARY_SKILL_10_RANKING"));
            return userSkillsDAO;
        }
    }

    //LIMIT TO 3 SECONDARY SKILLS
    public void setUserSkills(UserSkillsDAO userSkills) {
        int id = userSkills.getId();
        int userId = userSkills.getUserId();
        String primarySkill = userSkills.getPrimarySkill();
        int primarySkillRanking = userSkills.getPrimarySkillRanking();
        String secondarySkill1 = userSkills.getSecondarySkill1();
        int secondarySkill1Ranking = userSkills.getSecondarySkill1Ranking();
        String secondarySkill2 = userSkills.getSecondarySkill2();
        int secondarySkill2Ranking = userSkills.getSecondarySkill2Ranking();
        String secondarySkill3 = userSkills.getSecondarySkill3();
        int secondarySkill3Ranking = userSkills.getSecondarySkill3Ranking();
        jdbcTemplate.update(UPDATE_USER_SKILLS, primarySkill, primarySkillRanking,
                secondarySkill1, secondarySkill1Ranking, secondarySkill2, secondarySkill2Ranking,
                secondarySkill3, secondarySkill3Ranking, id, userId);
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
            user.setRole(rs.getString("role"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}
