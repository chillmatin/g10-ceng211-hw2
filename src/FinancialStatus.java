public class FinancialStatus {
    String id;
    int income;
    int savings;

    FinancialStatus(String id, int income, int savings) {
        setId(id);
        setIncome(income);
        setSavings(savings);
    }

    FinancialStatus(FinancialStatus financialStatus){
        setSavings(financialStatus.getSavings());
        setIncome(financialStatus.getIncome());
        setId(financialStatus.getId());
    }

    FinancialStatus(ApplicationInfo applicationInfo){
        String incomeString = applicationInfo.getApplicationInfo()[2];
        String savingsString = applicationInfo.getApplicationInfo()[3];

        setId(applicationInfo.getId());
        setIncome(Integer.parseInt(incomeString));
        setSavings(Integer.parseInt(savingsString));

    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    private void setIncome(int income) {
        this.income = income;
    }

    public int getSavings() {
        return savings;
    }

    private void setSavings(int savings) {
        this.savings = savings;
    }

    @Override
    public Object clone(){
        return new FinancialStatus(this);
    }

    @Override
    public String toString() {
        return "FinancialStatus{" +
                "id='" + id + '\'' +
                ", income=" + income +
                ", savings=" + savings +
                '}';
    }
}
