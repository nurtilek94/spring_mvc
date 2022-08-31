package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;

import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
import peaksoft.service.StudentService;


@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService,
                            CompanyService companyService,
                            InstructorService instructorService,
                            StudentService studentService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping("/courses/{id}")
    private String getAllCourses(@PathVariable("id") Long id, Model model) {
        model.addAttribute("companyId",id);
        model.addAttribute("courses", courseService.getAllCourse(id));
        model.addAttribute("instructors",instructorService.getAllInstructors(id));
        model.addAttribute("students",studentService.getAllStudents(id));
        return "courses/courseMain";
    }


    @GetMapping("/{id}/addCourse")
    public String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute( "course", new Course());
        model.addAttribute("comId",id);
        return "courses/saveCourse";
    }

    @PostMapping("/{id}/saveCourse")
    public String saveCourse(@ModelAttribute("course")Course course,
                             @PathVariable Long id) {
        System.out.println(course+"`````````````");
        courseService.addCourse(course,id);
        return "redirect:/courses/courses/ "+id;
    }


    @GetMapping("/updateCourse/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId",course.getCompany().getId());
        return "courses/saveUpdateCourse";
    }


    @PostMapping("/{id}/{courseId}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                   @PathVariable ("id") Long id ,
                                   @ModelAttribute ("course") Course course) {
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/courses/ "+id;
    }

    @RequestMapping("/{courseId}/{compId}")
    public String deleteCourse(@PathVariable("courseId") Long id,
                               @PathVariable("compId") Long companyId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/courses/ "+companyId;
    }
//    @PostMapping("/{companyId}/{courseId}/saveAssign")
//    private String saveAssign(@PathVariable("courseId")Long courseId,
//                              @ModelAttribute("inst") Instructor instructor,
//                              @PathVariable("companyId") Long compId) {
//        System.out.println(instructor);
//        instructorService.assignInstructorToCourse(instructor.getId(),courseId);
//        return "redirect:/courses/courses/ "+compId;
//    }
}
