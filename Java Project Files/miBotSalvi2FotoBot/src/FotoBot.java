import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FotoBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text); // Reply the same that you

            if (message_text.equals("/start")){
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        // Reply welcome message
                        .setText("Bienvenido a @FotoBot! Envíame una imagen y yo haré el resto!\uD83D\uDCAA");
            }else if (message_text.equals("/teclado")) {
                SendMessage message2 = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Aquí tienes tu teclado!");
                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();
                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();
                // Set each button, you can also use KeyboardButton objects if you need something else than text
                row.add("Row 1 Button 1");
                row.add("Row 1 Button 2");
                row.add("Row 1 Button 3");
                // Add the first row to the keyboard
                keyboard.add(row);
                // Create another keyboard row
                row = new KeyboardRow();
                // Set each button for the second line
                row.add("Row 2 Button 1");
                row.add("Row 2 Button 2");
                row.add("Row 2 Button 3");
                // Add the second row to the keyboard
                keyboard.add(row);
                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);
                // Add it to the message
                message2.setReplyMarkup(keyboardMarkup);

                try {
                    execute(message2); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            // Message contains photo // Si contiene foto

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            // Know photo width
            int f_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getWidth();
            // Know photo height
            int f_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getHeight();
            // Set photo caption
            String caption = "\uD83D\uDD34ID del archivo (file_id): " + f_id + "\n\uD83D\uDCD0Ancho (width): " + Integer.toString(f_width) + " píxeles\n\uD83D\uDCCFAlto (height): " + Integer.toString(f_height) + " píxeles";
            SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(f_id)
                    .setCaption(caption);
            try {
                execute(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (message_text.equals("/pic")) {
            // User sent /pic
            SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto("AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC")
                    .setCaption("Photo");
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            // Imagen incompatible

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText("Imagen incompatible, por favor prueba enviando otra diferente.");
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }







    }


    @Override
    public String getBotUsername() {
        // Se devuelve el nombre que dimos al bot al crearlo con el BotFather
        return "SalviBoT2 - FotoBot";
    }

    @Override
    public String getBotToken() {
        // Se devuelve el token que nos generó el BotFather de nuestro bot
        return "757049644:AAFBjdKu7D7pE_75KT3bMhOWCvOkjMtp6ZE";
    }
}
