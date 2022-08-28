package com.presidents.controller;


import com.presidents.model.dto.PresidentDto;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("presidents")
@RequiredArgsConstructor
public class PresidentsController {

    private final PresidentService presidentService;

    @GetMapping("all")
    public List<PresidentDto> getAll(){
        return presidentService.getAllPresidents();
    }

    @PostMapping("save")
    public PresidentDto save(@RequestBody PresidentDto presidentDto) {
        return presidentService.savePresident(presidentDto);
    }

    @PutMapping("update")
    public PresidentDto updateWithBodyOnly(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresident(presidentDto);
    }
//
//
//    @PatchMapping("update")
//    public String updatePartial(@RequestBody President president) {
//        President p = PresidentsDB.presidentRepository.get(Math.toIntExact(president.getId()));
//        if (president.getName() != null) {
//            p.setName(president.getName());
//        }
//        if (president.getSurname() != null) {
//            p.setSurname(president.getSurname());
//        }
//        if (president.getTermFrom() != null) {
//            p.setTermFrom(president.getTermFrom());
//        }
//        if (president.getTermTo() != null) {
//            p.setTermTo(president.getTermTo());
//        }
//        if (president.getPoliticalParty() != null) {
//            p.setPoliticalParty(president.getPoliticalParty());
//        }
//        return "Updated";
//    }
//
//    @DeleteMapping("delete/{id}")
//    public String deleteByIndex(@PathVariable int id){
//        PresidentsDB.presidentRepository.remove(id);
//        return "deleted";
//    }
}
