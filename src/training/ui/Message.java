package training.ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by karashevich on 01/09/15.
 */
public class Message {



    public enum MessageType {TEXT_REGULAR, TEXT_BOLD, SHORTCUT, CODE, LINK, CHECK}
    @NotNull
    private String messageText;
    @Nullable
    private Runnable runnable = null;

    @NotNull
    private MessageType messageType;
    public Message(@NotNull String messageText, @NotNull MessageType messageType) {
        this.messageText = messageText;
        this.messageType = messageType;
    }

    @Nullable
    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(@Nullable Runnable runnable) {
        this.runnable = runnable;
    }

    public String getText() {
        return messageText;
    }

    public MessageType getType() {
        return messageType;
    }

    public boolean isText() {
        return messageType == MessageType.TEXT_REGULAR || messageType == MessageType.TEXT_BOLD;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageText='" + messageText + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
