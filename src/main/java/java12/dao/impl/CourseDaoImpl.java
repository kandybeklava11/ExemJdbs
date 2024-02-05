package java12.dao.impl;

import java12.config.Config;
import java12.dao.CourseDao;
import java12.entity.Course;
import jdk.jfr.RecordingState;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private final Connection connection= Config.getConnection();
    @Override
    public void createTable() {
        String sql="create table Course(" +
                "id serial primary key," +
                "name varchar," +
                "description varchar," +
                "duration int) ;";
        Statement statement;
        try {
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("created successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCourse(Course course) {
        PreparedStatement preparedStatement;
    String sql="insert into Course(name,description,duration)" +
            "values(?,?,?) ;";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,course.getName());
            preparedStatement.setString(2,course.getDescription());
            preparedStatement.setInt(3,course.getDuration());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("successfully added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropTable() {
    String sql="drop table Course ;";
    Statement statement;
        try {
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("successfully droped !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCourseDuration(Long id, Course course) {
        PreparedStatement preparedStatement;
        String sql = "UPDATE Course " +
                "SET name = ?, " +
                "description = ?, " +
                "duration = ? " +
                "WHERE id = ? ;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getDuration());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Course getBiId(Long id) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Course course=new Course();
        String sql = " select * from Course where id = ? ;";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                course.setId(resultSet.getLong("id"));
                course.setDescription(resultSet.getString("description"));
                course.setDuration(resultSet.getInt("duration"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    @Override
    public List<Course> groupCourseByName() {
        PreparedStatement preparedStatement;
        List<Course>courses=new ArrayList<>();
        String sql="select name from Course group by name ;";
        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
            Course course=new Course();
            course.setName(resultSet.getString("Name"));
            courses.add(course);
            preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }

    @Override
    public Course getLongestCourse() {
        PreparedStatement preparedStatement;
        Course course = new Course();
        String sql = "SELECT id, name, description, duration FROM Course WHERE duration = (SELECT MAX(duration) FROM Course);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                course.setDuration(resultSet.getInt("duration"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

}

