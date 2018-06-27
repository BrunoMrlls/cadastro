package com.meireles.converter;

import com.meireles.financeiro.model.Pessoa;
import com.meireles.financeiro.util.JpaUtil;
import com.meireles.repository.Pessoas;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Conversor de String para o obj*
 * Conversos de obj to String
 */

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

    @Inject //funciona por causa do ommni faces, pq nao se pode injetar nada no beans. somente com o omni faces
    private Pessoas pessoas;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Pessoa ret = null;

        if (s != null){
            ret = this.pessoas.findById(new Long(s));
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null){
            return ((Pessoa) o).getId().toString();
        }
        return null;
    }
}
