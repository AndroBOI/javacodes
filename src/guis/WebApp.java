package  guis;

import java.awt.Desktop;
import java.net.URI;

public class WebApp {
    public static void main(String[] args) {
        try {
            Desktop.getDesktop().browse(new URI("https://seybing-webapp.vercel.app"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
