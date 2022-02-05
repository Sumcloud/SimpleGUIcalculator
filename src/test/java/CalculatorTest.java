import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    Calculator myCalculator;

    @Before
    public void setUp(){
        Calculator myCalculator = Calculator.getInstance();
        myCalculator.CreateAndShowCalculator();
    }

    @Test
    @DisplayName("Functionality test")
    public void calculateFunctionalityTest(){
        myCalculator.Calculate("12*(18+6)/5");
        assertEquals("57.6",Calculator.getInstance().getScreenText());
    }
    @Test
    @DisplayName("Exception thrown test")
    public void calculateExceptionTest(){
        myCalculator.Calculate("ab-ds");
        assertThrows(UnknownFunctionOrVariableException.class, ()-> toString().length());
    }
}
