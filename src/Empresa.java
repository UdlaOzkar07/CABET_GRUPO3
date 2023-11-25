import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empresa {
    private JPanel Empresa;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtSueldo;
    private JButton btnIngresar;
    private JLabel lblCedula;
    private JLabel lblNombre;
    private JLabel lblSueldo;
    private JScrollPane jcpEmpleados;
    private JTable jtEmpleados;
    private JButton btnMoficar;
    private JButton btnRolPago;
    private JTextArea txaRolPago;
    private RolPago rolPago = new RolPago();
    public Empresa() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateTextField()){
                    String cedula = txtCedula.getText();
                    String nombre = txtNombre.getText();
                    double sueldo = Double.parseDouble(txtSueldo.getText());

                    Empleado empleado = new Empleado(cedula,nombre,sueldo);

                    if (!rolPago.existeEmpleado(empleado)){
                        rolPago.registrarEmpleado(empleado);
                        JOptionPane.showMessageDialog(null,"El empleado con cédula: " + cedula + " registrado correctamente");
                        listarEmpleados();
                        limpiarTexto();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El empleado con cédula: " + cedula + " ya se encuentra registrado");
                    }
                }

            }
        });
        btnMoficar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateTextField()) {
                    String cedula = txtCedula.getText();
                    String nombre = txtNombre.getText();
                    double sueldo = Double.parseDouble(txtSueldo.getText());

                    Empleado empleado = new Empleado(cedula,nombre,sueldo);

                    if (rolPago.existeEmpleado(empleado)){
                        rolPago.modificarEmpleado(empleado);
                        JOptionPane.showMessageDialog(null,"El empleado con cédula: " + cedula + " fue actualizada su informacion correctamente");
                        listarEmpleados();
                        limpiarTexto();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El empleado con cédula: " + cedula + " no se encuentra registrado");
                    }
                }
            }
        });
        btnRolPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txaRolPago.setText("");
                int selectedRow = jtEmpleados.getSelectedRow();
                if (selectedRow != -1){
                    Empleado em = rolPago.obtenerEmpleado(jtEmpleados.getValueAt(selectedRow,0).toString());

                    txaRolPago.append("Cédula: " + em.getCedula() + "\n");
                    txaRolPago.append("Nombre: " + em.getNombre() + "\n");
                    txaRolPago.append("Sueldo Mensual: " + em.getSueldoMensual() + "\n");
                    txaRolPago.append("Aporte al Seguro Social: " + em.getAporteSeguro() + "\n");
                    txaRolPago.append("Impuesto a la Renta: " + em.getImpuestoRenta() + "\n");
                    txaRolPago.append("------------------------------\n");
                    txaRolPago.append("Sueldo a Recibir: " + (em.getSueldoMensual() - em.getAporteSeguro() - em.getImpuestoRenta()) + "\n");
                }
                else {
                    JOptionPane.showMessageDialog(null,"No se ha seleccionado un empleado");
                }
            }
        });
    }

    public boolean validateTextField(){
        boolean ret = true;

        if (txtCedula.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Ingrese el número de cédula del empleado");
            ret = false;
        }

        if(txtNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Ingrese el nombre del  empleado");
            ret = false;
        }
        if (!txtSueldo.getText().isEmpty()) {
            try {
                double value = Double.parseDouble(txtSueldo.getText());
                if (value <= 0)
                {
                    JOptionPane.showMessageDialog(null, "El sueldo debe ser mayor a cero");
                    ret = false;
                }
            }
            catch (NumberFormatException nfEx)
            {
                JOptionPane.showMessageDialog(null, nfEx.getMessage());
                ret = false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese el sueldo del empleado");
            ret = false;
        }

        return ret;
    }

    public void listarEmpleados(){
        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("N° Cédula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Sueldo Mensual");
        modeloTabla.addColumn("Aporte al Seguro");
        modeloTabla.addColumn("Impuesto a la Renta");

        modeloTabla.setRowCount(0);

        for (Empleado e: rolPago.listarEmpleados()){
            modeloTabla.addRow(new Object[]{e.getCedula(),e.getNombre(),e.getSueldoMensual(),e.getAporteSeguro(),e.getImpuestoRenta()});
        }

        jtEmpleados.setModel(modeloTabla);
    }

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("Empresa");
        frame.setContentPane(new Empresa().Empresa);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,700);
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x,y);
        frame.setVisible(true);
    }

    public void limpiarTexto(){
        txtNombre.setText("");
        txtCedula.setText("");
        txtSueldo.setText("");
    }
}
