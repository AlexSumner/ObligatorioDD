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

            opcion = Integer.parseInt(entrada.nextLine());

            switch(opcion){
                case 1 :{
                    altaPersona();
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
}