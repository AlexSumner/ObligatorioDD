import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Persona> personas = new ArrayList<>();
    static ArrayList<Partida> partidas = new ArrayList<>();
    static File miArchivo = new File("archivo.txt");


    public static void main(String[] args) {


        if(!miArchivo.exists()){
            try {
                miArchivo.createNewFile();
                System.out.println(miArchivo.getName()+"fue creado!");
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        importarTxt();

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
            System.out.println("13. Mostrar sueldo para un juez");
            System.out.println("14. Mostrar sueldo para un jugador");
            System.out.println("15. Atribucion de puntaje");
            System.out.println("16. Cargar archivo txt");
            opcion = Integer.parseInt(entrada.nextLine());

            switch(opcion){
                case 1 :{
                    if( altaPersona()){
                        System.out.println("Se ha ingresado con exito");
                    }else
                        System.out.println("No se pudo ingresar");

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
                case 13: {
                    DineroJuez();
                    break;
                }
                case 14: {
                    DineroJugador();
                    break;
                }
                case 15:{
                    atribucionDePuntaje();
                    break;
                }
                case 16: {
                    guardarArchivos();
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
    //#region "Archivo TXT"
    public static void importarTxt(){

    try (BufferedReader br = new BufferedReader(new FileReader("archivo.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            // Parsea la línea para obtener los datos, por ejemplo, separando por comas
            String[] datos = linea.split(",");

            if (datos.length == 6) { // Jugador
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                String pais = datos[3];
                short edad = Short.parseShort(datos[4]);
                int elo = Integer.parseInt(datos[5]);


                Persona persona = new Jugador(id, nombre, apellido, pais, elo, edad);
                personas.add(persona);
            }

            if (datos.length == 5) { // Arbitro

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                String pais = datos[3];
                short nivelCertificacion = Short.parseShort(datos[4]);

                // Crea un objeto Persona y agrégalo al ArrayList
                Persona persona = new Arbitro(id, nombre, apellido, pais,nivelCertificacion);
                personas.add(persona);

            }


            if (datos.length == 7) { // Partida

                int id = Integer.parseInt(datos[0]);
                Jugador jugador1 = buscarJugador(Integer.parseInt(datos[1]));
                Jugador jugador2 = buscarJugador(Integer.parseInt(datos[2]));
                Arbitro Arbitro = buscarArbitro(Integer.parseInt(datos[3]));
                Date fechaPartido;
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                     fechaPartido = dateFormat.parse(datos[4]);;
                }catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String tipoPartida = datos[5];
                Jugador Ganador;
                if(datos[6].equals("null")){
                    Ganador = null;
                }else{
                    Ganador = buscarJugador(Integer.parseInt(datos[6]));
                }



                // Crea un objeto Persona y agrégalo al ArrayList
                Partida partidasa = new Partida(id, jugador1, jugador2, Arbitro,fechaPartido,tipoPartida,Ganador);
                partidas.add(partidasa);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static void guardarArchivos() {
        try {
            File archivo = new File("archivo.txt");

            if (archivo.exists()) {
                archivo.delete();
            }

            archivo.createNewFile();

            FileWriter fileWriter = new FileWriter(archivo, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Registro de Personas: ");
            bufferedWriter.newLine();

            for (Persona persona : personas) {
                if(persona instanceof Arbitro){
                    bufferedWriter.write(persona.getCi() + "," + persona.getNombre() + ","+ persona.getApellido() + "," + persona.getPais() + "," + ((Arbitro) persona).getNvl_Certificacion() );
                    bufferedWriter.newLine();
                }else if(persona instanceof Jugador) {
                    bufferedWriter.write(persona.getCi() + "," + persona.getNombre() +","+ persona.getApellido() + "," + persona.getPais() + "," + ((Jugador) persona).getEdad() + "," + ((Jugador) persona).getElo());
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("Registro de partidas: ");
            bufferedWriter.newLine();

            for (Partida partida : partidas) {
                if( partida.getGanador() == null){
                    bufferedWriter.write( partida.getId() + "," + partida.getJugador1().getCi() +  "," + partida.getJugador2().getCi() + "," + partida.getArbitro().getCi() + "," + partida.getFecha() + "," + partida.getTipoPartida() + "," + null);
                    bufferedWriter.newLine();
                }else{
                    bufferedWriter.write( partida.getId() + "," + partida.getJugador1().getCi() +  "," + partida.getJugador2().getCi() + "," + partida.getArbitro().getCi() + "," + partida.getFecha() + "," + partida.getTipoPartida() + "," + partida.getGanador().getCi());
                    bufferedWriter.newLine();
                }
            }
            // tomar los datos para recrear el objeto partida que va a ir al arrayList
            // puedo tomar los datos de las personas solo con id para luego buscarlos?

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //#endregion
    //#region "ABM Persona"
    public static boolean altaPersona() {
        int opcion = 0;
        while (opcion != -1) {
            System.out.println("Seleccione la persona que desea ingresar: ");
            System.out.println("Ingrese -1 para salir.");
            System.out.println("1. Jugador");
            System.out.println("2. Árbitro");

            try {
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida (número entero).");
                continue;
            }

            if (opcion == 1) {
                try {
                    System.out.println("Ingrese Cédula de Identidad sin puntos ni guión:");
                    int id = Integer.parseInt(entrada.nextLine());

                    if (id <= 0) {
                        System.out.println("La cédula de identidad debe ser un número positivo.");
                        continue;
                    }


                        if (!controlCi(id)) {
                        return false;
                    }

                    System.out.println("Ingrese el nombre: ");
                    String nombre = entrada.nextLine();

                    if (esNumero(nombre)) {
                        System.out.println("El nombre no puede ser un número.");
                        continue;
                    }
                    if (nombre.trim().isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        continue;
                    }

                    System.out.println("Ingrese el apellido:");
                    String apellido = entrada.nextLine();

                    if (esNumero(apellido)) {
                        System.out.println("El apellido no puede ser un número.");
                        continue;
                    }
                    if (apellido.trim().isEmpty()) {
                        System.out.println("El apellido no puede estar vacío.");
                        continue;
                    }


                    System.out.println("Ingrese el país: ");
                    String pais = entrada.nextLine();

                    if (esNumero(pais)) {
                        System.out.println("El país no puede ser un número.");
                        continue;
                    }
                    if (pais.trim().isEmpty()) {
                        System.out.println("El país no puede estar vacío.");
                        continue;
                    }

                    System.out.println("Ingrese el valor ELO del jugador: ");
                    int elo = Integer.parseInt(entrada.nextLine());

                    if (elo < 0) {
                        System.out.println("El valor ELO del jugador debe ser un número positivo o cero.");
                        continue;
                    }

                    System.out.println("Ingrese la edad del jugador: ");
                    short edad = Short.parseShort(entrada.nextLine());

                    Persona unJugador = new Jugador(id, nombre, apellido, pais, elo, edad);

                    personas.add(unJugador);
                    System.out.println(unJugador);
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println("Error en el formato de entrada. Por favor, ingrese datos válidos.");
                }
            } else if (opcion == 2) {
                try {
                    System.out.println("Ingrese Cédula de Identidad:");
                    int id = Integer.parseInt(entrada.nextLine());

                    if (id <= 0) {
                        System.out.println("La cédula de identidad debe ser un número positivo.");
                        continue;
                    }

                    if (!controlCi(id)) {
                        System.out.println("La cédula de esta persona ya se encuentra registrada");
                        return false;
                    }

                    System.out.println("Ingrese el nombre: ");
                    String nombre = entrada.nextLine();

                    if (esNumero(nombre)) {
                        System.out.println("El nombre no puede ser un número.");
                        continue;
                    }
                    if (nombre.trim().isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        continue;
                    }

                    System.out.println("Ingrese el apellido:");
                    String apellido = entrada.nextLine();

                    if (esNumero(apellido)) {
                        System.out.println("El apellido no puede ser un número.");
                        continue;
                    }
                    if (apellido.trim().isEmpty()) {
                        System.out.println("El apellido no puede estar vacío.");
                        continue;
                    }


                    System.out.println("Ingrese el país: ");
                    String pais = entrada.nextLine();

                    if (esNumero(pais)) {
                        System.out.println("El país no puede ser un número.");
                        continue;
                    }
                    if (pais.trim().isEmpty()) {
                        System.out.println("El país no puede estar vacío.");
                        continue;
                    }

                    System.out.println("Ingrese el nivel de certificación (del 1 al 3): ");
                    short nvl_Certificacion = Short.parseShort(entrada.nextLine());

                    if (nvl_Certificacion < 1 || nvl_Certificacion > 3) {
                        System.out.println("El nivel de certificación debe estar entre 1 y 3.");
                        continue;
                    }

                    Persona unArbitro = new Arbitro(id, nombre, apellido, pais, nvl_Certificacion);
                    personas.add(unArbitro);

                    System.out.println("Árbitro registrado con éxito: " + unArbitro);
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println("Error en el formato de entrada. Por favor, ingrese datos válidos.");
                }
            } else if (opcion != -1) {
                System.out.println("Ingrese una opción válida.");
            }
        }
        return false;
    }

    public static boolean modificarPersona() {
        System.out.println("Seleccione un ID de persona que desea modificar");
        int opcion = 0;
        try {
            opcion = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese una opción válida (número entero).");
            return false;
        }
        boolean validacion = false;
        for (Persona unaPersona : personas) {
            if (unaPersona.getCi() == opcion) {
                validacion = true;

               try {
                    System.out.println("Ingrese el nombre: ");
                    String nombre = entrada.nextLine();

                    if (esNumero(nombre)) {
                        System.out.println("El nombre no puede ser un número.");
                        continue;
                    }
                    if (nombre.trim().isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        continue;
                    }

                    System.out.println("Ingrese el apellido:");
                    String apellido = entrada.nextLine();

                    if (esNumero(apellido)) {
                        System.out.println("El apellido no puede ser un número.");
                        continue;
                    }
                    if (apellido.trim().isEmpty()) {
                        System.out.println("El apellido no puede estar vacío.");
                        continue;
                    }


                    System.out.println("Ingrese el país: ");
                    String pais = entrada.nextLine();

                    if (esNumero(pais)) {
                        System.out.println("El país no puede ser un número.");
                        continue;
                    }
                    if (pais.trim().isEmpty()) {
                        System.out.println("El país no puede estar vacío.");
                        continue;
                    }

                    if (unaPersona instanceof Jugador) {
                        System.out.println("Ingrese un nuevo valor de elo: ");
                        int elo = Integer.parseInt(entrada.nextLine());

                        if (elo < 0) {
                            System.out.println("El valor ELO del jugador debe ser un número positivo o cero.");
                            continue;
                        }

                        System.out.println("Ingrese una nueva edad: ");
                        short edad = -1 ;

                        try{
                            edad = Short.parseShort(entrada.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Ingrese una edad valida.");
                            return false;
                        }

                        ((Jugador) unaPersona).setElo(elo);
                        ((Jugador) unaPersona).setEdad(edad);
                    } else if (unaPersona instanceof Arbitro) {
                        System.out.println("Ingrese un nuevo Nivel de Certificación: ");
                        short nvl_Certificacion = Short.parseShort(entrada.nextLine());

                        if (nvl_Certificacion < 1 || nvl_Certificacion > 3) {
                            System.out.println("El nivel de certificación debe estar entre 1 y 3.");
                            continue;
                        }

                        ((Arbitro) unaPersona).setNvl_Certificacion(nvl_Certificacion);
                    }

                unaPersona.setNombre(nombre);
                unaPersona.setApellido(apellido);
                unaPersona.setPais(pais);
            }catch (NumberFormatException e) {
                System.out.println("Error en el formato de entrada. Por favor, ingrese datos válidos.");
                return false;
            }
                System.out.println("Persona modificada: " + unaPersona);
                return true;
            }
        }
        if (validacion){
            return false;
        }
        System.out.println("No se encontró persona con ese número de ID.");
        return false;
    }
    public static void BajaPersona() {
        System.out.println("¿Qué desea eliminar, Jugadores (1) o Árbitro (2)?");
        short tipo = 0;

        try {
            tipo = Short.parseShort(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese una opción válida (número entero).");
            return;
        }

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
        int idTipo = -1;
        try{
            idTipo = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese una opcion valida");
            return;
        }

        if(idTipo < 4 && idTipo > 0){
            System.out.println("Ingrese el ci de el arbitro encargado de la partida");
            int ciArbitro = 0;
            try{
                ciArbitro = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una cedula valida");
                return;
            }
            for(Persona unaPersona : personas){
                if(unaPersona instanceof  Jugador){
                    if(unaPersona.getCi() == ciArbitro){
                        System.out.println("El arbitro no puede ser un jugador");
                        return;
                    }
                }
                if(unaPersona instanceof Arbitro ){
                    if(unaPersona.getCi() == ciArbitro){
                        if(idTipo <= ((Arbitro) unaPersona).getNvl_Certificacion()){
                            Arbitro unArbitro = ((Arbitro) unaPersona);

                            System.out.println("Ingrese el numero la partida");
                            int idPartida = -1;
                                try{
                                idPartida =Integer.parseInt(entrada.nextLine());
                                } catch (NumberFormatException e) {
                                System.out.println("Ingrese un id valido");
                                return;
                            }
                            if(!controlIdPartida(idPartida)){
                                System.out.println("El id de esta partida ya se encuentra registrado");
                                return;
                            }
                            if (!String.valueOf(idPartida).matches("\\d+")) {
                                System.out.println("El número de partida no puede contener letras ni caracteres especiales.");
                                return;
                            }
                            System.out.println("Ingrese el ci de el jugador");
                            int ciJugador1 = -1;

                            try{
                                ciJugador1 = Integer.parseInt(entrada.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Ingrese una cedula valida");
                                return;
                            }

                            int ciJugador2;
                            do {
                                System.out.println("Ingrese el ci del segundo jugador");
                                ciJugador2 = Integer.parseInt(entrada.nextLine());
                                if (ciJugador1 == ciJugador2) {
                                    System.out.println("Un jugador no puede jugar contra sí mismo. Por favor, elija dos jugadores diferentes.");
                                }
                            } while (ciJugador1 == ciJugador2);

                            Date fecha = null;
                            boolean fechaValida = false;

                            while (!fechaValida) {
                                System.out.println("Ingrese la fecha del partido (formato dd/MM/yyyy):");
                                String fechaInput = entrada.nextLine();

                                if (esFechaValida(fechaInput)) {
                                    try {
                                        fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInput);
                                    } catch (ParseException e) {
                                        throw new RuntimeException(e);
                                    }
                                    fechaValida = true;
                                } else {
                                    System.out.println("Fecha inválida o formato incorrecto. Intente nuevamente.");
                                }
                            }

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


                            Jugador jugadorUno = buscarJugador(ciJugador1);
                            Jugador jugadorDos = buscarJugador(ciJugador2);

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
                            return;
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

        for (Partida unaPartida: partidas){
            System.out.println(unaPartida.toString());
            System.out.println("----------------------");
        }
        System.out.println("Seleccione una partida");
        int idPartida = Integer.parseInt(entrada.nextLine());
        Partida partida = buscarPartida(idPartida);
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
    //#endregion



    //#region "Metodos Auxiliares"
    public static void listarJugadores(){
        boolean validar = false;
        for(Persona unaPersona : personas){
            if(unaPersona instanceof Jugador){
                Jugador jugador = (Jugador) unaPersona;
                validar = true;
                System.out.println(jugador);
            }
        }
        if(!validar){
            System.out.println("No se encuentran Jugadores registrados, ingrese uno");
        }
    }

    public static void listarArbitros(){
        boolean validar = false;
        for(Persona unaPersona : personas){
            if(unaPersona instanceof Arbitro){
                Arbitro arbitro = (Arbitro) unaPersona;
                validar = true;
                System.out.println(arbitro);
            }
        }
        if(!validar){
            System.out.println("No se encuentran Arbitros registrados, ingrese uno");
        }
    }

    public static void listaPartidas() {
        boolean validar = false;
        for (Partida partida : partidas) {
            System.out.println(partida.toString());
            validar = true;
        }
        if(!validar){
            System.out.println("No se encuentran Partidas registrados, ingrese uno");
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
            if(esFechaValida(fechaStr)){


                Date fecha = dateFormat.parse(fechaStr);

                for (Partida part : partidas) {
                    if (part.getFecha().equals(fecha)) {
                        System.out.println(part);
                        System.out.println("---------------------------------------------------------------------");
                    }
                }

            }else
                System.out.println("Fecha ingresada incorrectamente");
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

    private static boolean controlCi(int id){
        for(Persona unaPersonaValidacionCi : personas){
            if(unaPersonaValidacionCi.getCi() == id){
                System.out.println("La cédula de esta persona ya se encuentra registrada");
                return false;
            }
        }
        if (!(String.valueOf(id).length() == 8 && String.valueOf(id).matches("\\d+"))) {
            System.out.println("La cédula debe contener exactamente 8 números y no debe contener letras.");
            return false;
        }
        return true;
    }

    private static boolean controlIdPartida(int id){
        for(Partida PartidasValidacionId : partidas){
            if(PartidasValidacionId.getId() == id){
                return false;
            }
        }
        return true;
    }


    public static boolean esFechaValida(String fechaStr) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date fecha = dateFormat.parse(fechaStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            if (year >= 2000 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                return true;
            }
        } catch (ParseException e) {

        }
        return false;
    }

    private static void DineroJuez () {
        try {
            System.out.println("Ingrese la cedula de identidad de el juez");

            int ci = Integer.parseInt(entrada.nextLine());
            int total = 0;
            boolean comprobante = false;
            for(Persona Jugador : personas){
                if(Jugador instanceof Jugador){
                    if(Jugador.getCi() == ci){
                        System.out.println("El juez no es ningun jugador");
                        comprobante = true;
                    }
                }
            }

            if(!comprobante){
                for (Partida par : partidas) {
                    if (par.getArbitro().getCi() == ci) {
                        total += 500;
                    }
                }
                System.out.println("El total de dinero que ha cobrado este arbitro es: " + total);
            }



        } catch (Exception e) {
            System.out.println("A ocurrido un error en el codigo: " + e);
        }
    }

    private static void DineroJugador () {
        try {


            System.out.println("Ingrese la cedula de identidad de el Jugador");

            int ci = Integer.parseInt(entrada.nextLine());
            int total = 0;

            boolean comprobante = false;
            for(Persona Arbitro : personas){
                if(Arbitro instanceof Arbitro){
                    if(Arbitro.getCi() == ci){
                        System.out.println("El jugador no es ningun juez");
                        comprobante = true;
                    }
                }
            }

            if(!comprobante){
                for (Partida par : partidas) {
                    if (par.getJugador1().getCi() == ci || par.getJugador2().getCi() == ci) {
                        total += 600;
                    }
                }
                System.out.println("El total de dinero que ha cobrado este jugador es: " + total);
            }




        } catch (Exception e) {
            System.out.println("A ocurrido un error en el codigo: " + e);
        }
    }

    private static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static void atribucionDePuntaje() {
        System.out.println("Ingresar un ID de partida");
        int idIngresada = Integer.parseInt(entrada.nextLine());

        for (Partida unaPartida : partidas) {
            if(unaPartida.getGanador() == null){
                System.out.println("La partida no tiene ningun resultado");
            }else {
                if (unaPartida.getId() == idIngresada) {
                    Jugador ganador = unaPartida.getGanador();
                    Jugador perdedor;

                    if (ganador == unaPartida.getJugador1()) {
                        perdedor = unaPartida.getJugador2();
                    } else {
                        perdedor = unaPartida.getJugador1();
                    }
                    int diferenciaDePuntajes = ganador.getElo() - perdedor.getElo();
                    int puntajeAtribuido;
                    if (diferenciaDePuntajes > 0) {
                        puntajeAtribuido = diferenciaDePuntajes / 4;
                    } else {
                        puntajeAtribuido = diferenciaDePuntajes / 8;
                    }
                    //Aca le estariamos modificando los nuevos puntajes a cada uno
                    ganador.setElo(ganador.getElo() - puntajeAtribuido);
                    perdedor.setElo(perdedor.getElo() + puntajeAtribuido);

                    System.out.println("Puntaje atribuido al ganador: " + puntajeAtribuido);
                    System.out.println("Nuevo puntaje del ganador: " + ganador.getElo());
                    System.out.println("Nuevo puntaje del perdedor: " + perdedor.getElo());
                }
            }
        }
    }


//#endregion

}