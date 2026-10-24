package model;

/** Rule 3: premium members borrow more books, for longer, and pay a lower fine. */
public class PremiumMember extends Member {

    public PremiumMember(String memberId, String name) { super(memberId, name); }

    @Override public String getType() { return "Premium"; }
    @Override public int getMaxBooks() { return 5; }
    @Override public int getLoanDays() { return 21; }
    @Override public double getFinePerDay() { return 1.0; }
}
