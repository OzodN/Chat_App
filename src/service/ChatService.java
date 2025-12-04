package service;

import model.Chat;
import repository.ChatRepository;
import repository.FileChatRepository;

import java.time.LocalTime;
import java.util.List;

public class ChatService {
    private static final String CHAT_FILE = "src/data/chatMessages.txt";
    private final ChatRepository chatRepo = new FileChatRepository(CHAT_FILE);

    public ChatService() {
        chatRepo.findAll();
    }

    public void send(String sender, String text) {
        Chat m = new Chat(sender, text, LocalTime.now());
        chatRepo.save(m);
    }

    public List<Chat> getAll() {
        return chatRepo.findAll();
    }
}
