class Expense extends Transaction {
    public Expense(int id, String category, double amount) {
        super(id, category, amount);
    }

    @Override
    public String toString() {
        return "Expense -> " + super.toString();
    }
}