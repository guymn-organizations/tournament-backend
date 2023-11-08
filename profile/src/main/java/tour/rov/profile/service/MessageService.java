package tour.rov.profile.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tour.rov.profile.model.Message;
import tour.rov.profile.model.Scrims;
import tour.rov.profile.model.Message.MessageType;
import tour.rov.profile.model.PositionType;
import tour.rov.profile.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ScrimsService scrimsService;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public Message findById(String id) {
        return messageRepository.findById(id).get();
    }

    public List<Message> findAllById(List<String> id) {
        List<Message> messages = messageRepository.findAllByIdIn(id);
        return deleteMessagesIfSendDateMoreThanOneYearAgo(messages);
    }

    public List<Message> deleteMessagesIfSendDateMoreThanOneYearAgo(List<Message> messages) {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        List<Message> filteredMessages = messages.stream()
                .filter(message -> !message.getSendDate().isBefore(oneYearAgo))
                .collect(Collectors.toList());

        Collections.reverse(filteredMessages);
        return filteredMessages;
    }

    public void inviteToJoinTeam(String team_name, String profile_game_name, PositionType positionType) {
        Message message = new Message(MessageType.INVITE_TO_JOIN_TEAM);
        message.setSender(team_name);
        message.setPositionType(positionType);
        message.setContent(team_name + " has invited you to join the team in position " + positionType);
        saveMessage(message);

        profileService.addMeaasge(profile_game_name, message.getId());

    }

    public void requestToJoinTeam(String team_name, String profile_game_name, PositionType positionType) {
        Message message = new Message(MessageType.REQUEST_TO_JOIN_TEAM);
        message.setSender(profile_game_name);
        message.setPositionType(positionType);
        message.setContent(profile_game_name + " want to join your team in position " + positionType);
        saveMessage(message);

        teamService.addMeaasge(team_name, message.getId());

    }

    public void inviteToScrims(String team_nameB, String team_nameA, String scrims_id) {
        Message message = new Message(MessageType.REQUEST_TO_SCRIMS);
        message.setSender(team_nameB);
        Scrims scrims = scrimsService.findScrimsById(scrims_id);
        message.setScrimsId(scrims_id);
        message.setContent(scrimsService.formatLocalDateTime(scrims.getStartDate()));
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
