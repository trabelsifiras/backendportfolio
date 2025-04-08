package org.firas.portfolio.firasportfolio.controller;

import org.firas.portfolio.firasportfolio.data.VisitorInfo;
import org.firas.portfolio.firasportfolio.service.VisitorInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin
public class VisitorInfoController {

    @Autowired
    private VisitorInforService service;

    @GetMapping
    public List<VisitorInfo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VisitorInfo getById(@PathVariable Long id) {
        return service.getById(id).orElseThrow(() -> new RuntimeException("Visitor not found"));
    }

    @PostMapping
    public VisitorInfo create(@RequestBody VisitorInfo info) {
        return service.create(info);
    }

    @PutMapping("/{id}")
    public VisitorInfo update(@PathVariable Long id, @RequestBody VisitorInfo info) {
        return service.update(id, info);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

