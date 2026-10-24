package model;

import java.util.ArrayList;
import java.util.List;

/** Regular member. PremiumMember overrides the borrowing rules (polymorphism). */
public class Member {
    private final String memberId;
    private final String name;
    private final List<BorrowTransaction> activeBorrows = new ArrayList<>();

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<BorrowTransaction> getActiveBorrows() { return activeBorrows; }

    public String getType() { return "Regular"; }
    public int getMaxBooks() { return 2; }
    public int getLoanDays() { return 14; }
    public double getFinePerDay() { return 2.0; }
}
