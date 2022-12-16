package com.gestionbanque.api.controller;

import Model.ErrorResponse;
import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.dto.ClientDTO;
import com.gestionbanque.api.exception.BanqueException;
import com.gestionbanque.api.mapper.ClientMapper;
import com.gestionbanque.api.repository.ClientRepository;
import com.gestionbanque.api.services.ClientService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/client")
@CrossOrigin("*")
@Builder
public class ClientController {
    private final ClientService clientService ;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Operation(summary = " Afficher la liste des clients ", description = "Afficher la liste des clients ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompteEntity.class),
            @ApiResponse(code = 400, message = "Parametres non autorisés"),
            @ApiResponse(code = 401, message = "Utilisateur non autorisé"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)})
    @GetMapping
    public List<ClientDTO> getClients()  {
        return clientService.getCLients() ;
    }

    @Operation(summary = "Chercher des clients", description = "Chercher des clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompteEntity.class),
            @ApiResponse(code = 400, message = "Parametres non autorisés"),
            @ApiResponse(code = 401, message = "Utilisateur non autorisé"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)})
    @GetMapping("/searchClients")
    public List<ClientDTO> getSearchClients(@RequestParam(value = "nom" , defaultValue = "") String nom)  {
        return clientService.getSearchClients(nom) ;
    }

    @Operation(summary = "Ajouter un nouveau client", description = "Ajouter un nouveau client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompteEntity.class),
            @ApiResponse(code = 400, message = "Parametres non autorisés"),
            @ApiResponse(code = 401, message = "Utilisateur non autorisé"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)})
    @PostMapping
    public ClientEntity saveClient(@RequestBody ClientDTO clientDTO) throws BanqueException {
        return clientService.saveClient(clientDTO) ;
    }
}
