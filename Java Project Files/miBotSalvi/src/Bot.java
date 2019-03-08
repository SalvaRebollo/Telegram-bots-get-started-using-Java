import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        // Esta función se invocará cuando nuestro bot reciba un mensaje

        // Se obtiene el mensaje escrito por el usuario
        final String mensajeRecibido = update.getMessage().getText();
        final String nombre = update.getMessage().getFrom().getFirstName();
        String apellidos = update.getMessage().getFrom().getLastName();

        if (apellidos == null){
            apellidos = "";
        }

        // Se obtiene el id de chat del usuario // Obtaining chat id
        final long chatId = update.getMessage().getChatId();

        // Se crea un objeto mensaje // Creating message object
        SendMessage message = new SendMessage().setText("");

        // Debuging or test
     /*   message.setChatId(chatId).setText(nombre + ": " + mensajeRecibido);
        System.out.println(nombre + ": " + mensajeRecibido);*/
        //System.out.println(apellidos + ": " + mensajeRecibido);

        message.setChatId(chatId).setText(nombre + ": " + mensajeRecibido);

        //Commands logic
        if (mensajeRecibido.equals("/minombre")) {
            System.out.println(nombre + ": " + mensajeRecibido);
            message.setChatId(chatId).setText("Tu nombre es " + nombre);

        } else if (mensajeRecibido.equals("/miusuario")) {
            System.out.println("Tu usuario es @" + update.getMessage().getFrom().getUserName());
            message.setChatId(chatId).setText("Tu usuario es @" + update.getMessage().getFrom().getUserName());

        } else if (mensajeRecibido.equals("/misapellidos")) {

            if (apellidos.equals("")) {
                System.out.println("No tienes un apellido registrado en el perfil ;)");
                message.setChatId(chatId).setText("No tienes un apellido registrado en el perfil ;)");
            } else {
                System.out.println("Tus apellidos son " + apellidos);
                message.setChatId(chatId).setText("Tus apellidos son " + apellidos);
            }

        } else if (mensajeRecibido.equals("/minombrecompleto")) {

            if (apellidos.equals("")) {
                System.out.println("Tu nombre es " + nombre + "\npero no tienes un apellido registrado en el perfil ;)");
                message.setChatId(chatId).setText("Tu nombre es " + nombre + "\npero no tienes un apellido registrado en el perfil ;)");
            } else {
                System.out.println("Tu nombre completo es " + nombre + " " + apellidos);
                message.setChatId(chatId).setText("Tu nombre completo es " + nombre + " " + apellidos);
            }

        }


        try {
            // Se envía el mensaje
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Se devuelve el nombre que dimos al bot al crearlo con el BotFather
        return "SalviBoT";
    }

    @Override
    public String getBotToken() {
        // Se devuelve el token que nos generó el BotFather de nuestro bot
        return "591801316:AAGBJvGkOiUYooBIA9-u7Y5mJgPauMd0_iE";
    }
}
