package tour.rov.profile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.Message.MessageType;
import tour.rov.profile.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ProfileService profileService;

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

    public void inviteToJoinTeam(String team_name, String profile_game_name, String content) {
        Message message = new Message(MessageType.INVITE_TO_JOIN_TEAM);
        message.setSender(team_name);
        message.setContent(content);
        saveMessage(message);

        profileService.addMeaasge(profile_game_name, message.getId());

    }

    public void requestToJoinTeam(String team_name, String profile_game_name, String content) {
        Message message = new Message(MessageType.REQUEST_TO_JOIN_TEAM);
        message.setSender(profile_game_name);
        message.setContent(content);
        saveMessage(message);

        teamService.addMeaasge(team_name, message.getId());

    }

    public void inviteToScrims(String team_nameA, String team_nameB, String content) {
        Message message = new Message(MessageType.INVITE_TO_SCRIMS);
        message.setSender(team_nameB);
        message.setContent(content);
        saveMessage(message);

        teamService.addMeaasge(team_nameA, message.getId());

    }

    public void systemAlertToProfile(String profile_game_name, String content) {
        Message message = new Message(MessageType.SYSTEM_ALERT);
        message.setSender("System");
        message.setContent(content);
        saveMessage(message);

        profileService.addMeaasge(profile_game_name, message.getId());

    }

    public void systemAlertToTeam(String team_name, String content) {
        Message message = new Message(MessageType.SYSTEM_ALERT);
        message.setSender("System");
        message.setContent(content);
        saveMessage(message);

        teamService.addMeaasge(team_name, message.getId());

    }

}
