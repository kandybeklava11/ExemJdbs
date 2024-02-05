package java12.service;

import java12.entity.Course;

import java.util.List;

public interface CourseService {
    void createTable();
    void addCourse(Course course);
    void dropTable();
    void updateCourseDuration(Long id,Course course);
    Course getBiId(Long id);
    List<Course> groupCourseByName();
    Course getLongestCourse();
}
