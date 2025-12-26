package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.config.TelegramBotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private static final String EMAIL_REGEX =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String PHONE_NUMBER_REGEX ="^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    private  UserServiceImpl userServiceImpl;
    private  TelegramBotConfig telegramBotConfig;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    public void setTelegramBotConfig(TelegramBotConfig telegramBotConfig) {
        this.telegramBotConfig = telegramBotConfig;
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getName();
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }


    //update.getMessage().getText()Message(messageId=25, messageThreadId=null,
    // from=User(id=1006549558, firstName=Денис, isBot=false, lastName=null, userName=warmdive, languageCode=ru,canJoinGroups=null, canReadAllGroupMessages=null, supportInlineQueries=null, isPremium=null, addedToAttachmentMenu=null), date=1766748473,
    // chat=Chat(id=1006549558, type=private, title=null, firstName=Денис, lastName=null, userName=warmdive, photo=null, description=null, inviteLink=null, pinnedMessage=null, stickerSetName=null, canSetStickerSet=null, permissions=null, slowModeDelay=null, bio=null, linkedChatId=null, location=null, messageAutoDeleteTime=null, hasPrivateForwards=null, HasProtectedContent=null, joinToSendMessages=null, joinByRequest=null, hasRestrictedVoiceAndVideoMessages=null, isForum=null, activeUsernames=null, emojiStatusCustomEmojiId=null, hasAggressiveAntiSpamEnabled=null, hasHiddenMembers=null, emojiStatusExpirationDate=null, availableReactions=null, accentColorId=null, backgroundCustomEmojiId=null, profileAccentColorId=null, profileBackgroundCustomEmojiId=null, hasVisibleHistory=null, unrestrictBoostCount=null, customEmojiStickerSetName=null), forwardFrom=null, forwardFromChat=null, forwardDate=null, text=О, entities=null, captionEntities=null, audio=null, document=null, photo=null, sticker=null, video=null, contact=null, location=null, venue=null, animation=null, pinnedMessage=null, newChatMembers=[], leftChatMember=null, newChatTitle=null, newChatPhoto=null, deleteChatPhoto=null, groupchatCreated=null, replyToMessage=null, voice=null, caption=null, superGroupCreated=null, channelChatCreated=null, migrateToChatId=null, migrateFromChatId=null, editDate=null, game=null, forwardFromMessageId=null, invoice=null, successfulPayment=null, videoNote=null, authorSignature=null, forwardSignature=null, mediaGroupId=null, connectedWebsite=null, passportData=null, forwardSenderName=null, poll=null, replyMarkup=null, dice=null, viaBot=null, senderChat=null, proximityAlertTriggered=null, messageAutoDeleteTimerChanged=null, isAutomaticForward=null, hasProtectedContent=null, webAppData=null, videoChatStarted=null, videoChatEnded=null, videoChatParticipantsInvited=null, videoChatScheduled=null, isTopicMessage=null, forumTopicCreated=null, forumTopicClosed=null, forumTopicReopened=null, forumTopicEdited=null, generalForumTopicHidden=null, generalForumTopicUnhidden=null, writeAccessAllowed=null, hasMediaSpoiler=null, userShared=null, chatShared=null, story=null, externalReplyInfo=null
    // , forwardOrigin=null, linkPreviewOptions=null, quote=null, usersShared=null, giveawayCreated=null, giveaway=null, giveawayWinners=null, giveawayCompleted=null, replyToStory=null, boostAdded=null, senderBoostCount=null)
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            StringBuilder functionalFeatures = new StringBuilder();
            SendMessage sendMessage = new SendMessage();

            functionalFeatures.append("Этот бот добавляет события в яндекс календарь \nДля прикрепления своего аккаунта в Яндекс\nПришли следующим сообщением\nEmail или номер телефона\nПароль от учетной записи");

            String userMessage = update.getMessage().getText();
            Message message = update.getMessage();

            String name = message.getFrom().getUserName();
            Long userId = message.getFrom().getId();

            if(userMessage.equalsIgnoreCase("Покажи что ты умеешь") || userMessage.equalsIgnoreCase("что ты умеешь")){
                try {
                    sendMessage.setChatId(update.getMessage().getChatId());
                    sendMessage.setText(functionalFeatures.toString());
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            //userServiceImpl.getOrCreateTelegramUser(userId,name,timezone);
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phone);
        return matcher.matches();
    }
}
