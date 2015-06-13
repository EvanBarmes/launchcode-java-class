public class CoinCalculator {

    protected int coins;

    public void print() {
        System.out.println(coins);
    }

    protected void calcCoins(int whole, int change, int[] centValues, int factorOut, int[] billValues) {
        int count = 0;
        whole = whole % factorOut;
        for (int i = 0; i < billValues.length; i++) {
            count += (int) whole / billValues[i];
            whole = whole % billValues[i];
        }
        for (int i = 0; i < centValues.length; i++) {
            count += (int) change / centValues[i];
            change = change % centValues[i];
        }
        coins = count;
    }

    public String getCoins() {
        return Integer.toString(coins);
    }

}
