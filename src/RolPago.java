import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RolPago {
    private List<Empleado> empleados;
    public RolPago() {
        empleados = new ArrayList<Empleado>();
    }

    public void registrarEmpleado(Empleado e)
    {
        empleados.add(e);
    }

    public boolean existeEmpleado(Empleado e){
        for (Empleado em: empleados) {
            if (em.getCedula().equals(e.getCedula()))
                return true;
        }
        return false;
    }

    public List<Empleado> listarEmpleados(){
        return empleados;
    }

    public void modificarEmpleado(Empleado e){

        for (int i = 0; i < empleados.size(); i++) {
            if (!empleados.get(i).equals(e)) {
                empleados.set(i, e);
                break;
            }
        }
    }

    public Empleado obtenerEmpleado(String cedula){

        for (Empleado em: empleados) {
            if (em.getCedula().equals(cedula))
                return em;
        }

        return null;
    }
}
