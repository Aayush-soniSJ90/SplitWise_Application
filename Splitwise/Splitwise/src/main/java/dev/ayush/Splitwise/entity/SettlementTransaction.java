package dev.ayush.Splitwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@Entity
public class SettlementTransaction extends BaseModel{
    @ManyToOne
    private User borrower;
    @ManyToOne
    private User lendor;
    private double amount;
    private Currency currency;

    public SettlementTransaction() {
    }

    public SettlementTransaction(User borrower, User lendor, double amount) {
        this.borrower = borrower;
        this.lendor = lendor;
        this.amount = amount;
    }
}
