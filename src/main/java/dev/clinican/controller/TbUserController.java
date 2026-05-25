package dev.clinican.controller;

import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.service.TbUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tbUser")

public class TbUserController {

    private final TbUserService tbUserService;
    public TbUserController(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }


    @GetMapping("{id}")
    TbUserDto getUserById(@PathVariable UUID id){
        return tbUserService.findById(id);
    }

    @GetMapping
    public List<TbUserDto> findByName(@RequestParam String name){
        return tbUserService.findByName(name);
    }

    @PostMapping
    TbUserDto create(@RequestBody TbUserLoginDto user) {
        return tbUserService.create(user);
    }

    @PutMapping
    TbUserDto update(@PathVariable UUID id, @RequestBody TbUserLoginDto user) {
        return tbUserService.update(id, user);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) {
        tbUserService.delete(id);
    }



}
