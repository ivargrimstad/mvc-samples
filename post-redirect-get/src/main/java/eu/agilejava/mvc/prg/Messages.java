/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.agilejava.mvc.prg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mvc.annotation.RedirectScoped;

/**
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Named
@RequestScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 601263646224546642L;

    private List<String> errors = new ArrayList();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> messages) {
        this.errors = messages;
    }
}
