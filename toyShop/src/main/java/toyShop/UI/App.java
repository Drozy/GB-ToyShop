package toyShop.UI;

import toyShop.Core.Presenter;
import toyShop.Core.View;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void start() {
        View view = new ConsoleView();
        Presenter presenter = new Presenter(view);
        String filename = "PrizeHistory.txt";
        StringBuilder menu = new StringBuilder()
                .append("\n ====================================== \n")
                .append("1 - Показать все игрушки\n")
                .append("2 - Добавить игрушку\n")
                .append("3 - Изменить частоту выпадения игрушки\n")
                .append("4 - Добавить игрушку в список призов\n")
                .append("5 - Заполнить список призов случайными игрушками\n")
                .append("6 - Показать список призов\n")
                .append("7 - Получить приз\n")
                .append("0 - Выход\n");

        try (Scanner in = new Scanner(System.in)) {
            while (true) {
//                System.out.print("\033[H\033[J");
                System.out.println(menu);
                switch (in.nextLine()) {
                    case "1":
                        presenter.getToyList();
                        break;
                    case "2":
                        presenter.addToy();
                        break;
                    case "3":
                        presenter.changeFreq();
                        break;
                    case "4":
                        presenter.addPrize();
                        break;
                    case "5":
                        presenter.randomPrizeQueue();
                        break;
                    case "6":
                        presenter.getPrizeQueue();
                        break;
                    case "7":
                        try {
                            presenter.getPrize(filename);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "0":
                        in.close();
                        return;
                }
            }
        }
    }
}

