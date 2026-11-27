package mx.edu.utez.gimnasio.exeption;

public class CustomException extends RuntimeException{
    public CustomException(String mensaje) {
        super(mensaje);
    }
}
