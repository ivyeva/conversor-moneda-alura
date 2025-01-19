package conversor.principal;
import com.google.gson.Gson;
import conversor.modelos.Connection;
import conversor.modelos.MenuPrincipal;

public class Principal {
    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal(new Connection(), new Gson());
        menu.mainMenu();
    }
}