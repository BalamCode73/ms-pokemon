package com.pokemon.handler;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.pokemon.model.api.EntFailResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class TransactionHandler {


    /**
     * <p>
     * Captura y maneja la excepcion tipo MethodArgumentNotValidException(argumentos
     * invalidos) y reponse con la entidad generica
     * </p>
     *
     * @return ResponseEntity<EntFailResponse>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EntFailResponse> handlerValidationException(MethodArgumentNotValidException e,
                                                                      WebRequest request) {


        EntFailResponse response = getFailJSONResponse(e, "400","Solciitud Incorrecta", "https:1.1.1.1/help");

        return getResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

    /**
     * <p>
     * Captura y maneja la excepcion tipo MethodArgumentNotValidException(argumentos
     * invalidos) y reponse con la entidad generica
     * </p>
     *
     * @return ResponseEntity<EntFailResponse>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<EntFailResponse> handlerValidationException(Exception e,
                                                                      HttpServletRequest  request) {

        // Excluir Swagger UI
        if (request.getRequestURI().startsWith("/swagger-ui") || request.getRequestURI().startsWith("/v3/api-docs")) {
            return null; // Dejar que Spring maneje la solicitud
        }

        EntFailResponse response = getFailResponseGenric(e, "400","Solciitud Incorrecta", "https:1.1.1.1/help");

        return getResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

    /**
     * <p>
     * Arma Response Generico para respuestas no exitosas
     * </p>
     *
     * @param codigo
     * @param mensaje
     * @return EntFailResponse
     */
    public EntFailResponse getFailResponseGenric(Exception e, String codigo, String mensaje, String info) {
        EntFailResponse response = new EntFailResponse();

        response.getDetalles().addAll(Arrays.asList(e.getMessage()));

        response.setMensaje(mensaje);
        response.setCodigo(codigo);
        return response;
    }

    /**
     * <p>
     * Arma Response generico para parametros invalidos en el reques del servicio
     * </p>
     *
     * @param codigo
     * @param mensaje
     * @return EntFailResponse
     */
    public EntFailResponse getFailJSONResponse(MethodArgumentNotValidException e
            , String codigo, String mensaje,
                                               String info) {



        EntFailResponse response = new EntFailResponse();
        String folio =  LocalDateTime.now().getNano()+"";


        BindingResult result = e.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        fieldErrors.stream().forEach(err -> {
            if(!StringUtils.isAllBlank(err.getDefaultMessage())){
                response.getDetalles().add(err.getDefaultMessage());
            }});

        response.setMensaje(mensaje);

        response.setCodigo(codigo);
        return response;
    }

    /**
     * <p>
     * Retorna Response Generico
     * </p>
     *
     * @param <T>
     * @param object
     * @param estatus
     * @return ResponseEntity<T>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> ResponseEntity<T> getResponseEntity(Object object, HttpStatus estatus) {
        return new ResponseEntity(object, estatus);
    }
}