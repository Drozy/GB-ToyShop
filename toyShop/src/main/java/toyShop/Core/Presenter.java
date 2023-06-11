package toyShop.Core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Presenter {
    private View ui;
    private static LinkedList<Toy> toyList = new LinkedList<>();
    private Queue<Toy> prizeQueue;

    public Presenter(View ui) {
        this.ui = ui;
        fillToysList();
        prizeQueue = new PriorityQueue<>(toyList.size(), Comparator.comparing(Toy::getFrequency).reversed());
    }

    public void getToyList() {
        for (Toy toy: toyList) {
            ui.set(String.valueOf(toy));
        }
    }

    public void getPrizeQueue() {
        for (Toy toy: prizeQueue) {
            ui.set(String.valueOf(toy));
        }
    }

    private void fillToysList() {
        toyList.add(new Toy(1,"Робот", 15, 20));
        toyList.add(new Toy(2,"Конструктор", 10, 10));
        toyList.add(new Toy(3,"Настольная игра", 10, 10));
        toyList.add(new Toy(4,"Машинка", 20, 30));
        toyList.add(new Toy(5,"Кукла", 20, 20));
        toyList.add(new Toy(6,"Мягкая игрушка", 30, 25));
    }

    public void getPrize(String filename) throws IOException {
        if (prizeQueue.isEmpty()) {
            ui.set("Список призов пуст.");
        }
        else {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            Toy toy = prizeQueue.poll();
            ui.set("Приз: " + toy);
            for (Toy t : toyList) {
                if (t.getId() == toy.getId()) {
                    t.setAmount(t.getAmount() - 1);
                }
            }
            bw.write( LocalDateTime.now().toString() + "\t" + toy + "\n");
            bw.close();
        }
    }

    public void addToy() {
        boolean flag = true;
        int id = 0, amount = 0, freq = 0;
        ui.set("Введите информацию о новой игрушке");
        while(flag) {
            ui.set("id:");
            id = Integer.parseInt(ui.get());
            for (Toy t : toyList) {
                if (t.getId() == id) {
                    ui.set("Игрушка с таким id уже есть в магазине.");
                    flag = true;
                    break;
                }
                else flag = false;
            }
        }
        ui.set("Название:");
        String name = ui.get();
        while(!flag) {
            ui.set("Количество:");
            amount = Integer.parseInt(ui.get());
            if (amount < 0)
                ui.set("Ошибка ввода");
            else flag = true;
        }
        while(flag) {
            ui.set("Частота выпадения, %:");
            freq = Integer.parseInt(ui.get());
            if (freq < 0 || freq > 100)
                ui.set("Ошибка ввода");
            else flag = false;
        }
        toyList.add(new Toy(id, name, amount, freq));
    }

    public void changeFreq() {
        ui.set("Введите id игрушки для редактирования:");
        int id = Integer.parseInt(ui.get());
        boolean flag = true;
        for (Toy t : toyList) {
            if (t.getId() == id) {
                while(true) {
                    ui.set("Частота выпадения, %:");
                    int freq = Integer.parseInt(ui.get());
                    if (freq < 0 || freq > 100)
                        ui.set("Ошибка ввода");
                    else {
                        t.setFrequency(freq);
                        break;
                    }
                }
                flag = false;
            }
        }
        if (flag) ui.set("Игрушки с таким id нет в магазине.");
    }

    public void addPrize() {
        ui.set("Введите id игрушки, которую хотите сделать призом:");
        int id = Integer.parseInt(ui.get());
        boolean flag = true;
        for (Toy t : toyList) {
            if (t.getId() == id) {
                if (t.getAmount() > 0) {
                    prizeQueue.add(t);
                    ui.set("Добавлено в призы: " + t.getName());
                }
                else ui.set("Игрушек " + t.getName() + " больше нет.");
                flag = false;
            }
        }
        if (flag) ui.set("Игрушки с таким id нет в магазине.");
    }

    public void randomPrizeQueue() {
        Random rand = new Random();
        int index;
        for (int i = 0; i < 10; i++) {
            index = rand.nextInt(toyList.size());
            if (toyList.get(index).getAmount() > 0) {
                prizeQueue.add(toyList.get(index));
            }
            else i--;
        }
    }
}
