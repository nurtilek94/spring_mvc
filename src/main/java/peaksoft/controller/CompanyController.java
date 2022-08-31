package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;



@Controller
@RequestMapping("/companies")
public class CompanyController  {
    //all companies
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/allCompany")
    private String getAllCompany(Model model){
        model.addAttribute("allCompanies",companyService.getAllCompanies());
        return "company/mainPage";
    }

    @GetMapping("/new")
    public String saveCompany(Model model){
        model.addAttribute("newCompany",new Company());
        return "company/saveCompany";
    }

    @PostMapping("/save")
    public String saveNew(@ModelAttribute("newCompany") Company company){
        companyService.addCompany(company);
        return "redirect:/companies/allCompany";
    }

    @GetMapping("/{id}/getId")
    public String getCompanyById(@PathVariable("id")Long id,Model model){
        model.addAttribute("getCompany",companyService.getCompanyById(id));
     return "company/innerCompanyPage" ;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCompanyById(@PathVariable("id")Long id){
        companyService.deleteCompanyById(companyService.getCompanyById(id));
        return "redirect:/companies/allCompany";
    }
    @GetMapping("/update/{id}")
    private String updateCompany(@PathVariable("id")Long id, Model model) {
        model.addAttribute("company",companyService.getCompanyById(id));
        return "company/updateCompany";
    }


    @PostMapping("{id}/updateCompany")
    private String saveUpdateCompany(@ModelAttribute("company") Company company) {
        companyService.update(company);
        return "redirect:/companies/allCompany";
    }
}
