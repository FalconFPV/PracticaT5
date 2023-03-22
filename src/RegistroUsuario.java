import java.util.Random;
import java.util.Scanner;

public class RegistroUsuario {
    
    private static final String[] BASE_DATOS = {"Juan_123", "Maria-456", "Pedro_789", "Sofia-234",
                                                "Luisa_567", "Carlos-890", "Ana_321", "Pablo-654",
                                                "Lucas_987", "Laura-654"};
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduzca su nombre de usuario: ");
        String nombre = scanner.nextLine();
        while (!ValidarCampos.compruebaNombre(nombre, BASE_DATOS)) {
            System.out.println("El nombre de usuario introducido no es válido o ya existe");
            System.out.print("Introduzca un nombre de usuario válido: ");
            nombre = scanner.nextLine();
        }
        
        System.out.print("Introduzca su correo electrónico: ");
        String email = scanner.nextLine();
        while (!ValidarCampos.compruebaEmail(email)) {
            System.out.println("El correo electrónico introducido no es válido");
            System.out.print("Introduzca un correo electrónico válido: ");
            email = scanner.nextLine();
        }
        
        System.out.print("Introduzca su contraseña: ");
        String password = scanner.nextLine();
        while (!ValidarCampos.compruebaPassword(password)) {
            System.out.println("La contraseña introducida no es válida");
            System.out.print("Introduzca una contraseña válida: ");
            password = scanner.nextLine();
        }
        
        String codigoGenerado = ValidarCampos.generaCodigoSeguridad();
        System.out.println("El código de seguridad autogenerado es: " + codigoGenerado);
        
        System.out.print("Introduzca el código de seguridad mostrado: ");
        String codigoIntroducido = scanner.nextLine();
        while (!codigoIntroducido.equals(codigoGenerado)) {
            System.out.println("El código de seguridad introducido no coincide");
            System.out.print("Introduzca el código de seguridad mostrado: ");
            codigoIntroducido = scanner.nextLine();
        }
        
        System.out.println("El registro se ha realizado con éxito");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo electrónico: " + email);
        System.out.println("Contraseña: " + password);
        System.out.println("Código de seguridad: " + codigoIntroducido);
        
        scanner.close();
    }
    
}

class ValidarCampos {
    
        private static final String[] DOMINIOS_PERMITIDOS = {"paucasesnovescifp", "yahoo", "gmail", "hotmail"};
        private static final Random RANDOM = new Random();
        
        public static boolean compruebaNombre(String nombre, String[] baseDatos) {
            if (nombre.length() > 16) {
                return false;
            }
            String regex = "^[A-Z][a-z]*[-_][0-9]{3}$";
            if (!nombre.matches(regex)) {
                return false;
            }
            for (String nombreExistente : baseDatos) {
                if (nombre.equals(nombreExistente)) {
                    return false;
                }
            }
            return true;
        }
        
        public static boolean compruebaEmail(String email) {
            if (!email.contains("@")) {
                return false;
            }
            String[] partes = email.split("\\.");
            if (partes.length != 2 || partes[1].length() < 2 || partes[1].length() > 3) {
                return false;
            }
            String dominio = partes[0].substring(partes[0].indexOf('@') + 1);
            for (String dominioPermitido : DOMINIOS_PERMITIDOS) {
                if (dominio.equals(dominioPermitido)) {
                    return true;
                }
            }
            return false;
        }
    
        public static boolean compruebaPassword(String password) {
            if (password.length() != 8) {
                return false;
            }
            String regex = "^[A-Z][a-zA-Z0-9]*[@#_-][0-9]{2}$";
            return password.matches(regex);
        }
        
        public static String generaCodigoSeguridad() {
            String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#_-";
            StringBuilder codigo = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                int indice = RANDOM.nextInt(caracteres.length());
                codigo.append(caracteres.charAt(indice));
            }
            return codigo.toString();
        }
}
