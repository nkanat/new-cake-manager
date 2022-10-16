package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeManagerService;
import com.waracle.cakemgr.util.CakeManagerUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CakeManagerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CakeManagerControllerTest {

    @MockBean
    private CakeManagerService cakeManagerService;

    @MockBean
    private CakeManagerUtil cakeManagerUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(2)
    void shouldReturnListOfAvailableCakes() throws Exception {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("Vanilla Cake","Premium Vanilla Cake 1Kg","http://cake-factory/vanilla-cake.jpeg"));

        when(cakeManagerService.getCakes()).thenReturn(cakes);
        mockMvc.perform(get("/cakes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(cakes.size()))
                .andDo(print());
    }

    @Test
    @Order(1)
    void shouldAddNewCake() throws Exception {
        Cake cake = new Cake( "New Cake", "Description", "https://image-url.com");

        mockMvc.perform(post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(3)
    void addNewCakeShouldThrowValidationException() throws Exception {
        Cake cake = new Cake( "New Cake", "Description", "image-url");

        mockMvc.perform(post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(print());
    }
    @Test
    @Order(4)
    void shouldReturnCakesAsHtml() throws Exception {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("Vanilla Cake","Premium Vanilla Cake 1Kg","http://cake-factory/vanilla-cake.jpeg"));

        when(cakeManagerService.getCakes()).thenReturn(cakes);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(result ->assertNotNull(result))
                .andDo(print());
    }

}
