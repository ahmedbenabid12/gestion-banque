package com.gestionbanque.api.controller;


import Model.ErrorResponse;
import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.dto.CompteDTO;
import com.gestionbanque.api.exception.BanqueException;
import com.gestionbanque.api.services.CompteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/compte")
@CrossOrigin("*")
public class CompteController {
    private final CompteService compteService ;


    @Operation(summary = " Récupérer tous les comptes d'un client ", description = " Récupérer tous les comptes d'un client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompteEntity.class),
            @ApiResponse(code = 400, message = "Parametres non autorisés"),
            @ApiResponse(code = 401, message = "Utilisateur non autorisé"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)})
    @GetMapping("/{idClient}")
    public List<CompteDTO> getComptes(@PathVariable(name = "idClient") Long idClient) throws BanqueException {
            return compteService.getComptes(idClient) ;
    }

}
