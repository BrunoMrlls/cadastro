package com.meireles.converter;

import com.meireles.financeiro.model.Lancamento;
import com.meireles.repository.Lancamentos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

    @Inject
    private Lancamentos lancamentos;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Lancamento lancamento = null;

        if (s != null && !s.equals("")){
            lancamento = this.lancamentos.findById(new Long(s));
        }
        return lancamento;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null){
            Lancamento lancamento = ((Lancamento) o);
            return lancamento.getId() == null ? null : lancamento.getId().toString();
        }
        return null;
    }

}
