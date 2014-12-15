package me.ksiazka.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorWrapper {
    private List<FieldError> validationList = new ArrayList<>();

    public FieldErrorWrapper() {
    }

    public void addError(String field, String message){
        FieldError error = new FieldError(field, message);
        this.validationList.add(error);
    }

    public List<FieldError> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<FieldError> validationList) {
        this.validationList = validationList;
    }
}
