import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new FotoBot()); //Launch method and call an instance of FotoBot.java
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("FotoBot iniciado satisfactoriamente!!");
    }
}
