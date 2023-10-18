import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Persona> personas = new ArrayList<>();


    public static void main(String[] args) {
        int opcion = 0;

        while(opcion !=-1){
            System.out.println("** Gestion Personas **");
            System.out.println("Ingrese -1 para salir");
            System.out.println("Seleccione la opcion que desea: ");
            System.out.println("1. Dar de alta ");
        }


        public static boolean altaPersona(){

            int opcion = 0;

            while (opcion != -1){
                System.out.println("Seleccione el tipo de alta: ");
                System.out.println("1- Jugador");
                System.out.println("2- Árbitro");
                opcion = Integer.parseInt(entrada.nextLine());

                if (opcion == 1){
                    System.out.println("Ingrese ID: ");
                    int id = Integer.parseInt(entrada.nextLine());

                    System.out.println("Ingrese el nombre: ");
                    String nombre = entrada.nextLine();

                    System.out.println("Ingrese apellido; ");
                    String apellido = entrada.nextLine();

                    System.out.println("Ingrese pais: ");
                    String pais = entrada.nextLine();

                    System.out.println("Ingrese la edad: ");
                    short edad = short.parseDouble(entrada.nextLine());

                    Jugadador unJugador = new Jugador(id, nombre, apellido, pais, edad);

                    personas.add(unJugador);
                }
            }
        }

    }
}