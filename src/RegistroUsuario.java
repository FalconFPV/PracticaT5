import java.util.Random;

public class RegistroUsuario {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
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
}
