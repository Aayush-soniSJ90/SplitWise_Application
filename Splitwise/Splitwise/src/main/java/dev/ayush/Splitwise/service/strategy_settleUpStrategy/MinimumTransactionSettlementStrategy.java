package dev.ayush.Splitwise.service.strategy_settleUpStrategy;

import dev.ayush.Splitwise.dto.UserAmount;
import dev.ayush.Splitwise.entity.*;

import java.util.*;

public class MinimumTransactionSettlementStrategy implements SettleUpStrategy{
    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        HashMap<User, Double> map =  getOutstandingBalances(expenses);
        Comparator<UserAmount> minHeapComparator = Comparator.comparingDouble(UserAmount::getAmount);
        Comparator<UserAmount> maxHeapComparator = Comparator.comparingDouble(UserAmount::getAmount).reversed();

        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(maxHeapComparator);
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(minHeapComparator);

        for (Map.Entry<User, Double> entry : map.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }else if (entry.getValue() > 0){
                maxHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }else{
                System.out.println("No Transactions Needed.");
            }
        }

        List<SettlementTransaction> settlementTransactions = new ArrayList<>();
        while(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            UserAmount borrower =  minHeap.poll();
            UserAmount lender = maxHeap.poll();

            if(Math.abs(borrower.getAmount()) > lender.getAmount()){
                borrower.setAmount(borrower.getAmount() + lender.getAmount());
                minHeap.add(borrower);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                settlementTransactions.add(settlementTransaction);
            } else if (Math.abs(borrower.getAmount()) < lender.getAmount()) {
                lender.setAmount(lender.getAmount() + borrower.getAmount());
                maxHeap.add(lender);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), Math.abs(borrower.getAmount()));
                settlementTransactions.add(settlementTransaction);
            }else {
                System.out.println("Do nothing, both are equal.");
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                settlementTransactions.add(settlementTransaction);

            }
        }
        return settlementTransactions;
    }

    public HashMap<User, Double> getOutstandingBalances(List<Expense> expenses){
        HashMap<User, Double> expenseMap = new HashMap<>();
        for (Expense expense: expenses){
            for(UserExpense userExpense: expense.getUserExpenses()){
                User participant = userExpense.getUser();
                double amount = userExpense.getAmount();
                if(expenseMap.containsKey(participant)){
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        expenseMap.put(participant, expenseMap.get(participant)+ amount);
                    }else{
                        expenseMap.put(participant, expenseMap.get(participant) - amount);
                    }
                }else {
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        expenseMap.put(participant, 0 + amount);
                    }else{
                        expenseMap.put(participant, 0 - amount);
                    }
                }
            }
        }
        return expenseMap;
    }
}
