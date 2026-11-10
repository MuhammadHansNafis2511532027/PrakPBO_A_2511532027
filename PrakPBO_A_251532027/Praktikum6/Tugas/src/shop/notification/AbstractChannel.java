package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private int sentCount = 0;
    private String lastDelivered = "";

    protected abstract String resolveRecipient(CustomerContact contact);
    protected abstract void deliver(String recipient, String preparedMessage);
    protected String prepare(String message){
        return message;
    }

    @Override
    public final boolean send(CustomerContact contact, String message){
        if (contact == null || message == null || message.isBlank()){
            return false;
        }
        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isBlank()){
            return false;
        }
        String preparedMsg = prepare(message);
        deliver(recipient, preparedMsg);
        sentCount++;
        lastDelivered = preparedMsg;
        return true;
    }

    public int getSentCount(){
        return sentCount;
    }

    public String getLastDelivered(){
        return lastDelivered;
    }
}
