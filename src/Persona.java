public class Persona {
private int Id;
private String Nombre;
private String Apellido;
private String Pais;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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

    public Persona(int id, String nombre, String apellido, String pais) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Pais = pais;
    }
}
