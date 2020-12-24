package org.example.dao.impl;

import org.example.dao.GenericDAO;
import org.example.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/jspservletjdbc";
            String username ="root";
            String password="mysql123";
            return DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement,parameters);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                results.add(rowMapper.mapRow(rs));
            }
            return results;
        }catch (SQLException e){
            return null;
        }finally {
            try{
                if (connection!=null)
                    connection.close();
                if (preparedStatement!=null)
                    preparedStatement.close();
                if (rs!=null)
                    rs.close();
            }catch (SQLException e){
                return null;
            }
        }
    }

    private void setParameter(PreparedStatement preparedStatement, Object... parameters) {
        try {
            for (int i=0; i<parameters.length; i++){
                Object parameter = parameters[i];
                int index = i+1;
                if (parameter instanceof Long){
                    preparedStatement.setLong(index,(Long) parameter);
                } else if (parameter instanceof String){
                    preparedStatement.setString(index,(String) parameter);
                } else if (parameter instanceof Integer){
                    preparedStatement.setInt(index,(Integer) parameter);
                } else if (parameter instanceof Timestamp){
                    preparedStatement.setTimestamp(index,(Timestamp) parameter);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement,parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        ResultSet resultSet = null;
        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement,parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e){
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                return null;
            }
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
