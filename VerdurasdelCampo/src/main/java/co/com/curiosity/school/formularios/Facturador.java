package co.com.curiosity.school.formularios;

import co.com.curiosity.school.clases.OpeFacturador;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Facturador extends JFrame {
    private JButton btnRegistrar;
    private JComboBox cboProducto;
    private JTextField txtCantidad;
    private JLabel lblVlrUnitario;
    private JLabel lblVlrDescuento;
    private JLabel lblVlrPagar;
    private JButton btnTemrinar;
    private JButton btnLimpiar;
    private JPanel pnlFactura;

    public Facturador() {

        add(pnlFactura);
        setTitle("Verduras del campo");
        setSize(400, 400);
comboLlenar();
        //  LlenarCombo();
        add(pnlFactura);

        cboProducto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                // if(cboProducto.getSelectedIndex()>0){

                // }
                // else {
                //     lblVlrDescuento.setText("0");
                // }
            }
        });
        cboProducto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                consultarPrecio(cboProducto.getSelectedIndex());
            }
        });


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesar();
            }
        });
    }

    //private void LlenarCombo() {
    //    this.cboProducto.addItem("Seleccione");
    //    this.cboProducto.addItem("Aguacate");
    //    this.cboProducto.addItem("Zanahoria");
    //    this.cboProducto.addItem("Papa Nevada");
    //    this.cboProducto.addItem("Cebolla Huevo");
    //    this.cboProducto.addItem("Tomate Aliño");
    //    this.cboProducto.addItem("Pera");
    //}

    private void consultarPrecio(int codigo) {
        if (codigo > 0) {

            switch (codigo) {
                case 1:
                    this.lblVlrUnitario.setText("4.200");
                    break;
                case 2:
                    this.lblVlrUnitario.setText("2.300");
                    break;
                case 3:
                    this.lblVlrUnitario.setText("2.100");
                    break;
                case 4:
                    this.lblVlrUnitario.setText("2.800");
                    break;
                case 5:
                    this.lblVlrUnitario.setText("2.700");
                    break;
                case 6:
                    this.lblVlrUnitario.setText("5.500");
                    break;
                default:
                    this.lblVlrUnitario.setText("$0");


            }
        } else {
            lblVlrDescuento.setText("0");
        }
    }

    private boolean validar() {
        if (this.cboProducto.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(pnlFactura, "Debe seleccionar un producto");
        }
        if ("".equals(this.txtCantidad.getText())) {
            JOptionPane.showMessageDialog(pnlFactura, "Debe ingresar la cantidad");
        }
        return true;

    }

    private void procesar() {
        try {
            if (!validar()) {
                return;
            }

            int codigo = 0;
            double cant = 0;
            double vlrUnitario = 0;

            OpeFacturador facturador = new OpeFacturador();

            codigo = this.cboProducto.getSelectedIndex();
            vlrUnitario = Double.parseDouble(this.lblVlrUnitario.getText().replace("$", "").replace(".", ""));
            cant = Double.parseDouble(this.txtCantidad.getText());

            facturador.setCodigo(codigo);
            facturador.setVlrUnitario(vlrUnitario);
            facturador.setCant(cant);

            if (!facturador.procesar()) {
                JOptionPane.showMessageDialog(pnlFactura, facturador.getError());
                return;
            }

            this.lblVlrDescuento.setText(Double.toString(facturador.getVlrDescuento()));
            this.lblVlrPagar.setText(Double.toString(facturador.getVlrPagar()));

            JOptionPane.showMessageDialog(pnlFactura, "Gracias por su compra");


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pnlFactura, ex.getMessage());
        }

    }

    private void comboLlenar() {
        OpeFacturador opeFacturador = new OpeFacturador();
        for (int i = 0; i < opeFacturador.accerderRN().size(); i++) {
            this.cboProducto.addItem(opeFacturador.accerderRN().get(i));
        }
    }
}
