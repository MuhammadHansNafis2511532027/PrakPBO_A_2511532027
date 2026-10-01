package pertemuan_praktikum_4;

import java.util.ArrayList;

public class Rekening4 {
	// Bagian atas dari class Rekening.java
	private String nomorRekening;
	private String namaPemilik;
	private String pin; // Data sensitif!
	
	// Gunakan protected agar Subclass bisa mengaksesnya langsung
	protected double saldo;
	protected ArrayList<Transaksi3> riwayatTransaksi;
	
	// Isi konstruktor dan method lainnya TETAP SAMA seperti Modul 3
	
	// 2. Modifikasi Constructor untuk menerima PIN awal
	public Rekening4(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		// ValidasiPIN di dalam Constructor
		if (pinAwal.length() == 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin = "123456";
		}

		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
	}
	
	// 3. Getter untuk atribut yang diizinkan dibaca publik
	public String getNomorRekening() { return nomorRekening; }
	public String getNamaPemilik() { return namaPemilik; }
	
	// 4. Method Otentikasi Internal (Validasi Enkapsulasi)
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	
	// ... (method setorTunai, tarikTunai, cekInformasi, cetakMutasi tetap dipertahankan seperti Modul 2)
	
	public void setorTunai(double nominal) {
		if (nominal >= 10000) {
			saldo += nominal;
			// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi3 trxBaru = new Transaksi3(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo awal saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 10000!");
		}
	}
	
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal : Minimal nominal penarikan 10.000");
			} else if (nominal > saldo) {
				System.out.println("Transaksi Gagal : Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
			} else {
				saldo -= nominal;
				String idTrx = "TRX-T-" + System.currentTimeMillis();
				Transaksi3 trxBaru = new Transaksi3(idTrx, "Debit", nominal);
				riwayatTransaksi.add(trxBaru);
				System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
			}
	}
	
	public void cetakMutasi() {
		System.out.println("--- MUTASI REKENING ---");
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
		} else {
			for (Transaksi3 trx : riwayatTransaksi) {
				trx.cetakDetail();
			}
		}
		System.out.println("-----------------------");
	}
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir : Rp" + saldo);
		System.out.println("-------------------");
	}
}
