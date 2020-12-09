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
            String password="mysql";
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
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
