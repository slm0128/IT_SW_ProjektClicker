public class Klicker {
    public static final int MAX_LEVEL = 3; // Maximales Level für Upgrades
    private int counter; // Zähler für Schrauben und Mutter
    private int licht; // Level des Licht-Upgrades
    private int upgrade; // Gesamtzahl der Upgrades
    private int reifen; // Level des Reifen-Upgrades
    private int motor; // Level des Motor-Upgrades
    private int turbo; // Level des Turbo-Upgrades
    private int karosserie; // Level des Karosserie-Upgrades
    private GUI gui; // Referenz auf das GUI-Objekt

    // Konstruktor für Klicker
    public Klicker(GUI gui) {
        this.gui = gui;
        resetValues(); // Setzt die Werte zurück
    }

    // Methode zum Aktualisieren der GUI
    public void update() {
        gui.updateCounterLabel();
    }

    // Getter-Methoden für die verschiedenen Variablen
    public int getCounter() {
        return counter;
    }

    public int getLicht() {
        return licht;
    }

    public int getReifen() {
        return reifen;
    }

    public int getMotor() {
        return motor;
    }

    public int getTurbo() {
        return turbo;
    }

    public int getKarosserie() {
        return karosserie;
    }

    public int getUpgrade() {
        return upgrade;
    }

    // Methode zum Erhöhen des Zählers
    public void incrementCounter() {
        counter += upgrade; // Erhöht den Zähler basierend auf der Anzahl der Upgrades
    }

    // Methode zum Erhöhen des Zählers um einen bestimmten Betrag
    public void incrementCounterBy(int amount) {
        counter += amount; // Erhöht den Zähler um den angegebenen Betrag
    }

    // Upgrade-Methoden für die verschiedenen Komponenten
    public boolean upgradeLicht() {
        return upgradeComponent("licht", 10, 40, 80); // Kosten für die Licht-Upgrades
    }

    public boolean upgradeReifen() {
        return upgradeComponent("reifen", 30, 60, 100); // Kosten für die Reifen-Upgrades
    }

    public boolean upgradeMotor() {
        return upgradeComponent("motor", 500, 600, 650); // Kosten für die Motor-Upgrades
    }

    public boolean upgradeTurbo() {
        return upgradeComponent("turbo", 700, 720, 750); // Kosten für die Turbo-Upgrades
    }

    public boolean upgradeKarosserie() {
        return upgradeComponent("karosserie", 760, 780, 800); // Kosten für die Karosserie-Upgrades
    }

    // Methode zum Upgraden einer Komponente
    private boolean upgradeComponent(String component, int... costs) {
        int level = 0; // Aktuelles Level der Komponente
        switch (component) {
            case "licht":
                level = licht;
                break;
            case "reifen":
                level = reifen;
                break;
            case "motor":
                level = motor;
                break;
            case "turbo":
                level = turbo;
                break;
            case "karosserie":
                level = karosserie;
                break;
        }

        // Überprüft, ob genug Schrauben und Mutter für das Upgrade vorhanden sind
        if (level < costs.length && counter >= costs[level]) {
            counter -= costs[level]; // Reduziert den Zähler um die Kosten des Upgrades
            switch (component) {
                case "licht":
                    licht++;
                    break;
                case "reifen":
                    reifen++;
                    break;
                case "motor":
                    motor++;
                    break;
                case "turbo":
                    turbo++;
                    break;
                case "karosserie":
                    karosserie++;
                    break;
            }
            upgrade++; // Erhöht die Gesamtzahl der Upgrades
            return true; // Gibt true zurück, wenn das Upgrade erfolgreich war
        }
        return false; // Gibt false zurück, wenn nicht genug Schrauben und Mutter vorhanden sind
    }

    // Methode zum Zurücksetzen der Werte
    public void resetValues() {
        this.counter = 0; // Setzt den Zähler zurück
        this.licht = 0; // Setzt das Licht-Upgrade-Level zurück
        this.reifen = 0; // Setzt das Reifen-Upgrade-Level zurück
        this.motor = 0; // Setzt das Motor-Upgrade-Level zurück
        this.turbo = 0; // Setzt das Turbo-Upgrade-Level zurück
        this.karosserie = 0; // Setzt das Karosserie-Upgrade-Level zurück
        this.upgrade = 1; // Setzt die Gesamtzahl der Upgrades auf 1
    }

    // Methode zum Überprüfen, ob alle Upgrades auf Maximallevel sind
    public boolean areAllUpgradesMaxed() {
        return licht >= MAX_LEVEL && reifen >= MAX_LEVEL && motor >= MAX_LEVEL &&
                turbo >= MAX_LEVEL && karosserie >= MAX_LEVEL; // Überprüft, ob alle Upgrades das maximale Level erreicht haben
    }
}
