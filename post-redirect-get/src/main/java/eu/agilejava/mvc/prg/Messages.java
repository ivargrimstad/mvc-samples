/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.agilejava.mvc.prg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mvc.binding.ParamError;
import javax.mvc.binding.ValidationError;

/**
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Named
@RequestScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 601263646224546642L;

    private List<ParamError> errors = new ArrayList<>();

    public void setErrors(List<ParamError> messages) {
        this.errors = messages;
    }
    
    public List<String> getErrors() {
        return errors.stream()
                .map(ParamError::getMessage)
                .collect(toList());
    }
    
    public String getMessage(String param) {
        return errors.stream()
                .filter(v -> v.getParamName().equals(param))
                .map(ParamError::getMessage)
                .findFirst()
                .orElse("");
    }
}
