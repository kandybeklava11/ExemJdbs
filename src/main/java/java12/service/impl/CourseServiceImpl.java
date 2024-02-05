package java12.service.impl;

import java12.dao.CourseDao;
import java12.dao.impl.CourseDaoImpl;
import java12.entity.Course;
import java12.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao= new CourseDaoImpl();
    @Override
    public void createTable() {
        courseDao.createTable();
    }

    @Override
    public void addCourse(Course course) {
    courseDao.addCourse(course);
    }

    @Override
    public void dropTable() {
    courseDao.dropTable();
    }

    @Override
    public void updateCourseDuration(Long id, Course course) {
    courseDao.updateCourseDuration(id,course);
    }

    @Override
    public Course getBiId(Long id) {
        return courseDao.getBiId(id);
    }

    @Override
    public List<Course> groupCourseByName() {
        return courseDao.groupCourseByName();
    }

    @Override
    public Course getLongestCourse() {
        return courseDao.getLongestCourse();
    }
}
