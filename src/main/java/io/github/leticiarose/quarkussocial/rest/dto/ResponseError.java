package io.github.leticiarose.quarkussocial.rest.dto;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//vai ser o json retornado quando tivermos um erro
public class ResponseError {

    public static final int UNPROCESSABLE_ENTITY_STATUS = 422;


    private String message;
    private Collection<FieldError> error;

    public ResponseError(String message, Collection<FieldError> error) {
        this.message = message;
        this.error = error;
    }


    public static <T> ResponseError createFromValidation(
            Set<ConstraintViolation<T>> violations){
        List<FieldError> errors = violations
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        String message = "Validation Error";

        var responseError = new ResponseError(message, errors);
        return responseError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<FieldError> getError() {
        return error;
    }

    public void setError(Collection<FieldError> error) {
        this.error = error;
    }

    public Response withStatusCode(int code){
        return Response.status(code).entity(this).build();
    }
}
