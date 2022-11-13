package lotto;

import static lotto.utils.Constant.LOTTO_PRICE;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMaker;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.ResultRepository;
import lotto.domain.WinningLotto;
import lotto.view.Input;
import lotto.view.Output;

public class Mission {

    Output output;
    Input input;
    LottoMaker lottoMaker;
    ResultRepository repository;

    Mission() {
        output = new Output();
        input = new Input();
        lottoMaker = new LottoMaker();
        repository = new ResultRepository();

        try {
            play();
        } catch (IllegalArgumentException e) {
        }
    }

    private void play() {
        Money money = getMoney();

        int many = calculateHowManyLotto(money);
        List<Lotto> lottos = lottoMaker.getLottos(many);
        output.printLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            repository.add(rank);
        }

        int priceMoney = repository.getTotalMoney();

        output.printLottoResultTitle();
        EnumMap<Rank, Integer> result = repository.getResult();
        result.forEach((rank, count) -> callOutputAboutRank(rank, count));

        String rate = getRate(money.getMoney(), priceMoney);

        output.printTotalRate(rate);
    }

    private Money getMoney() {
        output.printGetMoney();
        return input.getMoney();
    }

    private int calculateHowManyLotto(Money money) {
        return money.getMoney() / LOTTO_PRICE;
    }

    private WinningLotto getWinningLotto() {
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = addBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private List<Integer> getWinningNumbers() {
        output.printGetWinningNumbers();
        return input.getWinningNumbers();
    }

    private int addBonusNumber() {
        output.pringGetWinningBonusNumber();
        return input.getWinningBonusNumber();
    }

    private String getRate(int inputMoney, int priceMoney) {
        double result = (double) priceMoney / inputMoney;
        result *= 100;
        return String.format("%.1f", result);
    }

    private void callOutputAboutRank(Rank rank, Integer count) {
        if (rank == Rank.SECOND) {
            output.printLottoResultAboutRankWithBonus(rank.getCountOfMatch(), rank.getMoney(), count);
            return;
        }
        if (rank == Rank.ZERO) {
            return;
        }
        output.printLottoResultAboutRank(rank.getCountOfMatch(), rank.getMoney(), count);
    }
}
