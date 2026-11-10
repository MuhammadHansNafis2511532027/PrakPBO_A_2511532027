package shop.notification;

public class EmailChannel extends AbstractChannel {
    @Override
    public String channelName(){
        return "EMAIL";
    }

    @Override
    protected String resolveRecipient(CustomerContact contact){
        return contact.getEmail().contains("@") ? contact.getEmail() : "";
    }

    @Override
    protected void deliver(String recipient, String preparedMsg){
        System.out.println("[EMAIL to " + recipient + "] " + preparedMsg);
    }
}
