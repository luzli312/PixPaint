package app;

//import view.LoginView;
import view.PixelArtView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        final JFrame application = new JFrame("Welcome to PixPaint!");
        application.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        application.add(new LoginView());
        application.add(new PixelArtView());
//        application.setSize(300, 200);
        application.setSize(800, 600);
        application.setVisible(true);
    }
}
