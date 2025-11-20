class Income extends Transaction {
    public Income(int id, String category, double amount) {
        super(id, category, amount);
    }

    @Override
    public String toString() {
        return "Income -> " + super.toString();
    }
}