package spring.springbootwebjpathymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.springbootwebjpathymeleaf.model.Analyst;
import spring.springbootwebjpathymeleaf.model.Project;
import spring.springbootwebjpathymeleaf.model.User;
import spring.springbootwebjpathymeleaf.service.Project.ProjectService;
import spring.springbootwebjpathymeleaf.service.User.UserService;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/projects")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/showNewProjectForm")
    public String showNewProjectForm(Model model) {
        User user = new User();
        List<User> listUser = userService.getAllUsers();
        model.addAttribute("listUser", listUser );
        model.addAttribute("user", user);
        Project project = new Project();
        model.addAttribute("project", project);
        return "new_project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.save(project);
        return "redirect:/projects/";
    }

    @GetMapping("/showFormForProjectUpdate/{id}")
    public String showFormForProjectUpdate(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        User user = new User();
        List<User> listUser = userService.getAllUsers();
        model.addAttribute("listUser", listUser );
        model.addAttribute("user", user);
        return "update_project";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteEquipment(@PathVariable(value = "id") long id) {
        projectService.deleteById(id);
        return "redirect:/projects/";
    }

    @RequestMapping("/find/project")
    public String viewNewPage(Model model, @Param("keyword") String keyword) {
        List<Project> listProjects = projectService.listAll(keyword);
        model.addAttribute("listProjects", listProjects);
        model.addAttribute("keyword", keyword);
        return "projects";
    }

    @GetMapping("projects/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;
        Page<Project> page = projectService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Project> listProjects = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listProjects", listProjects);
        return "projects";
    }
}