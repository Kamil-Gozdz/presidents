package com.presidents.controller;


import com.presidents.model.dto.PresidentDto;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("presidents")
@RequiredArgsConstructor
public class PresidentsController {

    private final PresidentService presidentService;

    @GetMapping("all")
    public List<PresidentDto> getAll() {
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
    // Tak można ale się nie robi bo są globalne annotacje
//    @ExceptionHandler({RuntimeException.class, IllegalAccessError.class})
//    public final ResponseEntity<Object> handleException(Exception ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    };

    @PatchMapping("update")
    public PresidentDto updatePartial(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresidentPartial(presidentDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteByIndex(@PathVariable long id) {
        presidentService.deletePresident(id);

    }

    @GetMapping("find-by-name/{name}")
    public Set<PresidentDto> findPresidentByName(@PathVariable String name){
        return presidentService.findPresidentsByName(name);
    }

    @GetMapping("find-by-party/{party}")
    public Set<PresidentDto> findPresidentByPoliticalParty(@PathVariable String party){
        return presidentService.findPresidentsByPoliticalParty(party);
    }

}
