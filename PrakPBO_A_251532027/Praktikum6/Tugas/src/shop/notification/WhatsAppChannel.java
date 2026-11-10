package shop.notification;

public class WhatsAppChannel extends AbstractChannel {
    @Override
    public String channelName(){
        return "WA";
    }

    @Override
    protected String resolveRecipient(CustomerContact contact){
        String p = contact.getPhone();
        if (p.startsWith("08") || p.startsWith("+62")){
            return p.length() >= 10 ? p : "";
        }
        return "";
    }

    @Override
    protected String prepare(String message){
        return "[Toko UMKM] " + message;
    }

    @Override
    protected void deliver(String recipient, String preparedMsg){
        System.out.println("[WA to " + recipient + "] " + preparedMsg);
    }
}
