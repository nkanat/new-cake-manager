package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cakes")
public class CakeManagerController {

    @Autowired
    private CakeManagerService cakeManagerService;

    @GetMapping
    public ResponseEntity<List<Cake>> getCakes() {
        return ResponseEntity.ok(cakeManagerService.getCakes());
    }

    @PostMapping
    public ResponseEntity.BodyBuilder addCake(@RequestBody Cake cake, HttpServletRequest request){
        cakeManagerService.addCake(cake);
        return ResponseEntity.created(URI.create(request.getRequestURI()));
    }
}
