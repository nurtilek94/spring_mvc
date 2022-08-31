package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Instructor;
import peaksoft.service.CompanyService;
import peaksoft.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final  InstructorService instructorService;
    private final  CompanyService companyService;
@Autowired
    public InstructorController(InstructorService instructorService, CompanyService companyService) {
        this.instructorService = instructorService;
        this.companyService = companyService;
    }
    @GetMapping("/allInstructors/{id}")
    public String getAllInstructors(@PathVariable("id") Long id, Model model){
    model.addAttribute("allInstructors",instructorService.getAllInstructors(id));
    model.addAttribute("companyId",id);
    return "/courses/courses/ " +id;
}


    @GetMapping("/{id}/addInstructor")
    public String addInstructor(@PathVariable Long id, Model model) {
        model.addAttribute( "instructor", new Instructor());
        model.addAttribute("comId",id);
        return "instructor/saveInstructor";
    }
    @PostMapping("/{id}/saveInstructor")
    public  String saveInstructor(@ModelAttribute("instructor")Instructor instructor,
                                  @PathVariable Long id){
        instructorService.addInstructor(instructor,id);
        return "redirect:/courses/courses/ "+ id;
    }


    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable("id") long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("companyId",instructor.getCompany().getId());
        return "instructor/saveUpdateInstructor";
    }


    @RequestMapping("/{id}/{instructorId}/saveUpdateInstructor")
    public String saveUpdateInstructor(@PathVariable("instructorId") Long instructorId,
                                   @PathVariable ("id") Long id ,
                                   @ModelAttribute ("instructor") Instructor instructor) {
        instructorService.updateInstructor(instructorId,instructor);
        return "redirect:/courses/courses/ "+id;
    }
    @RequestMapping("{id}/{compId}")
    private String deleteInstructor(@PathVariable("id") Long id ,
                                    @PathVariable("compId") Long compId) {
    instructorService.deleteInstructor(id);
    return "redirect:/courses/courses/ "+compId;
    }

}
