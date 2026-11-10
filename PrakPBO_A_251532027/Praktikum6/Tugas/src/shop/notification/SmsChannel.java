package shop.notification;

public class SmsChannel extends AbstractChannel {
    private final int maxLength;

    public SmsChannel(int maxLength){
        this.maxLength = maxLength;
    }

    @Override
    public String channelName(){
        return "SMS";
    }

    @Override
    protected String resolveRecipient(CustomerContact contact){
        return contact.getPhone().startsWith("08") ? contact.getPhone() : "";
    }

    @Override
    protected String prepare(String message){
        if (message.length() <= maxLength){
            return message;
        }
        return message.substring(0, maxLength - 3) + "...";
    }

    @Override
    protected void deliver(String recipient, String preparedMsg){
        System.out.println("[SMS to " + recipient + "] " + preparedMsg);
    }
}
