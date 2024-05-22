public class Klicker {
    private int counter;
    private int licht;
    private int upgrade;

    public Klicker() {
        this.counter = 0;
        this.licht = 0;
        this.upgrade = 1;
    }

    public int getCounter() {
        return counter;
    }

    public int getLicht() {
        return licht;
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
        }
    }

    public boolean upgradeLicht() {
        int cost;
        switch (licht) {
            case 0: cost = 10; break;
            case 1: cost = 40; break;
            case 2: cost = 80; break;
            default: return false; // Max level reached
        }

        if (counter >= cost) {
            counter -= cost;
            licht++;
            upgrade++;
            return true;
        }
        return false;
    }
}
