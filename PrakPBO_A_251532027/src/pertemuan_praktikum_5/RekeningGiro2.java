package pertemuan_praktikum_5;

public class RekeningGiro2 extends Rekening5 {
	private double batasOverdraft;
	
	public RekeningGiro2(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		// Memanggil inisialisasi dasar dari Superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	// Implementasi Polimorfisme (Overriding)
	@Override
	public boolean tarikTunai(double nominal) {
		if (nominal <= 0) {
			System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0!");
			return false;
		}
		// Logika Khusus Giro: Boleh ditarik asalkan nominal tidak melebihi (saldo + batasOverdraft)
		else if (nominal > (saldo + batasOverdraft)) {
			System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarikan: Rp" + (saldo + batasOverdraft));
			return false;
		}
		else {
			saldo -= nominal; // Saldo bisa bernilai negatif di sini (memakan overdraft)
			String idTrx = "TRX-G-" + System.currentTimeMillis();
			Transaksi4 trxBaru = new Transaksi4(idTrx, "Debit Giro", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Tarik tunai Giro RP" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
			return true;
		}
	}
	
	// Getter khusus Giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
}