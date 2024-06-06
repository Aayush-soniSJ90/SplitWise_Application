package dev.ayush.Splitwise.service.strategy_settleUpStrategy;

import dev.ayush.Splitwise.entity.Expense;
import dev.ayush.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {
    List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses);
}
