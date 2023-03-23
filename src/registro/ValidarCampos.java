package registro;
import java.util.Random;

/**
 * Clase que contiene métodos para validar campos como nombre de usuario,
 * correo electrónico, contraseña y código de seguridad.
 */
class ValidarCampos {
    /**
     * Expresión regular utilizada para validar el formato del nombre de usuario.
     */
    public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
    /**
     * Arreglo que guarda las partes del correo electrónico después de ser separado por el carácter '.'.
     */
	public String[] getPartes() {
		return partes;
	}

	public void setPartes(String[] partes) {
		this.partes = partes;
	}
    /**
     * Dominio permitido del correo electrónico.
     */
	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getRegex2() {
		return regex2;
	}
    /**
     * Expresión regular utilizada para validar el formato de la contraseña.
     */
	public void setRegex2(String regex2) {
		this.regex2 = regex2;
	}

	public String getCaracteres() {
		return caracteres;
	}
    /**
     * Caracteres permitidos para generar el código de seguridad.
     */
	public void setCaracteres(String caracteres) {
		this.caracteres = caracteres;
	}

	public StringBuilder getCodigo() {
		return codigo;
	}
    /**
     * Código de seguridad generado automáticamente para cada usuario.
     */
	public void setCodigo(StringBuilder codigo) {
		this.codigo = codigo;
	}
    /**
     * Constructor de la clase ValidarCampos.
     *
     * @param regex       expresión regular para validar el formato del nombre de usuario.
     * @param partes      arreglo que guarda las partes del correo electrónico después de ser separado.
     * @param dominio     dominio permitido del correo electrónico.
     * @param regex2      expresión regular para validar el formato de la contraseña.
     * @param caracteres  caracteres permitidos para generar el código de seguridad.
     * @param codigo      código de seguridad generado automáticamente para cada usuario.
     */
	public ValidarCampos(String regex, String[] partes, String dominio, String regex2, String caracteres,
			StringBuilder codigo) {
		super();
		this.regex = regex;
		this.partes = partes;
		this.dominio = dominio;
		this.regex2 = regex2;
		this.caracteres = caracteres;
		this.codigo = codigo;
	}

	private static final String[] DOMINIOS_PERMITIDOS = {"paucasesnovescifp", "yahoo", "gmail", "hotmail"};
    private static final Random RANDOM = new Random();
	private String regex;
	private String[] partes;
	private String dominio;
	private String regex2;
	private String caracteres;
	private StringBuilder codigo;
    
    public boolean compruebaNombre(String nombre, String[] baseDatos, String[] usuarios) {
        if (nombre.length() > 16) {
            return false;
        }
        regex = "^[A-Z][a-z]*[-_][0-9]{3}$";
        if (!nombre.matches(regex)) {
            return false;
        }
        for (String nombreExistente : baseDatos) {
            if (nombre.equals(nombreExistente)) {
                return false;
            }
        }
        for (String nombreExistente : usuarios) {
            if (nombre.equals(nombreExistente)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean compruebaEmail(String email) {
        if (!email.contains("@")) {
            return false;
        }
        partes = email.split("\\.");
        if (partes.length != 2 || partes[1].length() < 2 || partes[1].length() > 3) {
            return false;
        }
        dominio = partes[0].substring(partes[0].indexOf('@') + 1);
        for (String dominioPermitido : DOMINIOS_PERMITIDOS) {
            if (dominio.equals(dominioPermitido)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean compruebaPassword(String password) {
        if (password.length() != 8) {
            return false;
        }
        regex2 = "^[A-Z][a-zA-Z0-9]*[@#_-][0-9]{2}$";
        return password.matches(regex2);
    }
    
    public String generaCodigoSeguridad() {
        caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#_-";
        codigo = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int indice = RANDOM.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }
        return codigo.toString();
    }
    
}
