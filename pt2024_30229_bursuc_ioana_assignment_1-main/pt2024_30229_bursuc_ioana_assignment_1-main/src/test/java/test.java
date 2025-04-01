import model.Operatii;
import model.Polinom;
import org.junit.Test;

import static org.junit.Assert.*;

public class test {
    @Test
    public void testAdunare() {
        Polinom poly1 = new Polinom().Parse("2x^2+3x+1");
        Polinom poly2 = new Polinom().Parse("x^3-5x+2");
        String expected = "x^3 + 2.0x^2 - 2.0x + 3.0";
        String result = Operatii.adunare(poly1, poly2);
        assertEquals(expected, result);
    }

    @Test
    public void testScadere() {
        Polinom poly1 = new Polinom().Parse("4x^3+2x^2-x");
        Polinom poly2 = new Polinom().Parse("3x^3-5x^2+2");
        String expected = "x^3 + 7.0x^2 - x - 2.0";
        Polinom result = Operatii.scadere(poly1, poly2);
        String resultS=result.toString();
        assertEquals(expected, resultS);
    }

    @Test
    public void testInmultire() {
        Polinom poly1 = new Polinom().Parse("2x^2+3x+1");
        Polinom poly2 = new Polinom().Parse("x^3-5x+2");
        String expected = "2.0x^5 + 3.0x^4 - 10.0x^3 + 4.0x^2 + 6.0x + 2.0";
        Polinom result = Operatii.inmultire(poly1, poly2);
        String resultS=result.toString();
        assertEquals(expected, resultS);
    }

//    @Test
//    public void testImpartire() {
//        Polinom poly1 = new Polinom().Parse("x^2+2x+1");
//        Polinom poly2 = new Polinom().Parse("x+1");
//        String expected = "x + 1";
//        String result = Operatii.impartire(poly1, poly2);
//        assertEquals(expected, result);
//    }

    @Test
    public void testDerivare() {
        Polinom poly = new Polinom().Parse("3x^2+2x+1");
        String expected = "6.0x + 2.0";
        String result = Operatii.derivare(poly);
        assertEquals(expected, result);
    }

    @Test
    public void testIntegrala() {
        Polinom poly = new Polinom().Parse("3x^2+2x+1");
        String expected = "x^3 + x^2 + x";
        String result = Operatii.integrala(poly);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void main() {
    }
}