import org.junit.Test;

        import static org.junit.Assert.assertEquals;

public class CoinCalculatorTest {

    @Test
    public void USTest() {
        CoinCalculator coinCalculator = new DollarCalculator(new int[]{3, 45});

        String coinMessage = coinCalculator.getCoins();

        assertEquals("3", coinMessage);
    }

    @Test
    public void EUTest() {
        CoinCalculator coinCalculator = new EuroCalculator(new int[]{4, 21});

        String coinMessage = coinCalculator.getCoins();

        assertEquals("4", coinMessage);
    }


}
