package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeManagerService;
import com.waracle.cakemgr.util.CakeManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CakeManagerController {

    @Autowired
    private CakeManagerService cakeManagerService;

    @Autowired
    private CakeManagerUtil cakeManagerUtil;

    @GetMapping(produces = {MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<String> getCakeNames() {
        return ResponseEntity.ok(cakeManagerUtil.getCakesAsHtmlString(cakeManagerService.getCakes()));
    }

    @GetMapping("/cakes")
    public ResponseEntity<List<Cake>> getCakes() {
        return ResponseEntity.ok(cakeManagerService.getCakes());
    }

    @PostMapping("/cakes")
    public ResponseEntity addCake(@RequestBody @Valid Cake cake){
        cakeManagerService.addCake(cake);
        return ResponseEntity.status(HttpStatus.CREATED).body(cake);
    }
}
