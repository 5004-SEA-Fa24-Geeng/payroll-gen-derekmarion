package student;

public abstract class CSVStringFormatter {
    /**
     * Formats a double value to a string, truncating trailing zeroes in the
     * hundreds place.
     * 
     * @param value the double value to format
     * @return the formatted string
     */
    protected String formatDouble(double value) {
        String formattedValue = String.format("%.2f", value);
        if (formattedValue.endsWith("0")) {
            formattedValue = formattedValue.substring(0, formattedValue.length() - 1);
        }
        return formattedValue;
    }
}
