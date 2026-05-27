package dev.clinican.controller;

import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.service.TbUserService;
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

    // Find By ID
    @GetMapping("/{id}")
    TbUserDto getUserById(@PathVariable UUID id){
        return tbUserService.findById(id);
    }

    // Find by Name
    @GetMapping
    public List<TbUserDto> findByName(@RequestParam String name){
        return tbUserService.findByName(name);
    }

    // Create
    @PostMapping
    TbUserDto create(@RequestBody TbUserLoginDto user) {
        return tbUserService.create(user);
    }

    // Update
    @PutMapping("/{id}")
    TbUserDto update(@PathVariable UUID id, @RequestBody TbUserLoginDto user) {
        return tbUserService.update(id, user);
    }

    // Delete
    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) {
        tbUserService.delete(id);
    }
}
