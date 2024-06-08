public class Klicker {
    public static final int MAX_LEVEL = 3;
    private int counter;
    private int licht;
    private int upgrade;
    private int reifen;
    private int motor;
    private int turbo;
    private int karosserie;

    public Klicker() {
        resetValues();
    }

    // Getter-Methoden
    public int getCounter() { return counter; }
    public int getLicht() { return licht; }
    public int getReifen() { return reifen; }
    public int getMotor() { return motor; }
    public int getTurbo() { return turbo; }
    public int getKarosserie() { return karosserie; }
    public int getUpgrade() { return upgrade; }

    // Zähler erhöhen
    public void incrementCounter() { counter += upgrade; }

    // Zähler um bestimmten Betrag erhöhen
    public void incrementCounterBy(int amount) { counter += amount; }

    // Upgrade-Methoden
    public boolean upgradeLicht() { return upgradeComponent("licht", 1, 4, 8); }
    public boolean upgradeReifen() { return upgradeComponent("reifen", 3, 6, 1); }
    public boolean upgradeMotor() { return upgradeComponent("motor", 5, 1, 2); }
    public boolean upgradeTurbo() { return upgradeComponent("turbo", 7, 1, 2); }
    public boolean upgradeKarosserie() { return upgradeComponent("karosserie", 1, 2, 4); }

    private boolean upgradeComponent(String component, int... costs) {
        int level = 0;
        switch (component) {
            case "licht": level = licht; break;
            case "reifen": level = reifen; break;
            case "motor": level = motor; break;
            case "turbo": level = turbo; break;
            case "karosserie": level = karosserie; break;
        }

        if (level < costs.length && counter >= costs[level]) {
            counter -= costs[level];
            switch (component) {
                case "licht": licht++; break;
                case "reifen": reifen++; break;
                case "motor": motor++; break;
                case "turbo": turbo++; break;
                case "karosserie": karosserie++; break;
            }
            upgrade++;
            return true;
        }
        return false;
    }

    // Methode zum Zurücksetzen der Werte
    public void resetValues() {
        this.counter = 0;
        this.licht = 0;
        this.reifen = 0;
        this.motor = 0;
        this.turbo = 0;
        this.karosserie = 0;
        this.upgrade = 1;
    }

    // Überprüft, ob alle Upgrades auf Maximallevel sind
    public boolean areAllUpgradesMaxed() {
        return licht >= MAX_LEVEL && reifen >= MAX_LEVEL && motor >= MAX_LEVEL &&
               turbo >= MAX_LEVEL && karosserie >= MAX_LEVEL;
    }
}
