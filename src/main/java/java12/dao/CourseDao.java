package java12.dao;

import java12.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    void createTable();
    void addCourse(Course course);
    void dropTable();
    void updateCourseDuration(Long id,Course course);
    Course getBiId(Long id);
    List<Course> groupCourseByName();
    Course getLongestCourse();
}
