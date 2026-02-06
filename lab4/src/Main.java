import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void scrieBin(Object o, String fis) {
        try {
            FileOutputStream f = new FileOutputStream(fis);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object citesteBin(String fis) {
        try {
            FileInputStream f = new FileInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(f);
            Object o = ois.readObject();
            ois.close();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            // Erori ignorate sau tratate aici
            System.out.println("Eroare la citirea binara: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        List<Echipament> echipamente = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("echipamente.txt"))) {
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] campuri = linie.split(";");

                String denumire = campuri[0];
                int nr_inv = Integer.parseInt(campuri[1].trim());
                double pret = Double.parseDouble(campuri[2].trim());
                String zona = campuri[3].trim();
                StareEchipament stare = StareEchipament.valueOf(campuri[4].trim().toUpperCase());

                String tip = campuri[5].trim().toLowerCase();

                switch (tip) {
                    case "imprimanta":
                        int ppm = Integer.parseInt(campuri[6].trim());
                        String rezolutie = campuri[7].trim();
                        int p_car = Integer.parseInt(campuri[8].trim());
                        ModTiparire modt = ModTiparire.valueOf(campuri[9].trim().toUpperCase());

                        echipamente.add(new Imprimanta(denumire, nr_inv, pret, zona, stare, ppm, rezolutie, p_car, modt));
                        break;

                    case "copiator":
                        int p_ton = Integer.parseInt(campuri[6].trim());
                        FormatCopiere format = FormatCopiere.valueOf(campuri[7].trim().toUpperCase());

                        echipamente.add(new Copiator(denumire, nr_inv, pret, zona, stare, p_ton, format));
                        break;

                    case "sistem de calcul":
                        String mon = campuri[6].trim();
                        double vit = Double.parseDouble(campuri[7].trim());
                        int hdd = Integer.parseInt(campuri[8].trim());
                        SistemOperare os = SistemOperare.valueOf(campuri[9].trim().toUpperCase());

                        echipamente.add(new SistemCalcul(denumire, nr_inv, pret, zona, stare, mon, vit, hdd, os));
                        break;

                    default:
                        System.out.println("Tip echipament necunoscut: " + tip);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul 'electronice.txt' nu a fost gasit in radacina proiectului.");
        } catch (Exception e) {
            System.out.println("Eroare la parsare: " + e.getMessage());
            e.printStackTrace();
        }
        Scanner userScanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MENIU ---");
            System.out.println("1. Afisarea tuturor echipamentelor");
            System.out.println("2. Afisarea imprimantelor");
            System.out.println("3. Afisarea copiatoarelor");
            System.out.println("4. Afisarea sistemelor de calcul");
            System.out.println("5. Modificarea starii unui echipament");
            System.out.println("6. Setarea modului de scriere (Imprimanta)");
            System.out.println("7. Setarea formatului de copiere (Copiator)");
            System.out.println("8. Instalarea unui sistem de operare (Sistem Calcul)");
            System.out.println("9. Afisarea echipamentelor vandute");
            System.out.println("10. Serializare date in 'echip.bin'");
            System.out.println("11. Deserializare date din 'echip.bin'");
            System.out.println("0. Iesire");
            System.out.print("Optiunea dvs: ");

            int opt = userScanner.nextInt();

            switch (opt) {
                case 0:
                    System.exit(0);
                    break;
                case 1: // [cite: 16]
                    for (Echipament e : echipamente) System.out.println(e);
                    break;
                case 2: // [cite: 17]
                    for (Echipament e : echipamente)
                        if (e instanceof Imprimanta) System.out.println(e);
                    break;
                case 3: // [cite: 18]
                    for (Echipament e : echipamente)
                        if (e instanceof Copiator) System.out.println(e);
                    break;
                case 4:
                    for (Echipament e : echipamente)
                        if (e instanceof SistemCalcul) System.out.println(e);
                    break;
                case 5: // [cite: 20]
                    System.out.print("Introduceti Nr Inv: ");
                    int id5 = userScanner.nextInt();
                    System.out.print("Noua stare (ACHIZITIONAT/EXPUS/VANDUT): ");
                    String stNoua = userScanner.next();
                    for (Echipament e : echipamente) {
                        if (e.getNr_inv() == id5) {
                            e.setStare(StareEchipament.valueOf(stNoua.toUpperCase()));
                            System.out.println("Stare actualizata.");
                        }
                    }
                    break;
                case 6: // [cite: 21]
                    System.out.print("Nr Inv Imprimanta: ");
                    int id6 = userScanner.nextInt();
                    System.out.print("Mod nou (COLOR/ALB_NEGRU): ");
                    String mod6 = userScanner.next();
                    for (Echipament e : echipamente) {
                        if (e.getNr_inv() == id6 && e instanceof Imprimanta) {
                            ((Imprimanta) e).setMod_tiparire(ModTiparire.valueOf(mod6.toUpperCase()));
                            System.out.println("Mod actualizat.");
                        }
                    }
                    break;
                case 7: // [cite: 22]
                    System.out.print("Nr Inv Copiator: ");
                    int id7 = userScanner.nextInt();
                    System.out.print("Format nou (A3/A4): ");
                    String format7 = userScanner.next();
                    for (Echipament e : echipamente) {
                        if (e.getNr_inv() == id7 && e instanceof Copiator) {
                            ((Copiator) e).setFormat_copiere(FormatCopiere.valueOf(format7.toUpperCase()));
                            System.out.println("Format actualizat.");
                        }
                    }
                    break;
                case 8: // [cite: 23]
                    System.out.print("Nr Inv Sistem Calcul: ");
                    int id8 = userScanner.nextInt();
                    System.out.print("OS nou (WINDOWS/LINUX): ");
                    String os8 = userScanner.next();
                    for (Echipament e : echipamente) {
                        if (e.getNr_inv() == id8 && e instanceof SistemCalcul) {
                            ((SistemCalcul) e).setSistem_operare(SistemOperare.valueOf(os8.toUpperCase()));
                            System.out.println("Sistem de operare instalat.");
                        }
                    }
                    break;
                case 9: // [cite: 24]
                    for (Echipament e : echipamente) {
                        if (e.getStare() == StareEchipament.VANDUT) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 10: // Serializare [cite: 25]
                    scrieBin(echipamente, "echip.bin");
                    System.out.println("Date serializate in echip.bin");
                    break;
                case 11: // Deserializare [cite: 25]
                    List<Echipament> listaDes = (List<Echipament>) citesteBin("echip.bin");
                    if (listaDes != null) {
                        echipamente = listaDes;
                        System.out.println("Date incarcate din echip.bin");
                    }
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}
