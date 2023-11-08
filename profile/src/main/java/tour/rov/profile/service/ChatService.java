package tour.rov.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Chat;
import tour.rov.profile.repository.ChatRepo;

@Service
public class ChatService {
    @Autowired
    private ChatRepo chatRopo;

    public void addChatContent(Chat chat) {
        this.chatRopo.save(chat);
    }
}
