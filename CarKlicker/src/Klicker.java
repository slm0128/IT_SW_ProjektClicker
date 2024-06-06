/**
 * Die Klicker-Klasse verwaltet den Zähler und die Upgrades für das Spiel.
 */
public class Klicker {
    private int counter;  // Zähler für Schrauben und Mutter
    private int licht;    // Level des Licht-Upgrades
    private int upgrade;  // Allgemeiner Upgrade-Level
    private int reifen;   // Level des Reifen-Upgrades
    private int motor;    // Level des Motor-Upgrades
    private int turbo;    // Level des Turbo-Upgrades
    private int karosserie;  // Level des Karosserie-Upgrades

    public Klicker() {
        this.counter = 0;
        this.licht = 0;
        this.upgrade = 1;
        this.reifen = 0;
        this.motor = 0;
        this.turbo = 0;
        this.karosserie = 0;
    }

    // Getter-Methoden für die verschiedenen Felder
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

    // Erhöht den Zähler basierend auf dem aktuellen Upgrade-Level
    public void incrementCounter() {
        counter += upgrade;
    }

    // Erhöht den Zähler um einen bestimmten Betrag
    public void incrementCounterBy(int amount) {
        counter += amount;
    }

    // Methode zum Upgrade der Licht-Komponente
    public boolean upgradeLicht() {
        return upgradeComponent("licht", 10, 40, 80);
    }

    // Methode zum Upgrade der Reifen-Komponente
    public boolean upgradeReifen() {
        return upgradeComponent("reifen", 300, 600, 1200);
    }

    // Methode zum Upgrade der Motor-Komponente
    public boolean upgradeMotor() {
        return upgradeComponent("motor", 500, 1000, 2000);
    }

    // Methode zum Upgrade der Turbo-Komponente
    public boolean upgradeTurbo() {
        return upgradeComponent("turbo", 700, 1400, 2800);
    }

    // Methode zum Upgrade der Karosserie-Komponente
    public boolean upgradeKarosserie() {
        return upgradeComponent("karosserie", 1000, 2000, 4000);
    }

    // Allgemeine Methode zum Upgrade von Komponenten, um Code-Duplikate zu vermeiden
    private boolean upgradeComponent(String component, int... costs) {
        int level = 0;
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

        // Überprüft, ob das Upgrade möglich ist
        if (level < costs.length && counter >= costs[level]) {
            counter -= costs[level];
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
            upgrade++;
            return true;
        }
        return false;
    }
}
