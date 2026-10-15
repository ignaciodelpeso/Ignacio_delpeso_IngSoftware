package demo2.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class order {

    private String IdPedido;
    private List<article> articulos;

    public order() {
    }

    public order(String IdPedido, List<article> articulos) {
        this.IdPedido = IdPedido;
        this.articulos = articulos;
    }

    public String getId() {
        return IdPedido;
    }

    @JsonProperty("id")
    public void setId(String IdPedido) {
        this.IdPedido = IdPedido;
    }

    public List<article> getArticulos() {
        return articulos;
    }

    @JsonProperty("articles")
    public void setArticulos(List<article> articulos) {
        this.articulos = articulos;
    }

    public double getGrossTotal() {
        double total = 0;

        for (article articulo : articulos) {
            total += articulo.getGrossAmount();
        }

        return total;
    }

    public double getDiscountedTotal() {
        double total = 0;

        for (article articulo : articulos) {
            total += articulo.getDiscountedAmount();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "IdPedido='" + IdPedido + '\'' +
                ", articulos=" + articulos +
                '}';
    }
}