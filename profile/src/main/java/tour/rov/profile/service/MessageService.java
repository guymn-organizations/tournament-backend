package tour.rov.profile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Message;
import tour.rov.profile.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllById(List<String> id) {
        List<Message> messages = messageRepository.findAllById(id);
        return deleteMessagesIfSendDateMoreThanOneYearAgo(messages);
    }

    public List<Message> deleteMessagesIfSendDateMoreThanOneYearAgo(List<Message> messages) {
        List<Message> messagesToRemove = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearAgo = currentDate.minusYears(1);

        for (Message message : messages) {
            if (message.getSendDate().isBefore(oneYearAgo)) {
                messagesToRemove.add(message);
            }
        }

        messages.removeAll(messagesToRemove);
        return messages;
    }

}
