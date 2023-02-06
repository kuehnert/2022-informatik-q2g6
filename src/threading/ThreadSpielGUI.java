package threading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ThreadSpielGUI extends JFrame {
    private static final String FONT_NAME = "Arial";
    private static final char LEERZEICHEN = '\00';
    private static final Color ACTIVE = Color.BLACK;
    private static final Color INACTIVE = Color.GRAY;
    private static final Random generator = new Random();
    private final JLabel llBuchstabe;
    private final JLabel llPunkte;
    private final JLabel lBuchstabe;
    private final JLabel lPunkte;
    private final JButton bStartStop;
    private SpielThread spielThread = null;
    private char zeichen = LEERZEICHEN;
    private int timer = 0;
    private int punkte = -1;

    public ThreadSpielGUI() {
        super("ThreadSpiel mit GUI");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font llFont = new Font(FONT_NAME, Font.PLAIN, 32);
        Font lFont = new Font(FONT_NAME, Font.BOLD, 128);

        llBuchstabe = new JLabel("Buchstabe");
        llBuchstabe.setFont(llFont);
        llPunkte = new JLabel("Punkte");
        llPunkte.setFont(llFont);

        lBuchstabe = new JLabel("");
        lBuchstabe.setFont(lFont);
        lBuchstabe.setHorizontalAlignment(SwingConstants.CENTER);

        lPunkte = new JLabel("0");
        lPunkte.setFont(lFont);
        lPunkte.setHorizontalAlignment(SwingConstants.CENTER);

        bStartStop = new JButton("Start");
        bStartStop.setFont(llFont);
        bStartStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonGedrueckt();
            }
        });
        getContentPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                tasteGedrueckt(e);
            }
        });

        JPanel pLabels = new JPanel(new GridLayout(1, 2));
        pLabels.add(llBuchstabe);
        pLabels.add(llPunkte);

        JPanel pContent = new JPanel(new GridLayout(1, 2));
        pContent.add(lBuchstabe);
        pContent.add(lPunkte);

        setLayout(new GridLayout(3, 1));
        add(pLabels);
        add(pContent);
        add(bStartStop);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ThreadSpielGUI();
    }

    public void buttonGedrueckt() {
        if (bStartStop.isEnabled()) {
            if (spielThread != null) {
                spielThread.stop();
            }

            bStartStop.setEnabled(false);
            spielThread = new SpielThread();
            getContentPane().requestFocus();
            spielThread.start();
        }
    }

    public void tasteGedrueckt(KeyEvent e) {
        if (timer < 1) {
            return;
        }

        char getippt = e.getKeyChar();
        if (getippt == zeichen) {
            punkte++;
            lPunkte.setText("" + punkte);
            zeichen = zufallsZeichen();
            lBuchstabe.setText("" + zeichen);
        }
    }

    private char zufallsZeichen() {
        return (char) ('a' + generator.nextInt(26));
    }

    class SpielThread extends Thread {
        @Override
        public void run() {
            timer = 10;
            punkte = 0;
            zeichen = zufallsZeichen();
            lBuchstabe.setText("" + zeichen);

            while (timer > 0) {
                try {
                    sleep(1_000);
                } catch (InterruptedException e) {
                }

                timer--;
                bStartStop.setText("" + timer);
            }

            bStartStop.setText("Start");
            bStartStop.setEnabled(true);
        }
    }
}
