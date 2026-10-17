package app;

import app.configuration.Config;
import app.ui.UserInterface;

public class Application {
    public static void main(String[] args) {
        UserInterface userInterface = Config.createUserInterface();
        userInterface.menuInicio();
    }
}
