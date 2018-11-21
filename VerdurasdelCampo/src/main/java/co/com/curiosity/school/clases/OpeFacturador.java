package co.com.curiosity.school.clases;

import co.com.curiosity.school.reglasnegocio.RNFacturador;

import java.util.ArrayList;
import java.util.List;

public class OpeFacturador {
    private int codigo;
    private double vlrUnitario;
    private double cant;
    private double vlrDescuento;
    private double vlrPagar;
    private double subTotal;
    private String error;

    public OpeFacturador() {
        codigo = 0;
        vlrUnitario = 0;
        cant = 0;
        vlrDescuento = -1;
        vlrPagar = 0;
        subTotal = 0;
        error = "";
        RNFacturador facturador = new RNFacturador();

    }

    public double getVlrDescuento() {
        return vlrDescuento;
    }

    public double getVlrPagar() {
        return vlrPagar;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public String getError() {
        return error;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setVlrUnitario(double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public void setCant(double cant) {
        this.cant = cant;
    }

    private boolean validar() {
        if (codigo <= 0) {
            error = "Producto inexistente";
            return false;
        }
        if (vlrUnitario <= 0) {
            error = "Debe ingresar un valor unitario superior a cero";
            return false;
        }
        if (cant <= 0) {
            error = "LA cantidad debe ser superior a cero";
            return false;
        }
        return true;
    }

    public boolean procesar() {
        try {
            if (!validar()) {
                return false;
            }
            RNFacturador facturador = new RNFacturador();

            facturador.setCodigo(codigo);
            if (!facturador.consultarDescuento()) {
                error = facturador.getError();
                return false;
            }

            subTotal = cant * vlrUnitario;
            vlrDescuento = subTotal * facturador.getPorcDescuento();
            vlrPagar = subTotal - vlrDescuento;

            return true;

        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<String> accerderRN() {
        RNFacturador facturador = new RNFacturador();
        return facturador.tanquearCombo();
    }
}
