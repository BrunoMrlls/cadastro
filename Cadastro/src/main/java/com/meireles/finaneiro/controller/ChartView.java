package com.meireles.finaneiro.controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import com.meireles.service.GraficoDespesasReceitas;
import org.primefaces.model.chart.*;

@Named
@javax.faces.view.ViewScoped //do pcte view para funcionar o CDI Injection
public class ChartView implements Serializable {

    private LineChartModel lineModel1;

    @Inject
    private GraficoDespesasReceitas receitas;


    public void init() {
        createLineModels();
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    private void createLineModels() {

        lineModel1 = initLinearModel();
        lineModel1.setTitle("Quantitativo de Despesas x Receitas por data");
        lineModel1.setZoom(true);
        lineModel1.getAxis(AxisType.Y).setLabel("Quantidade");
        DateAxis axis = new DateAxis("Periodo");
        lineModel1.setLegendPosition("e");

        axis.setTickAngle(-50);
        axis.setTickFormat("%#d %b %y");

        lineModel1.getAxes().put(AxisType.X, axis);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries lineReceitas = new LineChartSeries();
        lineReceitas.setLabel("Receitas");

        List<Object[]> listReceitas = receitas.obterListaQuatitativoReceitas();

        for (Object[] obj : listReceitas){

            Long quantidade = (Long)obj[0];
            Date dataPagamento = (Date)obj[1];

            lineReceitas.set(dataPagamento, quantidade);
        }

        model.addSeries(lineReceitas);

        return model;
    }

}