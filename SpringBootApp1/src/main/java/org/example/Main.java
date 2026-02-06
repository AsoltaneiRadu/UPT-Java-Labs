package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<Autoturism> incarcareDate(String numeFisier) {
        List<Autoturism> lista = new ArrayList<>();
        try (var linii = Files.lines(Paths.get(numeFisier))) {
            lista = linii
                    .skip(1)
                    .map(linie ->
                    {
                        // Using split with -1 limit is safer to keep trailing empty columns
                        String[] date = linie.split(",", -1);
                        try {
                            String tip = date[0];
                            String firma = date[1];
                            Combustibil combustibil = Combustibil.valueOf(date[2].toUpperCase());
                            switch (tip) {
                                case "Masina":
                                    String model = date[3];
                                    double pret = Double.parseDouble(date[4]);
                                    return new Masina(model, pret, firma, combustibil);
                                case "Motocicleta":
                                    // Check if date is present before parsing
                                    if (date.length > 5 && !date[5].isEmpty()) {
                                        LocalDate anFabricatie = LocalDate.parse(date[5]);
                                        return new Motocicleta(firma, combustibil, anFabricatie);
                                    }
                                    return null; // Return null if date is missing for Motocicleta
                                default:
                                    System.err.println("Tip de autoturism necunoscut");
                                    return null;
                            }
                        } catch (Exception e) {
                            System.err.println("Eroare la parsare " + linie + " -> " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void main(String[] args) {
        String numeFisier = "auto.csv";
        List<Autoturism> lista = incarcareDate(numeFisier);
        lista.forEach(System.out::println);

        if (lista.isEmpty()) {
            System.out.println("Lista de autoturisme este goala. Nu se poate cauta.");
            return;
        }

        // Create one scanner and use it for all input. Do not close it inside the loop.
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nIntroduceti o firma de cautat:");
            // Declare and assign the variable inside the loop
            String firmaIntrodusa = scanner.nextLine();

            // Check if any car in the list matches the entered firm (case-insensitive)
            boolean firmaExista = lista.stream()
                    .anyMatch(autoturism -> autoturism.getFirma().equalsIgnoreCase(firmaIntrodusa));

            if (firmaExista) {
                System.out.println("Firma '" + firmaIntrodusa + "' a fost gasita in lista.");

                // Now, check if it matches the *first* element's firm
                if (lista.get(0).getFirma().equalsIgnoreCase(firmaIntrodusa)) {
                    System.out.println("Aceasta este si firma primului autoturism din lista.");
                } else {
                    System.out.println("Aceasta NU este firma primului autoturism din lista, care este '" + lista.get(0).getFirma() + "'.");
                }
                break; // Exit the loop since we found a match
            } else {
                System.out.println("Firma '" + firmaIntrodusa + "' nu a fost gasita. Va rugam sa incercati din nou.");
            }
        }

        // Close the scanner only when you are completely done with it.
        System.out.println("Introduceti combustibilul dorit:");
        String combustibilIntrodus = scanner.nextLine();
        lista.stream()
                .filter(autoturism -> autoturism.getCombustibil().toString().equalsIgnoreCase(combustibilIntrodus.toUpperCase()))
                        .forEach(System.out::println);

        scanner.close();
        Optional<Motocicleta> ceaMaiVeche=lista.stream()
                .filter(autoturism -> autoturism instanceof Motocicleta)
                .map(autoturism -> (Motocicleta) autoturism)
                .min(Comparator.comparing(Motocicleta::getAnFabricatie));
        if (ceaMaiVeche.isPresent()) {
            System.out.println("Motocicleta cea mai veche este: " + ceaMaiVeche.get());
        } else {
            System.out.println("Nu exista motocolete in lista.");
        }

    }
}
