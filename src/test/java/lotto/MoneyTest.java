package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @DisplayName("구매 금액이 1000원보다 작은 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {850, 999, 764, 1})
    void getMoneyByLowerThan1000(int input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매 금액이 1000원으로 나누어 떨어지지 않는 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1001, 2202, 9999})
    void getMoneyByDivideInto1000(int input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
