package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final CompanyService companyService;
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, CompanyService companyService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.companyService = companyService;
    }
//    @GetMapping("/allStudents/{companyId}")
//    private String getAllStudents(@PathVariable("companyId")Long companyId, Model model, @ModelAttribute("course") Course course) {
//        model.addAttribute("allStudents", studentService.getAllStudents(companyId));
//        model.addAttribute("companyId",companyId);
//        model.addAttribute("courses", courseService.getAllCourse(companyId));
//        return "courses/courseMain";
//    }
    @GetMapping("/{id}/addStudent")
    public String addStudent(@PathVariable Long id, Model model) {
        model.addAttribute( "student", new Student());
        model.addAttribute("comId",id);
        return "student/saveStudent";
    }

    @PostMapping("/{id}/saveStudent")
    public  String saveStud(@ModelAttribute("student") Student student,
                            @PathVariable Long id){
        Student student1 = new Student();
        student1.setCompany(companyService.getCompanyById(id));
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        studentService.addStudent(id,student1);
        return "redirect:/courses/courses/"+id;
    }


    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") long id, Model model) {
        Student student= studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("companyId",student.getCompany().getId());
        return "student/updateStudent";
    }
    @RequestMapping("/{companyId}/{studentId}/saveUpdateStudent")
    public String saveUpdateStudent(@PathVariable("companyId") Long id,
                                       @PathVariable ("studentId") Long studentId ,
                                       @ModelAttribute ("student") Student student) {
        studentService.updateStudent(student,studentId);
        return "redirect:/courses/courses/ "+id;
    }



    @RequestMapping("/{studentId}/{compId}")
    private String deleteInstructor(@PathVariable("studentId") Long id ,
                                    @PathVariable("compId") Long compId) {
        studentService.deleteStudent(id);
        return "redirect:/courses/courses/ "+compId;
    }
    }




