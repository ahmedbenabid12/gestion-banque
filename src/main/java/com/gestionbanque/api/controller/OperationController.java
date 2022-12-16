package com.gestionbanque.api.controller;

import Model.ErrorResponse;
import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.dto.HistriqueOperationDTO;
import com.gestionbanque.api.entity.dto.OperationDTO;
import com.gestionbanque.api.exception.BanqueException;
import com.gestionbanque.api.services.OperationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/operation")
@CrossOrigin("*")
public class OperationController {

    private final OperationService operationService ;

    @Operation(summary = "Ajouter une nouvelle operation retrait ou depot", description = "Ajouter une nouvelle operation retrait ou depot")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompteEntity.class),
            @ApiResponse(code = 400, message = "Parametres non autorisés"),
            @ApiResponse(code = 401, message = "Utilisateur non autorisé"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)})
    @PostMapping
    public OperationDTO saveOperation(@RequestBody OperationDTO operationDTO) throws BanqueException {
        return operationService.saveOperation(operationDTO) ;
    }


    @Operation(summary = "Récupérer tous les opérations liée a un compte sous forme de pagination", description = "Récupérer tous les opérations liée a un compte sous forme de pagination")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompteEntity.class),
            @ApiResponse(code = 400, message = "Parametres non autorisés"),
            @ApiResponse(code = 401, message = "Utilisateur non autorisé"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)})
    @GetMapping("/{idCompte}")
    public HistriqueOperationDTO getOperations(@PathVariable String idCompte ,
                                                     @RequestParam(name="page" , defaultValue = "0") int page ,
                                                     @RequestParam(name="size" , defaultValue = "5") int size ) throws BanqueException {
        return operationService.getOperations(idCompte, page , size) ;
    }

}
