package me.ksiazka.misc;

import me.ksiazka.Wrapper.FieldErrorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody FieldErrorWrapper validation(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private FieldErrorWrapper processFieldErrors(List<FieldError> fieldErrors){
        FieldErrorWrapper fieldErrorWrapper = new FieldErrorWrapper();

        for(FieldError fieldError: fieldErrors){
            fieldErrorWrapper.addError(fieldError.getField(), localizedErrorMessage(fieldError));
        }

        return fieldErrorWrapper;
    }

    private String localizedErrorMessage(FieldError fieldError){
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(fieldError, locale);
    }
}
