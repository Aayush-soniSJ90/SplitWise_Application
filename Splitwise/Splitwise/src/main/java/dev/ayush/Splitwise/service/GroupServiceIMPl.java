package dev.ayush.Splitwise.service;

import dev.ayush.Splitwise.entity.SettlementTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceIMPl implements GroupService{
    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        return null;
    }
}
