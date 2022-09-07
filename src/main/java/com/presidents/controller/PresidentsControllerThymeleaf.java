package com.presidents.controller;


import com.presidents.model.dto.PresidentDto;
import com.presidents.repository.PresidentsRepository;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PresidentsControllerThymeleaf {

    private final PresidentsRepository presidentsRepository;
    private final PresidentService presidentService;

    @RequestMapping("/main")
    @GetMapping("/main")
    public String getIndex(Model model, @RequestParam(name = "form", defaultValue = "0") int form) {
        model.addAttribute("presidents", presidentsRepository.findAll());
        model.addAttribute("presidentDto", new PresidentDto());
        model.addAttribute("form", form);
        return "index";
    }

    @PostMapping("/main/save")
    public String save(@ModelAttribute("presidentDto") PresidentDto presidentDto, Model model) {
        presidentService.savePresident(presidentDto);
        return "redirect:/main";
    }

    @DeleteMapping  ("/main/delete")
    public String delete(@RequestParam Long id) {
        presidentService.deletePresident(id);
        return "redirect:/main";
    }


}
