package registro;
import java.util.Scanner;
/**
* La clase RegistroUsuario se utiliza para registrar un nuevo usuario en el sistema.
* Permite al usuario introducir su nombre de usuario, correo electrónico, contraseña y un código de seguridad autogenerado.
* 
* @author Joan&Alejandro
*/
public class RegistroUsuario {
    private static final String[] BASE_DATOS = {"Juan_123", "Maria-456", "Pedro_789", "Sofia-234",
                                                "Luisa_567", "Carlos-890", "Ana_321", "Pablo-654",
                                                "Lucas_987", "Laura-654"};
    
    private static final String[] usuarios = {"Pepito_123","Juanan_567",};
    
    private ValidarCampos validarCampos; // Creamos un objeto de tipo ValidarCampos

	private String nombreUsuario;

	private String email;

	private String password;

	private String codigoGenerado;

	private String codigoIntroducido;
    
    public void init() {
        Scanner scanner = new Scanner(System.in);
        validarCampos = new ValidarCampos(codigoGenerado, null, codigoGenerado, codigoGenerado, codigoGenerado, null); // Inicializamos el objeto ValidarCampos
        
        System.out.print("Introduzca su nombre de usuario: ");
        nombreUsuario = scanner.nextLine();
        while (!validarCampos.compruebaNombre(nombreUsuario, BASE_DATOS, usuarios)) {
            System.out.println("El nombre de usuario introducido no es válido o ya existe");
            System.out.print("Introduzca un nombre de usuario válido: ");
            nombreUsuario = scanner.nextLine();
        }
        
        System.out.print("Introduzca su correo electrónico: ");
        email = scanner.nextLine();
        while (!validarCampos.compruebaEmail(email)) {
            System.out.println("El correo electrónico introducido no es válido");
            System.out.print("Introduzca un correo electrónico válido: ");
            email = scanner.nextLine();
        }
        
        System.out.print("Introduzca su contraseña: ");
        password = scanner.nextLine();
        while (!validarCampos.compruebaPassword(password)) {
            System.out.println("La contraseña introducida no es válida");
            System.out.print("Introduzca una contraseña válida: ");
            password = scanner.nextLine();
        }
        
        codigoGenerado = validarCampos.generaCodigoSeguridad();
        System.out.println("El código de seguridad autogenerado es: " + codigoGenerado);
        
        System.out.print("Introduzca el código de seguridad mostrado: ");
        codigoIntroducido = scanner.nextLine();
        while (!codigoIntroducido.equals(codigoGenerado)) {
            System.out.println("El código de seguridad introducido no coincide");
            System.out.print("Introduzca el código de seguridad mostrado: ");
            codigoIntroducido = scanner.nextLine();
        }
        
        System.out.println("El registro se ha realizado con éxito");
        System.out.println("Nombre: " + nombreUsuario);
        System.out.println("Correo electrónico: " + email);
        System.out.println("Contraseña: " + password);
        System.out.println("Código de seguridad: " + codigoIntroducido);
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        RegistroUsuario registro = new RegistroUsuario();
        registro.init(); // Llamamos al método init para ejecutar el registro de usuario
    }
}

