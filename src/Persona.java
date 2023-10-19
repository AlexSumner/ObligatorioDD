public class Persona {
private int Id;
private String Nombre;
private String Apellido;
private String Pais;

    public int getCi() {
        return Id;
    }

    public void setCi(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    @Override
    public String toString() {
        return "Id=" + Id + ", Nombre='" + Nombre + '\'' + ", Apellido='" + Apellido + '\'' + ", Pais='" + Pais + '\'';
    }

    public Persona(int id, String nombre, String apellido, String pais) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Pais = pais;
    }
}
