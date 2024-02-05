package java12;

import java12.entity.Course;
import java12.service.CourseService;
import java12.service.impl.CourseServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CourseService courseService=new CourseServiceImpl();
       // courseService.createTable();
        //courseService.addCourse(new Course("Kompot","Sweetest",6));
        //courseService.updateCourseDuration(1L,new Course("yryryryryyr","jjjjjjj",35));
        //System.out.println(courseService.getBiId(1L));
        //System.out.println(courseService.groupCourseByName());
        //System.out.println(courseService.getLongestCourse());
    }
}
