package ua.artcode.market.utils;

import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.models.Salesman;

public class TerminalUtils {

    private static final int THIS_IS_THE_BOSS = 2;

    public static void isNotBoss(Salesman manager, Salesman chief, Salesman subordinate) throws WrongSubordinateException {

        if (checkBoss(manager, chief, subordinate) == THIS_IS_THE_BOSS) {
            throw new WrongSubordinateException("Your boss can not become a subordinate");
        }
    }

    private static int checkBoss(Salesman manager, Salesman chief, Salesman subordinate) {
        // search chief
        if (manager.equals(chief)) {
            return 1;
        }

        int res = 0;
        if (manager.getSubordinates().size() != 0) {
            for (int i = 0; i < manager.getSubordinates().size(); i++) {
                res = checkBoss(manager.getSubordinates().get(i), chief, subordinate);

                // if we find our boss - we check whether his subordinate boss is not
                if (res == 2 || res == 1 && manager.equals(subordinate)) {
                    return 2;
                }
            }
        }

        return res;
    }
}

    /*public static boolean isNotBoss(Salesman manager, Salesman chief, Salesman subordinate) {
        // search chief
        if (manager.equals(chief)) {
            return false;
        }

        boolean res = false;
        if (manager.getSubordinates().size() != 0) {
            for (int i = 0; i < manager.getSubordinates().size(); i++) {
                if (!isNotBoss(manager.getSubordinates().get(i), chief, subordinate) &&
                        manager.equals(subordinate)) {
                    return true;

                } else if (isNotBoss(manager.getSubordinates().get(i), chief, subordinate)) {
                    res = true;
                }
            }
        }

        return res;
    }
}*/