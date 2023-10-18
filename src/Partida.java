import java.util.Date;

public class Partida {
    private int id;
    private Jugador jugador1;
    private Jugador jugador2;
    private Arbitro arbitro;
    private Date fecha;
    private String tipoPartida;
    private Jugador ganador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoPartida() {
        return tipoPartida;
    }

    public void setTipoPartida(String tipoPartida) {
        this.tipoPartida = tipoPartida;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "Partida{" + "id=" + id + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + ", arbitro=" + arbitro + ", fecha=" + fecha + ", tipoPartida='" + tipoPartida + '\'' + ", ganador=" + ganador + '}';
    }

    public Partida(int id, Jugador jugador1, Jugador jugador2, Arbitro arbitro, Date fecha, String tipoPartida, Jugador ganador) {
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.arbitro = arbitro;
        this.fecha = fecha;
        this.tipoPartida = tipoPartida;
        this.ganador = ganador;
    }
}
