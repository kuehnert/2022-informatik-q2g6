package trees;

import java.io.*;

public class TiereRatenSerialisieren extends TiereRaten {
    private static final String filename = "data/tierebaum.ser";

    public TiereRatenSerialisieren() {
        try {
            laden();
            System.out.println("Datei erfolgreich eingelesen.");
        } catch (RuntimeException e) {
            wurzel = new Frage("Ist es ein Saeugetier");
            wurzel.ja = new Frage("Loewe");
            wurzel.nein = new Frage("Papagei");
            System.out.println("WARNUNG: Datei konnte nicht gelesen werden, " +
                    "starte mit Initialbaum.");

        }
    }

    private void speichern() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(wurzel);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Baum gespeichert.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void laden() {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(fileInputStream);
            wurzel = (Frage) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Baum erfolgreich eingelesen.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void spiele() {
        super.spiele();
        speichern();
    }
}
