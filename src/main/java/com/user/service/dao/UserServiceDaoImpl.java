package com.user.service.dao;

import com.user.service.domain.Image;
import com.user.service.domain.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.user.service.domain.User.UserFields.*;

public class UserServiceDaoImpl implements UserDao {

    @Value("${user.getAll}")
    private String getAllUsers;

    @Value("${user.add}")
    private String addUser;

    @Value("${user.getById}")
    private String getUserById;

    @Value("${user.getByLogin}")
    private String getUserByLogin;

    @Value("${user.update}")
    private String updateUser;

    @Value("${user.delete}")
    private String deleteUser;

    @Value("${user.setToken}")
    private String setToken;

    @Value("${user.getByToken}")
    private String getUserByToken;

    @Value("${img.getAll}")
    private String getAllImages;

    @Value("${img.add}")
    private String addImg;

    @Value("${img.delete}")
    private String deleteImg;

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    private RowMapper<User> userMapper = new BeanPropertyRowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUserId(resultSet.getInt("userId"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setBirthDate(resultSet.getDate("birthDate"));
            user.setToken(resultSet.getString("token"));
            return user;
        }
    };

    private RowMapper<Image> imgMapper = new BeanPropertyRowMapper<Image>() {
        @Override
        public Image mapRow(ResultSet resultSet, int i) throws SQLException {
           Image img = new Image();
            img.setUserId(resultSet.getInt("userId"));
            img.setImgId(resultSet.getInt("imgId"));
            img.setUrl(resultSet.getString("url"));
           return img;
        }
    };

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserServiceDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {
        try {
            LOGGER.info("dao");
            return namedParameterJdbcTemplate.query(getAllUsers, userMapper);
        }
        catch(Exception ex) {
            LOGGER.info(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Integer addUser(User user) {
        KeyHolder key = new GeneratedKeyHolder();
        try {
            namedParameterJdbcTemplate.update(addUser, getParametersMapForUser(user), key);
            return key.getKey().intValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public User getUserById(Integer id) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(USER_ID.getValue(),id);
        return namedParameterJdbcTemplate.queryForObject(getUserById,map, userMapper);
    }

    public User getUserByLogin(String login) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(LOGIN.getValue(),login);
        try {
            return namedParameterJdbcTemplate.queryForObject(getUserByLogin, map, userMapper);
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Integer updateUser(User user) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(USER_ID.getValue(),user.getUserId());
        map.put(LOGIN.getValue(),user.getLogin());
        map.put(PASSWORD.getValue(),user.getPassword());
        map.put(FIRST_NAME.getValue(),user.getFirstName());
        map.put(LAST_NAME.getValue(),user.getLastName());
        map.put(BIRTH_DATE.getValue(),user.getBirthDate());
        try {
            namedParameterJdbcTemplate.update(updateUser, map);
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Integer deleteUser(Integer id) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(USER_ID.getValue(),id);
        try {
            namedParameterJdbcTemplate.update(deleteUser,map);
            return 1;
        } catch (Exception ex) {
            return -1;
        }
    }

    public void setToken(Integer id, BigInteger token) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(USER_ID.getValue(),id);
        map.put(TOKEN.getValue(),token.toString());
        namedParameterJdbcTemplate.update(setToken, map);
    }

    public User getUserByToken(String token) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(TOKEN.getValue(),token);
        try {
            return namedParameterJdbcTemplate.queryForObject(getUserByToken, map, userMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Image> getUserImages(Integer userId) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put(USER_ID.getValue(),userId);
        try {
            return namedParameterJdbcTemplate.query(getAllImages,map,imgMapper);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Integer addImg(String url, Integer userId) {
        KeyHolder key = new GeneratedKeyHolder();
        try {
            Image image = new Image();
            image.setUserId(userId);
            image.setUrl(url);
            namedParameterJdbcTemplate.update(addImg,getParametersMapForImg(image),key);
            return key.getKey().intValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Integer deleteImg(Integer imgId) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("imgId",imgId);
        try {
            namedParameterJdbcTemplate.update(deleteImg, map);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getToken() {
        return null;
    }

    private MapSqlParameterSource getParametersMapForUser(User user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(LOGIN.getValue(),user.getLogin());
        parameterSource.addValue(PASSWORD.getValue(), user.getPassword());
        parameterSource.addValue(FIRST_NAME.getValue(),user.getFirstName());
        parameterSource.addValue(LAST_NAME.getValue(),user.getLastName());
        parameterSource.addValue(BIRTH_DATE.getValue(), user.getBirthDate());
        return parameterSource;
    }

    private MapSqlParameterSource getParametersMapForImg(Image image) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId",image.getUserId());
        parameterSource.addValue("url", image.getUrl());
        return parameterSource;
    }
}
