package review.fixed;

import java.util.ArrayList;
import java.util.List;

public class BadShopRefactored {
    // Perbaikan 1
    interface Payable{
        boolean pay(long amount);
    }

    interface Invoiceable{
        String printInvoice();
    }

    static class GiftVoucher implements Payable, Invoiceable{
        private long balance = 100_000L; // Perbaikan 5
        public boolean pay(long amount){
            if (amount <= 0 || amount > balance){
                return false;
            }
            balance -= amount;
            return true;
        }

        public String printInvoice(){
            return "Voucher, sisa Rp" + balance;
        }
    }

    // Perbaikan 2
    abstract static class Courier{
        public final long cost(int kilograms){
            if (kilograms <= 0){
                return 0;
            }
            return kilograms * ratePerKg();
        }
        protected abstract long ratePerKg();
    }

    static class JneRegular extends Courier{
        @Override
        protected long ratePerKg(){
            return 10_000L;
        }
    }

    static class JneExpress extends Courier{
        @Override
        protected long ratePerKg(){
            return 18_000L;
        }
    }

    // Perbaikan 3
    static final class Address{
        private final String street;
        private final String city;

        Address(String street, String city){
            if (street == null || city == null){
                throw new IllegalArgumentException();
            }
            this.street = street;
            this.city = city;
        }
        public String toString(){
            return street + ", " + city;
        }
    }

    static class SimpleOrder{
        private final String id;
        private final Address address;

        SimpleOrder(String id, Address address){
            this.id = id;
            this.address = address;
        }

        Address getAddress(){
            return address;
        }

        public String toString(){
            return id + " -> " + address;
        }
    }

    // Perbaikan 6 dan 7
    static class Cart{
        static class Item{
            private final String name;
            private final long price;
            private final int quantity;

            Item(String name, long price, int quantity){
                if (price < 0 || quantity <= 0){
                    throw new IllegalArgumentException("Invalid item");
                }
                this.name = name;
                this.price = price;
                this.quantity = quantity;
            }

            long subtotal(){
                return price * quantity;
            }
        }

        private final List<Item> items = new ArrayList<>();
        void add(Item item){
            items.add(item);
        }
        long total(){
            long total = 0;
            for (Item item : items){
                total += item.subtotal();
            }
            return total;
        }
    }

    // Perbaikan 4
    interface Discount{
        long apply(long price);
    }

    static class MemberDiscount implements Discount{
        @Override
        public long apply(long price){
            return price - price * 5 / 100;
        }
    }

    static class FlashSaleDiscount implements Discount{
        @Override
        public long apply(long price){
            return price- price * 30 / 100;
        }
    }

    public static void main(String[] args){
        Cart cart = new Cart();
        cart.add(new Cart.Item("Kopi Gayo", 85_000, 2));
        System.out.println("Total cart: " + cart.total());

        Courier courier = new JneExpress();
        System.out.println("Ongkir Ekspres 2 kg: " + courier.cost(2));

        Address home = new Address("Jl. Sudirman 10", "Padang");
        SimpleOrder first = new SimpleOrder("ORD-1", home);
        SimpleOrder second = new SimpleOrder("ORD-2", home);
        System.out.println(first);
        System.out.println(second);

        Discount flashSale = new FlashSaleDiscount();
        System.out.println("Harga flash sale: " + flashSale.apply(100_000));

        GiftVoucher voucher = new GiftVoucher();
        System.out.println(voucher.pay(30_000) + " | " + voucher.printInvoice());
    }
}
