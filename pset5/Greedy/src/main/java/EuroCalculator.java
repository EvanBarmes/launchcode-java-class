public class EuroCalculator extends CoinCalculator {

    public EuroCalculator (int[] amount) {
        calcCoins(amount[0], amount[1], new int[]{50, 20, 10, 5, 2, 1}, 5, new int[]{2,1});
    }

}
