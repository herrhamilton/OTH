import java.util.Random;

public class Lotto {

    CircularList lottozahlen;
    Integer poolSize;
    Integer winnerSize;
    Integer[] gewinner;

    public Lotto(int poolSize, int winnerSize) {
        lottozahlen = new CircularList();
        this.poolSize = poolSize;
        this.winnerSize = winnerSize;
        for(int i=1; i<=poolSize; i++) {
            lottozahlen.append(i);
        }

    }

    public void getLottoZahlen() {
        gewinner = new Integer[winnerSize];
        Random rand = new Random();
        for(int i=0; i<winnerSize; i++) {
            gewinner[i]= rand.nextInt(poolSize-1);
            lottozahlen.delete(gewinner[i]);
            poolSize--;

        }
    }
}
