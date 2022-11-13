package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import lotto.utils.ExceptionType;

public class Input {

    public static String readLine() {
        return Console.readLine();
    }

    public List<Integer> getWinningNumbers() {
        String input = Console.readLine();
        return stringToIngerListByComma(input);
    }

    private int stringToInt(String target) {
        validateIsNumber(target);
        return Integer.parseInt(target);
    }

    private void validateIsNumber(String target) throws IllegalArgumentException {
        String numberRegex = "^[0-9]*$";
        if (!target.matches(numberRegex)) {
            Output.printErrorAndExit(ExceptionType.IS_NOT_NUMBER.getMessage());
            throw new IllegalArgumentException(ExceptionType.IS_NOT_NUMBER.getMessage());
        }
    }

    private List<Integer> stringToIngerListByComma(String input) {
        List<Integer> returns = new ArrayList<>();
        String[] split = input.split(",");
        for (String number : split) {
            returns.add(stringToInt(number));
        }
        return returns;
    }

    public int getWinningBonusNumber() {
        String input = Console.readLine();
        return stringToInt(input);
    }
}
