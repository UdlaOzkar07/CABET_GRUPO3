public class Empleado {
    private String cedula;
    private String nombre;
    private double sueldoMensual;
    private double aporteSeguro;
    private double impuestoRenta;

    public Empleado(String cedula, String nombre, double sueldoMensual) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldoMensual = sueldoMensual;
        calcularAporteSeguro();
        calcularImpuestoRenta();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(double sueldo) {
        this.sueldoMensual = sueldo;
    }

    public double getAporteSeguro() {
        return aporteSeguro;
    }

    public void setAporteSeguro(double aporteSeguro) {
        this.aporteSeguro = aporteSeguro;
    }

    public double getImpuestoRenta() {
        return impuestoRenta;
    }

    public void setImpuestoRenta(double impuestoRenta) {
        this.impuestoRenta = impuestoRenta;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sueldoMensual=" + sueldoMensual +
                ", aporteSeguro=" + aporteSeguro +
                ", impuestoSeguro=" + impuestoRenta +
                '}';
    }

    // Calcula el Aporte al Seguro Social
    public void calcularAporteSeguro(){

        aporteSeguro = sueldoMensual * 0.0935;
    }

    //Calcula el Impuesto a la Renta según la tabla proporcionada
    public void calcularImpuestoRenta(){
        double sueldoAnual = sueldoMensual * 12;
        if (sueldoAnual <= 5000) {
            impuestoRenta = 0;
        } else if (sueldoAnual <= 10000) {
            impuestoRenta = 0.10 * (sueldoAnual - 5000);
        } else if (sueldoAnual <= 18000) {
            impuestoRenta = 0.20 * (sueldoAnual - 10000);
        } else {
            impuestoRenta = 0.30 * (sueldoAnual - 18000);
        }
    }
}
