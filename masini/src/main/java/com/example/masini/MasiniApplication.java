package com.example.masini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.masini.models.Masina;
import com.example.masini.repository.MasinaJpaRepository;

import java.util.Scanner;

@SpringBootApplication
public class MasiniApplication {
	private static final Logger logger = LoggerFactory.getLogger(MasiniApplication.class);
	@Autowired
	private MasinaJpaRepository masinaJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(MasiniApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Scanner scanner=new Scanner(System.in);

		System.out.println("Alege implementarea:");
		System.out.println("1. Jakarta Persistence API (EntityManager)");
		System.out.println("2. Spring Data JPA");
		int optiune = scanner.nextInt();
		scanner.nextLine(); // consuma newline

		if (optiune == 1) {
			demoCerința1(scanner);
		}
	}
	private void demoCerința1(Scanner scanner) {
		System.out.println("\n--- DEMO JPA STANDARD (EntityManager) ---");

		// a. Adaugare
		Masina mNoua = new Masina("VS99TEST", "Volvo", 2023, "Alb", 10000);
		masinaJpaRepository.adaugaMasina(mNoua);
		logger.info("S-a adaugat masina: {}", mNoua);

		// b. Stergere
		System.out.println("Stergem masina TM10ABC...");
		masinaJpaRepository.stergeMasina("TM10ABC");

		// c. Cautare
		Masina gasita = masinaJpaRepository.cautaDupaNrInmatriculare("B99WWW");
		System.out.println("Masina cautata: " + gasita);

		// d. Lista toate
		System.out.println("Toate masinile:");
		masinaJpaRepository.getToateMasinile().forEach(System.out::println);

		// e. Numar masini marca (input tastatura)
		System.out.print("Introdu marca pentru numaratoare: ");
		String marca = scanner.nextLine();
		long countMarca = masinaJpaRepository.getMarca(marca);
		System.out.println("Masini marca " + marca + ": " + countMarca);

		// f. Sub 100k km
		System.out.println("Masini sub 100k km: " + masinaJpaRepository.getNrMasini());

		// g. Mai noi de 5 ani
		System.out.println("Masini mai noi de 5 ani:");
		masinaJpaRepository.getMasiniNoi().forEach(System.out::println);
	}
	}
