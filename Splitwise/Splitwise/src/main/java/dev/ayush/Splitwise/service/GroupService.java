package dev.ayush.Splitwise.service;

import dev.ayush.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface GroupService {
    List<SettlementTransaction> settleUp(int groupId);

}
