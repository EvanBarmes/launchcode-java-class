public class CalculatorFactory {

    private CalculatorFactory () {}

    public CoinCalculator dollarCalculator(int[] amount) {
        return new DollarCalculator(amount);
    }

    public CoinCalculator euroCalculator(int[] amount) {
        return new EuroCalculator(amount);
    }

}
