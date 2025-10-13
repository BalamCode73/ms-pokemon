package com.pokemon.model.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class EntFailResponse {

    @Schema(description = "Codigo de la respuesta.", example = "CodigoEstadoHTTP")
    private String codigo;
    @Schema(description = "Descripci�n de la respuesta..", example = "Mensaje")
    private String mensaje;
    @Schema(description = "Detalles de la operacion", example = "[]")
    private List<Object> detalles = new ArrayList<>();

}
