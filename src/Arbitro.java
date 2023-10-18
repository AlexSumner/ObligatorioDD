public class Arbitro extends Persona{
    private short Nvl_Certificacion;

    public short getNvl_Certificacion() {
        return Nvl_Certificacion;
    }

    public void setNvl_Certificacion(short nvl_Certificacion) {
        Nvl_Certificacion = nvl_Certificacion;
    }

    public Arbitro(int id, String nombre, String apellido, String pais, short nvl_Certificacion) {
        super(id, nombre, apellido, pais);
        Nvl_Certificacion = nvl_Certificacion;
    }

    @Override
    public String toString() {
        return  "Arbitro{" + super.toString() +
                "Nvl_Certificacion=" + Nvl_Certificacion +
                '}';
    }
}
