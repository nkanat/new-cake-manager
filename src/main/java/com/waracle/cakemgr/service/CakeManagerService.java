package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.Cake;

import java.util.List;

public interface CakeManagerService {


    List<Cake> getCakes();

    void addCake(Cake cake);
}
