package com.presidents.controller;


import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;
import com.presidents.repository.PresidentsRepository;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PutMapping("/main/update-all-data")
    public String updateAllData(@RequestBody @ModelAttribute("presidentDto") PresidentDto presidentDto, Long id) {
        presidentsRepository.findById(id);
        presidentService.updatePresident(presidentDto);
        return "redirect:/main";
    }

    @PatchMapping("/main/update-part-data")
    public String updatePartData(@RequestBody @ModelAttribute("presidentDto") PresidentDto presidentDto, Long id) {
        presidentsRepository.findById(id);
        presidentService.updatePresidentPartial(presidentDto);
        return "redirect:/main";
    }

    @DeleteMapping("/main/delete")
    public String delete(@RequestParam Long id) {
        presidentService.deletePresident(id);
        return "redirect:/main";
    }

//    @GetMapping("/main/name")
//    public String searchByName(@Param("name") String name,
//                               Model model,
//                               @ModelAttribute("presidentDto") PresidentDto presidentDto) {
//        Set<PresidentDto> presidentsByName = presidentService.findPresidentsByName(name);
//        model.addAttribute("presidentsByName",presidentsByName);
//        return "redirect: /index";
//    }


}
