package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    /**
     * Scrie o listă de obiecte Angajati într-un fișier JSON.
     * @param lista Lista de angajați de scris în fișier.
     */
    public static void scriere(List<Angajati> lista ) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // Suport pentru LocalDate
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Scrie datele ca string-uri
            File file=new File("src/main/resources/angajati.json");
            mapper.writeValue(file,lista);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Citește o listă de obiecte Angajati dintr-un fișier JSON.
     * @return Lista de angajați citită din fișier.
     */
    public static List<Angajati> citire()
    {
        try {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // Suport pentru LocalDate
            // Citirea listei de angajați din fișierul JSON
            List<Angajati> persoane = mapper
                    .readValue(file, new TypeReference<List<Angajati>>(){});
            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Afișează angajații care îndeplinesc o anumită condiție.
     * @param lista Lista de angajați.
     * @param cond Condiția de filtrare.
     */
    public static void afisare(List<Angajati> lista, Predicate<Angajati> cond)
    {
        lista.stream()
                .filter(cond)
                .forEach(System.out::println);
    }

    /**
     * Afișează angajații care îndeplinesc o anumită condiție, cu un mesaj introductiv.
     * @param lista Lista de angajați.
     * @param cond Condiția de filtrare.
     */
    public static void afisareluna(List<Angajati> lista, Predicate<Angajati> cond)
    {
        List<Angajati> angajatidupa=lista.stream()
                .filter(cond)
                .collect(Collectors.toList());
        System.out.println("Angajati dupa luna aprilie anu trecut");
        System.out.println(angajatidupa);
    }

    /**
     * Afișează salariile angajaților care îndeplinesc o anumită condiție, sortate descrescător.
     * @param lista Lista de angajați.
     * @param cond Condiția de filtrare.
     */
    public static void afisareP(List<Angajati> lista, Predicate<Angajati> cond)
    {
        lista.stream()
            .filter(cond)
                .sorted(Comparator.comparing(Angajati::getSalariul).reversed()) // Sortează descrescător după salariu
                .map(Angajati::getSalariul) // Extrage doar salariul
                .forEach(System.out::println);
    }

    public static void main(String[] args)
    {
        // Data de referință pentru angajări
        LocalDate dataang=LocalDate.of(2024,4,1);

        // Citirea listei de angajați din fișier
       List<Angajati> angajati=citire();
       System.out.println(angajati);
       for(Angajati a:angajati)
           System.out.println(a);

        // Scrierea listei de angajați înapoi în fișier (demonstrație)
        scriere(angajati);

        // Afișarea angajaților cu salariul peste 2500
        System.out.println("Angajati peste 2500");
        Predicate<Angajati> salariupeste=a->a.getSalariul()>2500;
        afisare(angajati,salariupeste);

        // Definirea predicatelor pentru filtrarea angajaților
        Predicate<Angajati> data=a->a.getData_angajarii().isAfter(dataang); // Angajați după data de referință
        Predicate<Angajati> posts=a->a.getPostul().toLowerCase().contains("sef"); // Postul conține "sef"
        Predicate<Angajati> postd=a->a.getPostul().toLowerCase().contains("director"); // Postul conține "director"
        Predicate<Angajati>postc=posts.or(postd); // Postul este de conducere
        Predicate<Angajati> prdF=postc.and(data); // Post de conducere și angajat după data de referință
        Predicate<Angajati> prdF2 = postc.negate(); // Nu este post de conducere

        // Afișarea angajaților cu post de conducere angajați după data de referință
        afisareluna(angajati,prdF);

        // Afișarea salariilor angajaților fără post de conducere, în ordine descrescătoare
        System.out.println("Angajatii fara post de conducere in ordine descrescatoare");
        afisareP(angajati,prdF2);

        // Extragerea și afișarea numelor angajaților cu majuscule
        System.out.println("\nNumele angajatilor cu majuscule:");
        List<String> numeMajuscule = angajati.stream()
                .map(angajat -> angajat.getNumele().toUpperCase()) // Transformă numele în majuscule
                .collect(Collectors.toList());
        System.out.println(numeMajuscule);

        // Găsirea și afișarea primului angajat din companie (cel mai vechi)
        System.out.println("\nPrimul angajat din companie (cel mai vechi):");
        angajati.stream()
                .min(Comparator.comparing(Angajati::getData_angajarii)) // Găsește angajatul cu data minimă de angajare
                .ifPresent(System.out::println);

        // Calcularea și afișarea statisticilor referitoare la salarii
        System.out.println("\nStatistici salarii:");
        DoubleSummaryStatistics stats = angajati.stream()
                .collect(Collectors.summarizingDouble(Angajati::getSalariul));
        System.out.println("Salariu mediu: " + stats.getAverage());
        System.out.println("Salariu maxim: " + stats.getMax());
        System.out.println("Salariu minim: " + stats.getMin());

        // Verificarea existenței unui angajat cu numele "Ion"
        System.out.println("\nVerificare angajat Ion:");
        angajati.stream()
                .filter(a -> a.getNumele().contains("Ion"))
                .findAny()
                .ifPresentOrElse(
                        (a) -> System.out.println("Firma are cel putin un Ion angajat"),
                        () -> System.out.println("Firma nu are nici un Ion angajat")
                );

        // Numărarea persoanelor angajate în vara anului precedent
        System.out.println("\nNumarul de persoane angajate in vara anului precedent:");
        int anulPrecedent = LocalDate.now().getYear() - 1;
        LocalDate startVara = LocalDate.of(anulPrecedent, Month.JUNE, 1);
        LocalDate stopVara = LocalDate.of(anulPrecedent, Month.AUGUST, 31);
        long nrAngajatiVara = angajati.stream()
                .filter(a -> a.getData_angajarii().isAfter(startVara.minusDays(1)) && a.getData_angajarii().isBefore(stopVara.plusDays(1)))
                .count();
        System.out.println(nrAngajatiVara);
    }
}
