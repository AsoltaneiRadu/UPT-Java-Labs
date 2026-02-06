package org.example;

import org.example.exceptii.ExceptieAnExcursie;
import org.example.exceptii.ExceptieVarsta;

import java.sql.*;
import java.time.Year;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/lab8";
    static final String USER = "root"; 
    static final String PASS = "1234";

    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexiune la baza de date reusita!");

            int optiune;
            do {
                afisareMeniu();
                optiune = citesteInt("Introduceti optiunea: ");
                switch (optiune) {
                    case 1: adaugaPersoana(); break;
                    case 2: adaugaExcursie(); break;
                    case 3: afisarePersoaneSiExcursii(); break;
                    case 4: afisareExcursiiPersoana(); break;
                    case 5: afisarePersoaneByDestinatie(); break;
                    case 6: afisarePersoaneByAn(); break;
                    case 7: stergeExcursie(); break;
                    case 8: stergePersoana(); break;
                    case 0: System.out.println("Iesire..."); break;
                    default: System.out.println("Optiune invalida!");
                }
                System.out.println();
            } while (optiune != 0);

            conn.close();
        } catch (SQLException e) {
            System.err.println("Eroare la conectare: " + e.getMessage());
        }
    }

    private static void afisareMeniu() {
        System.out.println("--- MENIU ---");
        System.out.println("1. Adaugare persoana");
        System.out.println("2. Adaugare excursie");
        System.out.println("3. Afisare persoane si excursii");
        System.out.println("4. Cautare excursii dupa numele persoanei");
        System.out.println("5. Afisare persoane care au vizitat o destinatie");
        System.out.println("6. Afisare persoane care au facut excursii intr-un an");
        System.out.println("7. Stergere excursie");
        System.out.println("8. Stergere persoana");
        System.out.println("0. Iesire");
    }
    
    private static void adaugaPersoana() {
        System.out.print("Nume: ");
        String nume = scanner.next();

        try {
            System.out.print("Varsta: ");
            int varsta = citesteVarsta();

            String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nume);
            pstmt.setInt(2, varsta);
            pstmt.executeUpdate();
            System.out.println("Persoana adaugata cu succes.");
            pstmt.close();
        } catch (ExceptieVarsta e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Eroare SQL: " + e.getMessage());
        }
    }
    
    private static void adaugaExcursie() {
        try {
            System.out.print("Introduceti ID-ul persoanei: ");
            int idPersoana = citesteInt("ID: ");

            if (!persoanaExista(idPersoana)) {
                System.out.println("Persoana cu acest ID nu exista in baza de date."); 
                return;
            }
            
            int varstaPersoana = getVarstaById(idPersoana);

            System.out.print("Destinatia: ");
            String destinatia = scanner.next();

            System.out.print("Anul excursiei: ");
            int anul = citesteAnExcursie(varstaPersoana); 

            String sql = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPersoana);
            pstmt.setString(2, destinatia);
            pstmt.setInt(3, anul);
            pstmt.executeUpdate();
            System.out.println("Excursie adaugata.");
            pstmt.close();

        } catch (ExceptieAnExcursie e) {
            System.out.println("Eroare validare an: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Eroare SQL: " + e.getMessage());
        }
    }
    
    private static void afisarePersoaneSiExcursii() throws SQLException {
        String sql = "SELECT p.nume, p.varsta, e.destinatia, e.anul FROM persoane p " +
                "LEFT JOIN excursii e ON p.id = e.id_persoana";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.printf("%-20s %-10s %-20s %-10s%n", "Nume", "Varsta", "Destinatia", "Anul");
        System.out.println("-------------------------------------------------------------");
        while (rs.next()) {
            String dest = rs.getString("destinatia");
            System.out.printf("%-20s %-10d %-20s %-10s%n",
                    rs.getString("nume"),
                    rs.getInt("varsta"),
                    (dest == null ? "-" : dest),
                    (dest == null ? "-" : rs.getInt("anul")));
        }
        stmt.close();
    }
    
    private static void afisareExcursiiPersoana() throws SQLException {
        System.out.print("Introduceti numele persoanei: ");
        String nume = scanner.next();

        String sql = "SELECT p.nume, e.destinatia, e.anul FROM excursii e " +
                "JOIN persoane p ON p.id = e.id_persoana WHERE p.nume = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nume);
        ResultSet rs = pstmt.executeQuery();

        boolean found = false;
        while(rs.next()) {
            found = true;
            System.out.println("Destinatie: " + rs.getString("destinatia") + ", An: " + rs.getInt("anul"));
        }
        if(!found) System.out.println("Nu s-au gasit excursii pentru " + nume);
        pstmt.close();
    }
    
    private static void afisarePersoaneByDestinatie() throws SQLException {
        System.out.print("Destinatia cautata: ");
        String dest = scanner.next();

        String sql = "SELECT DISTINCT p.nume FROM persoane p " +
                "JOIN excursii e ON p.id = e.id_persoana WHERE e.destinatia = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dest);
        ResultSet rs = pstmt.executeQuery();

        System.out.println("Persoane care au vizitat " + dest + ":");
        while(rs.next()) {
            System.out.println("- " + rs.getString("nume"));
        }
        pstmt.close();
    }
    private static void afisarePersoaneByAn() throws SQLException {
        int an = citesteInt("Anul cautat: ");

        String sql = "SELECT DISTINCT p.nume FROM persoane p " +
                "JOIN excursii e ON p.id = e.id_persoana WHERE e.anul = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, an);
        ResultSet rs = pstmt.executeQuery();

        System.out.println("Persoane care au facut excursii in " + an + ":");
        while(rs.next()) {
            System.out.println("- " + rs.getString("nume"));
        }
        pstmt.close();
    }
    
    private static void stergeExcursie() throws SQLException {
        int idExcursie = citesteInt("Introduceti ID-ul excursiei de sters: ");

        String sql = "DELETE FROM excursii WHERE id_excursie = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idExcursie);
        int rows = pstmt.executeUpdate();

        if (rows > 0) System.out.println("Excursie stearsa.");
        else System.out.println("Excursia cu acest ID nu exista.");
        pstmt.close();
    }

    private static void stergePersoana() throws SQLException {
        int idPersoana = citesteInt("Introduceti ID-ul persoanei de sters: ");

        // Deoarece am setat ON DELETE CASCADE in MySQL, stergerea persoanei sterge automat excursiile
        String sql = "DELETE FROM persoane WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idPersoana);
        int rows = pstmt.executeUpdate();

        if (rows > 0) System.out.println("Persoana si excursiile aferente au fost sterse.");
        else System.out.println("Persoana cu acest ID nu exista.");
        pstmt.close();
    }

    private static boolean persoanaExista(int id) throws SQLException {
        String sql = "SELECT id FROM persoane WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    private static int getVarstaById(int id) throws SQLException {
        String sql = "SELECT varsta FROM persoane WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) return rs.getInt("varsta");
        return 0;
    }

    private static int citesteVarsta() throws ExceptieVarsta {
        int varsta = citesteInt(""); // Citeste un int sigur (fara litere)
        if (varsta < 0 || varsta > 120) {
            throw new ExceptieVarsta("Varsta invalida! Trebuie sa fie intre 0 si 120.");
        }
        return varsta;
    }

    private static int citesteAnExcursie(int varstaPersoana) throws ExceptieAnExcursie {
        int anExcursie = citesteInt("");
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varstaPersoana;

        if (anExcursie > anCurent) {
            throw new ExceptieAnExcursie("Anul excursiei nu poate fi in viitor!");
        }
        if (anExcursie < anNastere) {
            throw new ExceptieAnExcursie("Anul excursiei nu poate fi inainte de nasterea persoanei (" + anNastere + ")!");
        }
        return anExcursie;
    }
    
    private static int citesteInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Eroare: Va rugam introduceti un numar valid!"); // Tratare caractere
            scanner.next(); // consuma inputul gresit
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
}