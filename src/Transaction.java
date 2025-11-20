class Transaction {
    protected int id;
    protected String category;
    protected double amount;

    public Transaction(int id, String category, double amount) {
        this.id = id;
        this.category = category;
        this.amount = amount;
    }

    public String toString() {
        return String.format("ID: %d, Category: %s, Amount: %.2f", id, category, amount);
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
