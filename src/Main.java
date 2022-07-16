import Cat.ServiceFile;

public class Main {
    public static void main(String[] args) {
        ServiceFile serviceFile = new ServiceFile();
        ServiceFile.read();
        serviceFile.printCats();
        Game game = new Game();
        game.userInteraction();

    }
}
