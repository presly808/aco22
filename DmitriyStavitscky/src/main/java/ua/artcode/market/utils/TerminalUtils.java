package ua.artcode.market.utils;

import ua.artcode.market.models.Salesman;

public class TerminalUtils {
    public static boolean isBoss(Salesman manager, Salesman chief, Salesman subordinate) {
        // search chief
        if (manager.equals(chief)) {
            return false;
        }

        boolean res = false;
        if (manager.getSubordinates().size() != 0) {
            for (int i = 0; i < manager.getSubordinates().size(); i++) {
                if (!isBoss(manager.getSubordinates().get(i), chief, subordinate) &&
                        manager.equals(subordinate)) {
                    return true;

                } else if (isBoss(manager.getSubordinates().get(i), chief, subordinate)) {
                    res = true;
                }
            }
        }

        return res;
    }
}
/*
if(!IsNotBoss(manager.getSubordinates().get(i), chief, subordinate) &&
        manager.equals(subordinate)){
        res = true;
        }*/
