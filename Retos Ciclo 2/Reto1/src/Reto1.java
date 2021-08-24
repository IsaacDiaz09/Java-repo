package Reto1.src;

import java.util.Scanner;

/**
 * @author Isaac Diaz
 * Reto 1 G15 Indice de masa corporal
 */
class Reto1 {

    /**
     * Objeto de tipo scanner para recibir datos de teclado
     */
    private final static Scanner SC = new Scanner(System.in);

    /**
     *
     * @return string de lo que encuentre el scanner
     */
    private static String read() {
        return SC.nextLine();
    }

    /**
     * metodo main
     */
    public static void main(String[] arg) {
        /**
         * Metodo principal a ejecutar
         */
        run();
    }

    /**
     * metodo principal con la solución propuesta
     */
    public static void run() {
        /**
         * Recepcion de datos
         */
        int anos;
        float masa;
        float altura;
        boolean error = false;
        String[] datos = read().split(" ");
        masa = Float.parseFloat(datos[0]);
        altura = Float.parseFloat(datos[1]);
        anos = Integer.parseInt(datos[2]);

        /**
         * Se comprueba si los datos recibidos estan dentro de sus
         * rangos permitidos
         */ 
        if (masa < 0 || masa > 150) {
            error = true;
        } else if (altura < 0.1 || altura > 2.5) {
            error = true;
        } else if (anos < 0 || anos > 110) {
            error = true;
        }
        /**
         * Comprobación de condiciones para la entrega del resultado
         */
        if (error) {
            System.out.println("ERROR");
        } else {

            double imc = masa / Math.pow(altura, 2);
            if (anos <= 45 && imc < 22) {
                System.out.printf("%1.1f", imc);
                System.out.print(" RIESGO BAJO");
            } else if (anos > 45 && imc < 22) {
                System.out.printf("%1.1f", imc);
                System.out.print(" RIESGO MEDIO");
            } else if (anos > 45 && imc >= 22) {
                System.out.printf("%1.1f", imc);
                System.out.print(" RIESGO ALTO");
            } else if (anos <= 45 && imc >= 22) {
                System.out.printf("%1.1f", imc);
                System.out.print(" RIESGO MEDIO");
            }
        }
    }
}
