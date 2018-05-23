import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.toIntExact;



public class BotApi20 extends TelegramLongPollingBot {

    public static HashMap<Long, Group> groups = new HashMap();
    public static HashMap<Long, Boolean> starting = new HashMap();

    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {


                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Welcome to the Foxhunt API, please enter you groupname: ");

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                starting.put(chat_id, true);
            }
            else if(update.hasMessage() && update.getMessage().hasText() && starting.get(chat_id) != null && starting.get(chat_id)) {
                // Set variables
                starting.put(chat_id, false);
                String group_name = update.getMessage().getText();
                Group new_group = new Group(group_name);
                String new_mes = ("You are registered with name "+ new_group.getName() + ". Please follow directions to the first question");
                chat_id = update.getMessage().getChatId();
                groups.put(chat_id, new_group);
                stuurBericht(chat_id, new_mes);

            } else if (update.getMessage().getText().equals("/name")) {
                Group group = (Group) groups.get(chat_id);
                String new_mes;
                if (group != null) {
                    new_mes = ("You are registered with name " + group.getName() + ". Please follow directions to the first question");
                } else {
                    new_mes = "Unkwown group!";
                }
                stuurBericht(chat_id, new_mes);
            }

        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        public void stuurBericht(long chatid, String bericht) {
            SendMessage message = new SendMessage()
                    .setChatId(chatid)
                    .setText(bericht);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getBotUsername() {
            // Return bot username
            // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
            return "API20Bot";
        }

        @Override
        public String getBotToken() {
            return "597729934:AAHoSmxY1uHby5Ngv1kNH0Gimyj03vIdQ80";
        }
    }


