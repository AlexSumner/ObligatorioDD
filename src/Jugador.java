import java.io.Serializable;
public class Jugador extends Persona  implements Serializable {
    private int elo;
    private short edad;

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugador{" +super.toString()+ "elo=" + elo + ", edad=" + edad + '}';
    }

    public Jugador(int id, String nombre, String apellido, String pais, int elo, short edad) {
        super(id, nombre, apellido, pais);
        this.elo = elo;
        this.edad = edad;
    }

}
