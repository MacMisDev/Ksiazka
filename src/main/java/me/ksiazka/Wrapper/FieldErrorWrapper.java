package me.ksiazka.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorWrapper {
    private List<FieldErrorDto> validationList = new ArrayList<>();

    public FieldErrorWrapper() {
    }

    public void addError(String field, String message){
        FieldErrorDto error = new FieldErrorDto(field, message);
        this.validationList.add(error);
    }

    public List<FieldErrorDto> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<FieldErrorDto> validationList) {
        this.validationList = validationList;
    }
}
