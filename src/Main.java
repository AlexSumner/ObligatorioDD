import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Date;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Persona> personas = new ArrayList<>();
    static ArrayList<Partida> partidas = new ArrayList<>();

    public static void main(String[] args) {

        int opcion = 0;

        while (opcion != -1){
            System.out.println("Gestion Campeonato Ajedrez ");
            System.out.println("Ingrese -1 para salir del programa.");
            System.out.println("** Seleccione la opción que desee: ");
            System.out.println("1. Alta de Jugador/Árbitro.");
            System.out.println("2. Lista de Jugadores.");
            System.out.println("3. Lista de Árbitros.");
            System.out.println("4. Modificar Jugadores/Arbitros");
            System.out.println("5. Baja Jugadores/Arbitros");
            System.out.println("6. Alta partidas");
            System.out.println("7. Modificar partidas");
            System.out.println("8. Baja partidas");
            System.out.println("9. Mostrar listado de partidas");
            System.out.println("10. Mostrar partidas de un jugador");
            System.out.println("11. Mostrar partidas por fecha");
            System.out.println("12. Mostrar historial del partida y resultado");
            opcion = Integer.parseInt(entrada.nextLine());
            
            switch(opcion){
                case 1 :{
                    altaPersona();
                    break;
                }
                case 2: {
                    listarJugadores();
                    break;
                }
                case 3:{
                    listarArbitros();
                    break;
                }
                case 4 :{
                    modificarPersona();
                    break;
                }
                case 5 :{
                    BajaPersona();
                    break;
                }
                case 6 :{
                    AltaPartida();
                    break;
                }
                case 7 :{
                   ModificarPartida();
                    break;
                }
                case 8 :{
                    BajaPartida();
                    break;
                }
                case 9 :{
                    listaPartidas();
                    break;
                }
                case 10: {
                    MostrarPartidasJugador();
                    break;
                }
                case 11: {
                    MostrarPartidasFecha();
                    break;
                }
                case 12: {
                    historialDePartidas();
                    break;
                }

                default:{
                    if(opcion != -1){
                        System.out.println("Ingrese una opción válida.");
                    }
                }
            }
        }

    }
    //#region "ABM Persona"
    public static boolean altaPersona(){
        int opcion = 0;

        while(opcion != -1){
            System.out.println("Seleccione la persona que desea ingresar: ");
            System.out.println("Ingrese -1 para salir.");
            System.out.println("1. Jugador");
            System.out.println("2. Árbitro");

            opcion = Integer.parseInt(entrada.nextLine());

            if(opcion == 1){
                System.out.println("Ingrese Cedula de Identidad:");
                int id = Integer.parseInt(entrada.nextLine());

                System.out.println("Ingrese el nombre: ");
                String nombre = entrada.nextLine();

                System.out.println("Ingrese el apellido:");
                String apellido = entrada.nextLine();

                System.out.println("Ingrese el pais: ");
                String pais = entrada.nextLine();

                System.out.println("Ingrese el valor ELO del jugador: ");
                int elo = Integer.parseInt(entrada.nextLine());

                System.out.println("Ingrese la edad del jugador: ");
                short edad = Short.parseShort(entrada.nextLine());

                Persona unJugador = new Jugador(id, nombre, apellido, pais, elo, edad);

                personas.add(unJugador);

                System.out.println(unJugador);

            } else if (opcion == 2) {

                System.out.println("Ingrese Cedula de Identidad:");
                int id = Integer.parseInt(entrada.nextLine());

                System.out.println("Ingrese el nombre: ");
                String nombre = entrada.nextLine();

                System.out.println("Ingrese el apellido:");
                String apellido = entrada.nextLine();

                System.out.println("Ingrese el pais: ");
                String pais = entrada.nextLine();

                System.out.println("Ingrese el nivel de certificación (del 1 al 3): ");
                short nvl_Certificacion = Short.parseShort(entrada.nextLine());

                if (nvl_Certificacion >= 1 && nvl_Certificacion <= 3) {
                    Persona unArbitro = new Arbitro(id, nombre, apellido, pais, nvl_Certificacion);
                    personas.add(unArbitro);
                    System.out.println("Árbitro registrado con éxito: " + unArbitro);
                } else {
                    System.out.println("El nivel de certificación debe estar entre 1 y 3.");
                }

                return true;
            } else if (opcion != -1) {

                System.out.println("Ingrese una opción válida");
            }
        }
        return false;
    }
    public static boolean modificarPersona(){
            System.out.println("Seleccione un ID de persona que desea modificar");
            int opcion = Integer.parseInt(entrada.nextLine());

            for (Persona unaPersona : personas){
                if (unaPersona.getCi() == opcion){
                    System.out.println("Ingrese un nuevo nombre: ");
                    String nombre = entrada.nextLine();

                    System.out.println("Ingrese un nuevo apellido: ");
                    String apellido = entrada.nextLine();

                    System.out.println("Ingrese un nuevo pais: ");
                    String pais = entrada.nextLine();

                    if (unaPersona instanceof Jugador ){
                        System.out.println("Ingrese un nuevo valor de elo: ");
                        int elo = Integer.parseInt(entrada.nextLine());

                        System.out.println("Ingrese una nueva edad: ");
                        short edad = Short.parseShort(entrada.nextLine());
                        ((Jugador) unaPersona).setElo(elo);
                        ((Jugador) unaPersona).setEdad(edad);
                    }else if (unaPersona instanceof Arbitro){
                        System.out.println("Ingrese un nuevo Nivel de Certificacion: ");
                        short Nvl_Certificacion = Short.parseShort(entrada.nextLine());
                        ((Arbitro) unaPersona).setNvl_Certificacion(Nvl_Certificacion);
                    }
                    unaPersona.setNombre(nombre);
                    unaPersona.setApellido(apellido);
                    unaPersona.setPais(pais);
                }
                System.out.println("Persona modificada: "+unaPersona);
                return true;
            }
        System.out.println("No se encontro persona con ese numero de ID!!");
            return false;
    }
    public static void BajaPersona() {
        System.out.println("¿Qué desea eliminar, Jugadores (1) o Árbitro (2)?");
        short tipo = Short.parseShort(entrada.nextLine());
        boolean comprobante = false;

        if (tipo == 1) {
            System.out.println("Ingrese el CI del jugador a eliminar. Nos aseguraremos de que no vuelva...");
            int ciJugador = Integer.parseInt(entrada.nextLine());

            // Verificar si el jugador está en una partida
            boolean jugadorEnPartida = false;
            for (Partida partida : partidas) {
                if (partida.getJugador1() != null && partida.getJugador1().getCi() == ciJugador) {
                    jugadorEnPartida = true;
                    break;
                }
                if (partida.getJugador2() != null && partida.getJugador2().getCi() == ciJugador) {
                    jugadorEnPartida = true;
                    break;
                }
            }

            if (jugadorEnPartida) {
                System.out.println("No es posible eliminar al jugador, ya que está en una partida.");
            } else {
                // Eliminar el jugador si no está en una partida
                for (Persona unaPer : personas) {
                    if (unaPer instanceof Jugador && unaPer.getCi() == ciJugador) {
                        personas.remove(unaPer);
                        System.out.println("El jugador fue eliminado exitosamente.");
                        comprobante = true;
                        break;
                    }
                }

                if (!comprobante) {
                    System.out.println("No se encontró a este jugador.");
                }
            }
        } else if (tipo == 2) {
            System.out.println("Ingrese el CI del árbitro a eliminar. Nos aseguraremos de que no vuelva...");
            int ciArbitro = Integer.parseInt(entrada.nextLine());

            // Verificar si el árbitro está en una partida
            boolean arbitroEnPartida = false;
            for (Partida partida : partidas) {
                if (partida.getArbitro() != null && partida.getArbitro().getCi() == ciArbitro) {
                    arbitroEnPartida = true;
                    break;
                }
            }

            if (arbitroEnPartida) {
                System.out.println("No es posible eliminar al árbitro, ya que está en una partida.");
            } else {
                // Eliminar el árbitro si no está en una partida
                for (Persona unaPer : personas) {
                    if (unaPer instanceof Arbitro && unaPer.getCi() == ciArbitro) {
                        personas.remove(unaPer);
                        System.out.println("El árbitro ha sido eliminado exitosamente.");
                        comprobante = true;
                        break;
                    }
                }

                if (!comprobante) {
                    System.out.println("No se encontró a este árbitro.");
                }
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

    //#endregion

    //#region "ABM Partida"
    public static void AltaPartida(){

        System.out.println("Ingrese el nivel de la partida 1: Regional , 2: Nacional , 3: Internacional ");
        int idTipo = Integer.parseInt(entrada.nextLine());
        if(idTipo < 4 && idTipo > 0){
            System.out.println("Ingrese el ci de el arbitro encargado de la partida");
            int ciArbitro = Integer.parseInt(entrada.nextLine());
                for(Persona unaPersona : personas){
                    if(unaPersona instanceof Arbitro ){
                        if(unaPersona.getCi() == ciArbitro){
                            if(idTipo <= ((Arbitro) unaPersona).getNvl_Certificacion()){
                                Arbitro unArbitro = ((Arbitro) unaPersona);

                                System.out.println("Ingrese el numero la partida");
                                int idPartida = Integer.parseInt(entrada.nextLine());

                                System.out.println("Ingrese el ci de el jugador");
                                int ciJugador1 = Integer.parseInt(entrada.nextLine());

                                System.out.println("Ingrese el ci de el segundo jugador");
                                int ciJugador2 = Integer.parseInt(entrada.nextLine());

                                System.out.println("Ingrese fecha de el partido");
                                Date fecha = new Date(entrada.nextLine());

                                System.out.println("Ingrese el CI del ganador (o presione Enter para dejarlo en blanco): ");
                                String ganadorInput = entrada.nextLine();
                                Persona ganador = null;

                                if (!ganadorInput.isEmpty()) {
                                    int ciGanador = Integer.parseInt(ganadorInput);
                                    for (Persona unPer : personas) {
                                        if (unPer.getCi() == ciGanador) {
                                            ganador = unPer;
                                            break;
                                        }
                                    }
                                }

                                Jugador jugadorUno = null;
                                Jugador jugadorDos = null;
                                for(Persona per : personas){
                                    if(per.getCi() == ciJugador1){
                                        jugadorUno = ((Jugador) per);
                                    }
                                    if(per.getCi() == ciJugador2){
                                        jugadorDos = ((Jugador) per);
                                    }
                                }
                                String tipo = "";
                                if( idTipo == 1){
                                    tipo = "Regional";
                                } else if (idTipo == 2) {
                                    tipo = "Nacional";
                                }else {
                                    tipo = "Internacional";
                                }
                                Partida unaPartida = new Partida(idPartida,jugadorUno,jugadorDos,unArbitro,fecha,tipo, (Jugador) ganador);
                                partidas.add(unaPartida);
                                System.out.println(unaPartida.toString());
                            }else {
                                System.out.println("El nivel de el arbitro no es suficiente para esta partida");
                            }
                        }
                    }
                }

        }
        else {
            System.out.println("Ese nivel no existe en nuestros registros, debe ser 1 ,2 o 3");
        }
    }

    public static void ModificarPartida(){
        System.out.println("---MODIFICAR PARTIDA---");
        System.out.println("Ingresar un id de partida para modificar");
        System.out.println(partidas);
        int opcion = Integer.parseInt(entrada.nextLine());
        Partida porModificar = buscarPartida(opcion);
        if(porModificar != null){
            System.out.println(porModificar.toString());
            System.out.println("Ingrese un nuevo Jugador 1: ");
            int ciJugador1 = Integer.parseInt(entrada.nextLine());
            System.out.println("Ingrese un nuevo Jugador2: ");
            int ciJugador2 = Integer.parseInt(entrada.nextLine());
            System.out.println("Ingresar un nuevo Arbitro: ");
            int ciArbitro = Integer.parseInt(entrada.nextLine());
            System.out.println("Ingresar una nueva Fecha: ");
            String fechaStr = entrada.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = null;
            try {
                fecha = dateFormat.parse(fechaStr);
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Utilice dd/MM/yyyy.");
                return;
            }
            System.out.println("Ingresar un GANADOR: ");
            int ciGanador = Integer.parseInt(entrada.nextLine());
            Jugador jugador1 = buscarJugador(ciJugador1);
            Jugador jugador2 = buscarJugador(ciJugador2);
            Arbitro arbitro  = buscarArbitro(ciArbitro);

            porModificar.setJugador1(jugador1);
            porModificar.setJugador2(jugador2);
            porModificar.setArbitro(arbitro);
            porModificar.setFecha(fecha);

            if(ciGanador != 0){
                Jugador ganador = buscarJugador(ciGanador);
                if (ganador != null){
                    porModificar.setGanador(ganador);
                }else{
                    System.out.println("No se encontre al ganador con ese CI");
                }
            }else{
                porModificar.setGanador(null);
            }
        }else{
            System.out.println("No hay partida con este ID");
        }
    }
    public static void BajaPartida(){
        System.out.println("--- ELIMINAR PARTIDA ---");
       int opcion = 0;

       while(opcion != -1){
           for (Partida unaPartida: partidas){
               System.out.println(unaPartida.toString());
               System.out.println("----------------------");
           }
           System.out.println("Seleccione una partida");
           opcion = Integer.parseInt(entrada.nextLine());
           Partida partida = buscarPartida(opcion);
           if (partida != null) {
               if (partida.getGanador() != null) {
                   System.out.println("La partida no puede eliminarse, ya tiene un ganador.");
               } else {
                   partidas.remove(partida);
                   System.out.println("La partida ha sido eliminada con éxito.");
               }
           } else {
               System.out.println("Partida no encontrada.");
           }

       }
    }
    //#endregion



    //#region "Metodos Auxiliares"
    public static void listarJugadores(){
        for(Persona unaPersona : personas){
            if(unaPersona instanceof Jugador){
                Jugador jugador = (Jugador) unaPersona;
                System.out.println(jugador);
            }
        }
    }
    public static void listarArbitros(){
        for(Persona unaPersona : personas){
            if(unaPersona instanceof Arbitro){
                Arbitro arbitro = (Arbitro) unaPersona;
                System.out.println(arbitro);
            }
        }
    }
    public static void listaPartidas() {
        for (Partida partida : partidas) {
            System.out.println(partida.toString());
        }
    }
    private static Partida buscarPartida(int id){
        for (Partida unaPartida : partidas){
            if(unaPartida.getId() == id){
                return (Partida) unaPartida;
            }
        }
        return null;
    }
    private static Jugador buscarJugador(int ci){
        for(Persona unaPer : personas){
            if(unaPer instanceof Jugador && unaPer.getCi() == ci){
                return (Jugador) unaPer;
            }
        }
        return null;
    }
    private static Arbitro buscarArbitro(int ci){
        for(Persona unaPer : personas){
            if(unaPer instanceof Arbitro && unaPer.getCi() == ci){
                return (Arbitro) unaPer;
            }
        }
        return null;
    }

    private static void MostrarPartidasJugador(){

        System.out.println("Ingrese la cedula de el jugador para ver su historial");
        int ci = Integer.parseInt(entrada.nextLine());

        Jugador jugador = null;

        for (Persona unaPersona: personas){
            if(unaPersona instanceof Jugador && unaPersona.getCi() == ci){
                 jugador = (Jugador) unaPersona;
            }
        }
        if(jugador != null){
            System.out.println("Partidas del jugador "+ jugador.getNombre() + " " +jugador.getApellido());
            for(Partida unaPartida: partidas){
                if(unaPartida.getJugador1() != null && unaPartida.getJugador1().equals(jugador)){
                    System.out.println(unaPartida);
                }
                if(unaPartida.getJugador2() != null && unaPartida.getJugador2().equals(jugador)){
                    System.out.println(unaPartida);
                }
            }
        }
        else
        {
            System.out.println("No se encontro un jugador ni partidas con ese numero de cédula.");
        }
    }

    private static void MostrarPartidasFecha() {
        System.out.println("Ingrese la fecha de la partida en formato 00/00/0000 para ver las partidas jugadas en esa fecha");
        String fechaStr = entrada.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date fecha = dateFormat.parse(fechaStr);

            for (Partida part : partidas) {
                if (part.getFecha().equals(fecha)) {
                    System.out.println(part);
                    System.out.println("---------------------------------------------------------------------");
                }
            }
        } catch (ParseException e) {
            System.out.println("Fecha ingresada en formato incorrecto. Debe ser 00/00/0000.");
        }
    }


    private static void historialDePartidas(){
        System.out.println("Ingrese un ID partida");
        int idPartida = Integer.parseInt(entrada.nextLine());
        for(Partida unaPartida : partidas){
            if(unaPartida.getId() == idPartida){
                System.out.println("Fecha: "+unaPartida.getFecha()+" Resultado: "+unaPartida.getGanador());
            }
        }
    }
    //#endregion
}