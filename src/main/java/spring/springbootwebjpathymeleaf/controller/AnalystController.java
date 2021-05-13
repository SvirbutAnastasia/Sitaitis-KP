package spring.springbootwebjpathymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.springbootwebjpathymeleaf.model.Analyst;
import spring.springbootwebjpathymeleaf.service.Analyst.AnalystService;

import java.util.List;

@Controller
public class AnalystController {
    @Autowired
    private AnalystService analystService;

    @GetMapping("/analysts")
    public String viewHomePage(Model model) {
        return findPaginated(1, "FIO", "asc", model);
    }

    @GetMapping("/showNewAnalystForm")
    public String showNewAnalystForm(Model model) {
        Analyst analyst = new Analyst();
        model.addAttribute("analyst", analyst);
        return "new_analyst";
    }

    @PostMapping("/saveAnalyst")
    public String saveAnalyst(@ModelAttribute("analyst") Analyst analyst) {
        analystService.save(analyst);
        return "redirect:/analysts/";
    }

    @GetMapping("/showFormForAnalystUpdate/{id}")
    public String showFormForAnalystUpdate(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("analyst", analystService.getById(id));
        return "update_analyst";
    }

    @GetMapping("/deleteAnalyst/{id}")
    public String deleteAnalyst(@PathVariable(value = "id") long id) {
        analystService.deleteById(id);
        return "redirect:/analysts/";
    }

    @RequestMapping("/find/analyst")
    public String viewNewPage(Model model, @Param("keyword") String keyword) {
        List<Analyst> listAnalysts = analystService.listAll(keyword);
        model.addAttribute("listAnalysts", listAnalysts);
        model.addAttribute("keyword", keyword);
        return "analysts";
    }

    @GetMapping("analysts/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;
        Page<Analyst> page = analystService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Analyst> listAnalysts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listAnalysts", listAnalysts);
        return "analysts";
    }
}