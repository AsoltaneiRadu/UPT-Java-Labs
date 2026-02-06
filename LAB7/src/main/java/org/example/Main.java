package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Definim numele fișierului și inițializăm ObjectMapper pentru JSON
       File file=new File("src/main/resources/carti.json");
        var objectMapper = new ObjectMapper();
        // Configurăm ObjectMapper pentru a scrie JSON formatat (pretty-print)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Inițializăm colecția Map folosind 'var' (inferența tipului)
        Map<String, Carti> colectieCarti;

        try {
            // Task 0: Citirea datelor din JSON și crearea colecției HashMap
            // Folosim TypeReference pentru a specifica tipul generic Map<String, Carte>
            colectieCarti = objectMapper.readValue(
                    new File(file),
                    new TypeReference<HashMap<String, Carti>>() {}
            );

            System.out.println("--- Colecția inițială (citită din JSON) ---");


            // 1) Afișarea colecției (folosind 'var' pentru entry)
            System.out.println("\n--- 1) Afișare Colecție Map ---");
            for (var entry : colectieCarti.entrySet()) {
                System.out.println("ID (Cheie): " + entry.getKey() + ", Carte (Valoare): " + entry.getValue());
            }

            // 2) Ștergerea unei cărți din colecție
            System.out.println("\n--- 2) Ștergere Carte (ID '3') ---");
            var idDeSters = "3";
            var carteStearsa = colectieCarti.remove(idDeSters);
            if (carteStearsa != null) {
                System.out.println("Cartea ștearsă: " + carteStearsa);
            } else {
                System.out.println("Cartea cu ID-ul " + idDeSters + " nu a fost găsită.");
            }

            // 3) Adăugarea unei cărți (folosind putIfAbsent)
            System.out.println("\n--- 3) Adăugare Carte (putIfAbsent) ---");
            var carteNoua = new Carti("Crimă și pedeapsă", "Feodor Dostoievski", 1866);
            var idNou = "6";

            // Încercare de adăugare ID nou (va funcționa)
            colectieCarti.putIfAbsent(idNou, carteNoua);
            System.out.println("Adăugat (ID '6'): " + carteNoua);

            // Încercare de adăugare ID existent (NU va funcționa, deoarece ID '4' există)
            var idExistent = "4";
            var altaCarte = new Carti("Alt titlu", "Alt autor", 2000);
            var rezultatAdaugare = colectieCarti.putIfAbsent(idExistent, altaCarte);
            if (rezultatAdaugare != null) {
                System.out.println("Cartea cu ID-ul '" + idExistent + "' există deja. Nu s-a adăugat.");
            }

            // 4) Salvarea modificărilor în fișierul JSON
            System.out.println("\n--- 4) Salvare Modificări în " + numeFisier + " ---");
            try {
                objectMapper.writeValue(new File(numeFisier), colectieCarti);
                System.out.println("Modificările au fost salvate cu succes.");
            } catch (IOException e) {
                System.err.println("Eroare la salvarea fișierului JSON: " + e.getMessage());
            }

            // 5) Creare Set cu cărțile autorului "Yuval Noah Harari" (Stream API și Colectori)
            System.out.println("\n--- 5) Creare Set (Autor: Yuval Noah Harari) ---");
            var autorCautat = "Yuval Noah Harari";

            var setHarari = colectieCarti.values() // Luăm doar valorile (obiectele Carte)
                    .stream() // Începem stream-ul
                    .filter(carte -> carte.autor().equalsIgnoreCase(autorCautat)) // Filtrăm după autor
                    .collect(Collectors.toSet()); // Colectăm rezultatele într-un Set

            // Afișare Set cu metoda forEach
            System.out.println("Cărțile lui " + autorCautat + " (" + setHarari.size() + " volume):");
            setHarari.forEach(carte -> System.out.println(" * " + carte));

            // 6) Afișare Set (Harari) ordonat după titlu (Stream, Lambda, Referințe la metode)
            System.out.println("\n--- 6) Afișare Set (Harari) Ordonat după Titlu ---");

            setHarari.stream()
                    // Sortare folosind Comparator.comparing și referință la metodă (Carte::titlu)
                    // (Expresia Lambda echivalentă ar fi: .sorted((c1, c2) -> c1.titlu().compareTo(c2.titlu())) )
                    .sorted(Comparator.comparing(Carti::titlu))
                    // Afișare folosind referință la metodă (System.out::println)
                    // (Expresia Lambda echivalentă ar fi: .forEach(carte -> System.out.println(carte)) )
                    .forEach(System.out::println);

            // 7) Afișarea celei mai vechi cărți din Set (Stream API și Optional)
            System.out.println("\n--- 7) Afișare Cea Mai Veche Carte (din Set Harari) ---");

            var ceaMaiVecheCarteOpt = setHarari.stream()
                    // Găsim minimul folosind 'an' (anul apariției)
                    // Folosim comparingInt și referință la metodă (Carte::an)
                    .min(Comparator.comparingInt(Carti::an));

            // Gestionăm rezultatul Optional
            ceaMaiVecheCarteOpt.ifPresentOrElse(
                    carte -> System.out.println("Cea mai veche carte este: " + carte),
                    () -> System.out.println("Setul este gol, nu s-a găsit nicio carte.")
            );


        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului JSON: " + e.getMessage());
        }
    }
}