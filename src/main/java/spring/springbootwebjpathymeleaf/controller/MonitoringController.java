package spring.springbootwebjpathymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.springbootwebjpathymeleaf.model.Monitoring;
import spring.springbootwebjpathymeleaf.model.Project;
import spring.springbootwebjpathymeleaf.model.*;
import spring.springbootwebjpathymeleaf.service.Analyst.AnalystService;
import spring.springbootwebjpathymeleaf.service.Monitoring.MonitoringService;
import spring.springbootwebjpathymeleaf.service.Project.ProjectService;

import java.util.List;

@Controller
public class MonitoringController {
    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private AnalystService analystService;

    @GetMapping("/monitorings")
    public String viewHomePage(Model model) {
        return findPaginated(1, "FIOAnalyst", "asc", model);
    }

    @GetMapping("/showNewMonitoringForm")
    public String showNewMonitoringForm(Model model) {

        Project project = new Project();
        List<Project> listProject = projectService.getAllProjects();
        model.addAttribute("listProject", listProject );
        model.addAttribute("project", project);
        Analyst analyst = new Analyst();
        List<Analyst> listAnalyst = analystService.getAllAnalysts();
        model.addAttribute("listAnalyst", listAnalyst );
        model.addAttribute("analyst", analyst);
        Monitoring monitoring = new Monitoring();
        model.addAttribute("monitoring", monitoring);

        return "new_monitoring";
    }

    @PostMapping("/saveMonitoring")
    public String saveMonitoring(@ModelAttribute("monitoring") Monitoring monitoring) {
        monitoringService.save(monitoring);
        return "redirect:/monitorings/";
    }

    @GetMapping("/showFormForMonitoringUpdate/{id}")
    public String showFormForMonitoringsUpdate(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("monitoring", monitoringService.getById(id));
        Project project = new Project();
        List<Project> listProject = projectService.getAllProjects();
        model.addAttribute("listProject", listProject );
        model.addAttribute("project", project);
        Analyst analyst = new Analyst();
        List<Analyst> listAnalyst = analystService.getAllAnalysts();
        model.addAttribute("listAnalyst", listAnalyst );
        model.addAttribute("analyst", analyst);
        return "update_monitoring";
    }

    @GetMapping("/deleteMonitoring/{id}")
    public String deleteMonitoring(@PathVariable(value = "id") long id) {
        monitoringService.deleteById(id);
        return "redirect:/monitorings/";
    }

    @RequestMapping("/find/monitoring")
    public String viewNewPage(Model model, @Param("keyword") String keyword) {
        List<Monitoring> listMonitorings = monitoringService.listAll(keyword);
        model.addAttribute("listMonitorings", listMonitorings);
        model.addAttribute("keyword", keyword);
        return "monitorings";
    }

    @GetMapping("monitorings/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;
        Page<Monitoring> page = monitoringService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Monitoring> listMonitorings = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listMonitorings", listMonitorings);
        return "monitorings";
    }
}