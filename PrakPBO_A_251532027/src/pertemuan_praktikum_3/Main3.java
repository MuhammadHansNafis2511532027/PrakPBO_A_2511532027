package pertemuan_praktikum_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening3> daftarAkun = new ArrayList<>();
		Rekening3 akunAktif = null; // Objek belum diinisialisasi (null)
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama: ");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Keuangan");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi (Riwayat)");
			System.out.println("0. Keluar");
			System.out.println("Pilih menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); // Membersihkan buffer enter
			
			switch (pilihan) {
			case 1:
				System.out.print("Masukkan No Rekening: ");
				String no = input.nextLine();
				System.out.print("Masukkan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukkan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine();
				// Meminta PIN sebelum memuat objek rekening
				System.out.print("Masukkan PIN (6 digit): ");
				String pin = input.nextLine();
				
				// Instalasi Object / Menjalankan Constructor
				Rekening3 rekeningBaru = new Rekening3(no, nama, saldo, pin);
				daftarAkun.add(rekeningBaru);
				akunAktif = rekeningBaru;
				System.out.println("Rekening berhasil ditambahkan");
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
				 if (akunAktif == null) {
					 System.out.println("Error: Anda belum membuka rekening!");
				 } else {
					 // Otentikasi sebelum tarik tunai
					 System.out.print("Masukkan PIN : ");
					 String pinInput = input.nextLine();
					 
					 if (akunAktif.otentikasi(pinInput)) {
						 System.out.print("Masukkan nominal tarik: ");
						 double tarik = input.nextDouble();
						 akunAktif.tarikTunai(tarik);
					 } else {
						 System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					 }
				 }
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (daftarAkun.isEmpty()) {
					System.out.println("Error: Belum ada rekening yang tersedia!");
				} else {
					System.out.print("Masukkan No Rekening yang ingin dipilih: ");
					String nomorCari = input.nextLine();
					boolean ditemukan = false;
					for (Rekening3 rekening : daftarAkun) {
						if (rekening.getNomorRekening().equals(nomorCari)) {
							akunAktif = rekening;
							ditemukan = true;
							System.out.println("Berhasil berganti ke rekening " + rekening.getNamaPemilik());
							break;
						}
					}
					if (!ditemukan) {
						System.out.println("Rekening tidak ditemukan!");
					}
				}
				break;
				
			case 6:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					// Otentikasi sebelum mencetak mutasi
					System.out.print("Masukkan PIN : ");
					String pinInput = input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;
				
			case 0:
			isRunning = false;
			System.out.println("Sistem ditutup. Terima kasih!");
			break;
			
			default:
				System.out.println("Pilihan tidak valid!");
			}
		}
		input.close();
	}
}
