package com.anujmehla.util;

import com.anujmehla.exception.InsufficientBalance;

import java.util.HashMap;
import java.util.Map;

public class PaymentGatewaySimulator {

    private static Map<String, Double> accountBalanceMap = new HashMap<>();

    static {
        accountBalanceMap.put("acnt-1212", 2000.0);
        accountBalanceMap.put("acnt-1313", 4000.0);
        accountBalanceMap.put("acnt-1414", 3000.0);
    }

    public static boolean validateFareBalanceCriteria(String accountNumber, Double fare) throws InsufficientBalance {
        if (fare > accountBalanceMap.get(accountNumber)) {
            throw new InsufficientBalance("Low Balance");
        }
        return true;
    }
}
