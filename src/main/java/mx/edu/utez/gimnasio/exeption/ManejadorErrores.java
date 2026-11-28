package mx.edu.utez.gimnasio.exeption;

public class ManejadorErrores extends RuntimeException {
    public ManejadorErrores(String message) {
        super(message);
    }
}
