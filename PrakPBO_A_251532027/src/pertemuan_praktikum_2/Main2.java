package pertemuan_praktikum_2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening2> daftarAkun = new ArrayList<>();
		Rekening2 akunAktif = null; // Objek belum diinisialisasi (null)
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama: ");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Keuangan");
			System.out.println("5. Ganti Akun");
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
				
				// Instalasi Object / Menjalankan Constructor
				Rekening2 rekeningBaru = new Rekening2(no, nama, saldo);
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
					 System.out.print("Masukkan nominal tarik: ");
					 double tarik = input.nextDouble();
					 akunAktif.tarikTunai(tarik);
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
					for (Rekening2 rekening : daftarAkun) {
						if (rekening.nomorRekening.equals(nomorCari)) {
							akunAktif = rekening;
							ditemukan = true;
							System.out.println("Berhasil berganti ke rekening " + rekening.namaPemilik);
							break;
						}
					}
					if (!ditemukan) {
						System.out.println("Rekening tidak ditemukan!");
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
