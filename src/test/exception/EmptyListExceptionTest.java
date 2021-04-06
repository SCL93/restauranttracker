package exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyListExceptionTest {

    @Test
    void constructorTest(){
        EmptyListException test1 = new EmptyListException();
        assertTrue(test1 != null);
    }

}