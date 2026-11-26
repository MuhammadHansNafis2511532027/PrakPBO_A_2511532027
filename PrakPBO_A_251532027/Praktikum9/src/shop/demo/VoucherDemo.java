package shop.demo;

import shop.model.Voucher;

public class VoucherDemo {
    public static void main(String[] args){
        Voucher sah = new Voucher.Builder("HEMAT!", 10)
                .minimumPurchase(100_000)
                .description("Diskon 10%")
                .build();

        System.out.println("Voucher sah: " + sah.getCode() + " (" + sah.getPercent() + "%, min. " + sah.getMinimumPurchase() + ")");

        try{
            new Voucher.Builder("SALAH", 60).build();
            System.out.println("Voucher tidak sah: DITERIMA (seharusnya ditolak!)");
        }catch(IllegalStateException e){
            System.out.println("Voucher ditolak: " + e.getMessage());
        }
    }
}
