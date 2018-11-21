package co.com.curiosity.school.reglasnegocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RNFacturador<main> {

    //atributos
    private int codigo; // es de entrada entonces setter
    private double porcDescuento;
    private String error;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPorcDescuento() {
        return porcDescuento;
    }

    public String getError() {
        return error;
    }

    public RNFacturador() {
        codigo = 0;
        porcDescuento = -1;
        error = "";
    }

    private boolean validar() {
        if (codigo <= 0) {
            error = "Producto inexistente";
            return false;
        }
        return true;
    }

    public boolean consultarDescuento() {
        try {
            if (!validar()) {
                return false;
            }

            switch (codigo) {
                case 1:
                    porcDescuento = 0.1;
                    break;
                case 4:
                    porcDescuento = 0.05;
                    break;
                default:
                    porcDescuento = 0;
                    break;
            }
            return true;

        } catch (Exception ex) {
            throw ex;
        }

    }

    public ArrayList LlenarCombo() {

        ArrayList<String> combo = new ArrayList<String>();
        combo.add("Seleccione");
        combo.add("Aguacate");
        combo.add("Zanahoria");
        combo.add("Papa Nevada");
        combo.add("Cebolla Huevo");
        combo.add("Tomate Aliño");
        combo.add("Pera");
        return combo;
    }

   public List<String> tanquearCombo() {
        Path path = Paths.get("D:\\VerdurasdelCampo\\src\\main\\java\\co\\com\\curiosity\\school\\reglasnegocio\\combo.txt");
       List<String> combo =  new ArrayList<>();
        try {
            combo = Files.readAllLines(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
       return combo;
    }

}
