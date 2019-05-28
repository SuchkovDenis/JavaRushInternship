package ru.suchkov.parts.controllers;

import ru.suchkov.parts.entities.Part;
import ru.suchkov.parts.servises.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
public class MainController {
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 6;

    private PartService partService;

    @Autowired
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public String shopPage(Model model, @RequestParam("necessary") Optional<Boolean> necessary, @RequestParam("page") Optional<Integer> page, @RequestParam("totalPage") Optional<Integer> totalPage) {
        int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;;
        int totalPageNumber;
        String pagingLinkText;
        List<Part> parts;

        if (necessary.isPresent()) {
            totalPageNumber = totalPage.orElse(partService.getAllPartsByPage(necessary.get(), currentPage, PAGE_SIZE).getTotalPages());
            parts = partService.getAllPartsByPage(necessary.get(), currentPage, PAGE_SIZE).getContent();
            pagingLinkText = "&necessary=" + necessary.get();
        } else {
            totalPageNumber = totalPage.orElse(partService.getAllPartsByPage(currentPage, PAGE_SIZE).getTotalPages());
            parts = partService.getAllPartsByPage(currentPage, PAGE_SIZE).getContent();
            pagingLinkText = "";
        }


        model.addAttribute("num", partService.getAmountOfComputers());
        model.addAttribute("part", new Part());
        model.addAttribute("parts", parts);
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", totalPageNumber);
        model.addAttribute("pagingLinkText",pagingLinkText);
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        partService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        Part part = partService.get(id);
        model.addAttribute("part", part);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(Model model, @ModelAttribute("part") Part part) {
        partService.update(part);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("part") Part part) {
        partService.add(part);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "name") String name) {
        model.addAttribute("parts", partService.getAllByName(name));
        return "search";
    }
}