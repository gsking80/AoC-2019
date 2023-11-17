package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day01Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day01/input.txt").getPath());
        final Day01 day01 = new Day01(fileReader);
        Assertions.assertThat(day01.getFuel()).isEqualTo(3412207);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day01/input.txt").getPath());
        final Day01 day01 = new Day01(fileReader);
        Assertions.assertThat(day01.getFuel2()).isEqualTo(5115436);
	}

	@Test
    public void testStuff() {

        Locale[] locales = NumberFormat.getAvailableLocales();
        double myNumber = -1234.56;
        NumberFormat form;
        for (int j = 0; j < 4; ++j) {
            System.out.println("FORMAT");
            for (int i = 0; i < locales.length; ++i) {
                if (locales[i].getCountry().length() == 0) {
                    continue; // Skip language-only locales
                }
                System.out.print(locales[i].getDisplayName());
                switch (j) {
                    case 0:
                        form = NumberFormat.getInstance(locales[i]); break;
                    case 1:
                        form = NumberFormat.getIntegerInstance(locales[i]); break;
                    case 2:
                        form = NumberFormat.getCurrencyInstance(locales[i]); break;
                    default:
                        form = NumberFormat.getPercentInstance(locales[i]); break;
                }
                if (form instanceof DecimalFormat) {
                    System.out.print(": " + ((DecimalFormat) form).toPattern());
                }
                System.out.print(" -> " + form.format(myNumber));
                try {
                    System.out.println(" -> " + form.parse(form.format(myNumber)));
                } catch (ParseException e) {}
            }
        }

//        for (Locale locale : DecimalFormat.getAvailableLocales()) {
//            DecimalFormat df;
//            System.out.println(df.set);
//        }

//	    BigDecimal blah = new BigDecimal("123.45-");
    }

    @Test
    public void testThis() {
	    Date thisdate = new Date(123434455);
	    System.out.println(thisdate);

	    System.out.println(thisdate.toInstant());
    }

}
