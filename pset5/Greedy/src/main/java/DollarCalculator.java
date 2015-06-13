
public class DollarCalculator extends CoinCalculator {

    public DollarCalculator (int[] amount) {
        calcCoins(amount[0], amount[1], new int[]{50, 25, 10, 5, 1}, 1, new int[]{});
    }

}
