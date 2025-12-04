package repository;

import model.Chat;

import java.util.List;

public interface ChatRepository {
    List<Chat> findAll();
    void save(Chat user);
}
