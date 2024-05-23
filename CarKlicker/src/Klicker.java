public class Klicker {
    private int counter;
    private int licht;
    private int upgrade;
    private int reifen;
    private int motor;
    private int turbo;
    private int karosserie;

    public Klicker() {
        this.counter = 0;
        this.licht = 0;
        this.upgrade = 1;
        this.reifen = 0;
        this.motor = 0;
        this.turbo = 0;
        this.karosserie = 0;
    }

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

    public void incrementCounter() {
        switch (upgrade) {
            case 1: counter++; break;
            case 2: counter += 2; break;
            case 3: counter += 3; break;
            case 4: counter += 4; break;
            case 5: counter += 5; break;
            case 6: counter += 6; break;
            case 7: counter += 7; break;
            case 8: counter += 8; break;
            case 9: counter += 9; break;
            case 10: counter += 10; break;
            case 11: counter += 11; break;
            case 12: counter += 12; break;
            case 13: counter += 13; break;
            case 14: counter += 14; break;
            case 15: counter += 15; break;
        }
    }

    public boolean upgradeLicht() {
        int cost;
        switch (licht) {
            case 0: cost = 10; break;
            case 1: cost = 40; break;
            case 2: cost = 80; break;
            default: return false; // Max 
        }

        if (counter >= cost) {
            counter -= cost;
            licht++;
            upgrade++;
            return true;
        }
        return false;
    }

    public boolean upgradeReifen() {
        int cost;
        switch (reifen) {
            case 0: cost = 300; break;
            case 1: cost = 600; break;
            case 2: cost = 1200; break;
            default: return false; // Max 
        }

        if (counter >= cost) {
            counter -= cost;
            reifen++;
            upgrade++;
            return true;
        }
        return false;
    }

    public boolean upgradeMotor() {
        int cost;
        switch (motor) {
            case 0: cost = 500; break;
            case 1: cost = 1000; break;
            case 2: cost = 2000; break;
            default: return false; // Max 
        }

        if (counter >= cost) {
            counter -= cost;
            motor++;
            upgrade++;
            return true;
        }
        return false;
    }

    public boolean upgradeTurbo() {
        int cost;
        switch (turbo) {
            case 0: cost = 700; break;
            case 1: cost = 1400; break;
            case 2: cost = 2800; break;
            default: return false; // Max 
        }

        if (counter >= cost) {
            counter -= cost;
            turbo++;
            upgrade++;
            return true;
        }
        return false;
    }

    public boolean upgradeKarosserie() {
        int cost;
        switch (karosserie) {
            case 0: cost = 1000; break;
            case 1: cost = 2000; break;
            case 2: cost = 4000; break;
            default: return false; // Max 
        }

        if (counter >= cost) {
            counter -= cost;
            karosserie++;
            upgrade++;
            return true;
        }
        return false;
    }
}