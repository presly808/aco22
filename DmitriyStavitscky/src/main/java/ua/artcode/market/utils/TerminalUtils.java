package ua.artcode.market.utils;

import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.UserType;

public class TerminalUtils {

    public static void isNotBoss(Salesman manager, Salesman chief, Salesman subordinate) throws WrongSubordinateException {
        if (checkBoss(manager, chief, subordinate) == UserType.MANAGER) {
            throw new WrongSubordinateException("Your boss can not become a subordinate");
        }
    }

    private static UserType checkBoss(Salesman manager, Salesman chief, Salesman subordinate) {
        // search chief
        if (manager.equals(chief)) {
            return UserType.CHIEF;
        }

        UserType res = UserType.SUBSALESMAN;
        if (manager.getSubordinates().size() != 0) {
            for (int i = 0; i < manager.getSubordinates().size(); i++) {
                res = checkBoss(manager.getSubordinates().get(i), chief, subordinate);

                // if we find our boss - we check whether his subordinate boss is not
                if (res == UserType.MANAGER || res == UserType.CHIEF && manager.equals(subordinate)) {
                    return UserType.MANAGER;
                }
            }
        }
        return res;
    }
}