package shop.app;

import shop.StudentInfo;
import shop.notification.*;

public class TugasApp {
    public static void main(String[] args){
        NotificationService service = new NotificationService();
        service.register(new EmailChannel());
        service.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        service.register(new WhatsAppChannel());

        NotificationChannel logChannel = new NotificationChannel(){
            @Override
            public String channelName(){
                return "LOG";
            }

            @Override
            public boolean send(CustomerContact contact, String message){
                if (message == null || message.isBlank()){
                    return false;
                }
                System.out.println("[LOG] " + contact.getName() + ": " + message);
                return true;
            }
        };
        service.register(logChannel);

        CustomerContact siti = new CustomerContact("Siti Rahma", "siti@example.com", "081234567890");
        CustomerContact budi = new CustomerContact("Budi", null, "+6281298765432");

        String pesan = "Pesanan ORD-0001 sudah dikirim dengan kurir Ekspres. " + "Nomor resi akan muncul di aplikasi dalam 1x24 jam. " + "Terima kasih telah berbelanja produk UMKM Sumatera Barat di toko kami!";
        System.out.println("\n== T2: Notifikasi status pesanan ==");
        int sentSiti = service.broadcast(siti, pesan);
        System.out.println("Terkirim ke Siti lewat " + sentSiti + " kanal\n");
        int sentBudi = service.broadcast(budi, pesan);
        System.out.println("Terkirim ke Budi lewat " + sentBudi + " kanal");
    }
}
