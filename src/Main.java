import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Persona> personas = new ArrayList<>();

    public static void main(String[] args) {

        int opcion = 0;

        while (opcion != -1){
            System.out.println("Gestion Campeonato Ajedrez ");
            System.out.println("Ingrese -1 para salir del programa.");
            System.out.println("** Seleccione la opción que desee: ");
            System.out.println("1. Alta de Jugador/Árbitro.");
            System.out.println("2. Lista de Jugadores.");
            System.out.println("3. Lista de Árbitros.");
            System.out.println("4. Modificar Persona");
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
                default:{
                    if(opcion != -1){
                        System.out.println("Ingrese una opción válida.");
                    }
                }
            }
        }

    }
    public static boolean altaPersona(){
        int opcion = 0;

        while(opcion != -1){
            System.out.println("Seleccione la persona que desea ingresar: ");
            System.out.println("1. Jugador");
            System.out.println("2. Árbitro");

            opcion = Integer.parseInt(entrada.nextLine());

            if(opcion == 1){
                System.out.println("Ingrese un numero identificatorio:");
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

                System.out.println("Ingrese un numero identificatorio:");
                int id = Integer.parseInt(entrada.nextLine());

                System.out.println("Ingrese el nombre: ");
                String nombre = entrada.nextLine();

                System.out.println("Ingrese el apellido:");
                String apellido = entrada.nextLine();

                System.out.println("Ingrese el pais: ");
                String pais = entrada.nextLine();

                System.out.println("Ingrese el nivel de certificación: ");
                short nvl_Certificacion = Short.parseShort(entrada.nextLine());

                Persona unArbitro = new Arbitro(id, nombre, apellido, pais, nvl_Certificacion);

                personas.add(unArbitro);

                System.out.println(unArbitro);

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
                if (unaPersona.getId() == opcion){
                    System.out.println("Ingrese un nuevo nombre: ");
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese un nuevo apellido: ");
                    String apellido = entrada.nextLine();
                    System.out.println("Ingrese un nuevo pais: ");
                    String pais = entrada.nextLine();
                    if (unaPersona instanceof Jugador ){
                        System.out.println("Ingrese un nuevo valor de elo: ");
                        short elo = Short.parseShort(entrada.nextLine());
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
    //#endregion
}