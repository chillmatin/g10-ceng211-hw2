public class Date {

    private final int[] FOURTH_YEAR_MONTH_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int[] NORMAL_YEAR_MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int day;
    private int month;
    private int year;

    private int[] daysOfMonths = new int[12];

    private String[] stringArrayDate = new String[3];
    private int[] intArrayDate = new int[3];

    private int daysSince2000;

    private String stringDate;

    /**
     * This Constructor gets the string input
     * in the yyyy-mm-dd format and turns it into date object.
     * Example parameter: 2025-09-30
     *
     */
    Date(String stringDate) {


        setStringArrayDate(stringDate);

        setDay(stringArrayDate[2]);
        setMonth(stringArrayDate[1]);
        setYear(stringArrayDate[0]);

        setDaysOfMonths(year);

        setIntArrayDate(day, month, year);

        setDaysSince2000(intArrayDate);
        setStringDate(stringDate);

    }

    /**
     * This constructor creates a deep copy of a date object
     */
    Date(Date date) {

        setStringArrayDate(date.getStringArrayDate());

        setDay(date.getDay());
        setMonth(date.getMonth());
        setYear(date.getYear());

        setDaysOfMonths(date.getDaysOfMonths());


        setIntArrayDate(date.getIntArrayDate());

        setDaysSince2000(date.getDaysSince2000());
        setStringDate(date.toString());

    }

    /**
     * Gives difference between this date and comparisonDate in days
     * if this date precedes comparisonDate, resulting day number will be negative
     * if this date proceeds comparisonDate, resulting day number will be positive
     * in case firstDate = secondDate, resulting day number will be zero
     * This method only works for dates between 2000 and 2099 (inclusively)
     */
    public int compareTo(Date comparisonDate) {
        return this.getDaysSince2000() - comparisonDate.getDaysSince2000();
    }

    public int[] getDaysOfMonths() {
        int[] copyDaysOfMonths = new int[12];

        System.arraycopy(daysOfMonths, 0, copyDaysOfMonths, 0, daysOfMonths.length);
        return copyDaysOfMonths;
    }

    private void setDaysOfMonths(int[] daysOfMonths) {
        this.daysOfMonths = daysOfMonths;
    }

    /**
     * This Method sets daysOfMonths variable according to the year variable
     * if year is divisible by four, then FOURTH_YEAR_MONTH_DAYS will be assigned to daysOfMonths variable
     * if year is not divisible by four, then NORMAL_YEAR_MONTH_DAYS will be assigned to daysOfMonths variable
     */
    private void setDaysOfMonths(int year) {

        if (year / 4 == 0) {
            this.daysOfMonths = FOURTH_YEAR_MONTH_DAYS;
        } else {
            this.daysOfMonths = NORMAL_YEAR_MONTH_DAYS;
        }
    }

    public int getDay() {
        return day;
    }

    private void setDay(int day) {
        this.day = day;
    }

    private void setDay(String day) {
        this.day = Integer.parseInt(day);
    }

    public int getMonth() {
        return month;
    }

    private void setMonth(int month) {
        this.month = month;
    }

    private void setMonth(String month) {
        this.month = Integer.parseInt(month);
        }


    public int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    private void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    public String[] getStringArrayDate() {
        String[] copyStringArrayDate = new String[3];

        System.arraycopy(stringArrayDate, 0, copyStringArrayDate, 0, stringArrayDate.length);
        return copyStringArrayDate;
    }

    private void setStringArrayDate(String[] stringArrayDate) {
        this.stringArrayDate = stringArrayDate;
    }

    private void setStringArrayDate(String stringDate) {
        stringArrayDate = stringDate.split("-");
    }

    public int[] getIntArrayDate() {
        int[] copyIntArrayDate = new int[3];

        System.arraycopy(intArrayDate, 0, copyIntArrayDate, 0, intArrayDate.length);
        return copyIntArrayDate;
    }

    private void setIntArrayDate(int[] intArrayDate) {
        this.intArrayDate = intArrayDate;
    }

    private void setIntArrayDate(int day, int month, int year) {
        intArrayDate = new int[]{day, month, year};
    }

    public int getDaysSince2000() {
        return daysSince2000;
    }

    private void setDaysSince2000(int days) {
        this.daysSince2000 = days;
    }

    private void setDaysSince2000(int[] intArrayDate) {
        int day = intArrayDate[0];
        int month = intArrayDate[1];
        int year = intArrayDate[2];
        int yearsSince2000 = year - 2000;
        int daysSince2000 = 0;

        boolean isFourthYear = year % 4 == 0;
        int fourthYearsSince2000 = (yearsSince2000 - 1) / 4 + 1;
        int normalYearsSince2000 = yearsSince2000 - fourthYearsSince2000;   // a year that's 365 days long

        if (year != 2000) {
            daysSince2000 = fourthYearsSince2000 * 366 + normalYearsSince2000 * 365; // days accumulated over past years
        }

        int[] monthDaysArray = NORMAL_YEAR_MONTH_DAYS;

        if (isFourthYear) {
            monthDaysArray = FOURTH_YEAR_MONTH_DAYS;
        }
        for (int i = 0; i < month - 1; i++) {
            daysSince2000 += monthDaysArray[i];     /* days accumulated over past months since
             * beginning of the year */
        }
        daysSince2000 += (day);                        // days accumulated over given month

        this.daysSince2000 = daysSince2000;

    }


    private void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    @Override
    public String toString() {
        return stringDate;
    }
}
