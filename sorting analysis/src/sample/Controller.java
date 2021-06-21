package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private AreaChart<Integer, Integer> areaChart1;
    @FXML
    private ComboBox<String> orderliness1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items1 = FXCollections.observableArrayList(
                "Неупорядоченный",
                "Упорядоченный",
                "Упорядоченный обратно",
                "Частично"
        );
        orderliness1.setItems(items1);
        ObservableList<String> items2 = FXCollections.observableArrayList(
                "Обмен",
                "Выбор",
                "Вставка",
                "Шелла",
                "Линейная",
                "Однофазная",
                "Двухфазная",
                "Естественная однофазная",
                "Естественная двухфазная",
                "Быстрая (Левый)",
                "Быстрая (Правый)",
                "Пирамидальная"
        );
        orderliness2.setItems(items2);
    }

    @FXML
    private TextArea COUNT1;
    @FXML
    private TextArea COUNT2;
    @FXML
    private AreaChart<Integer, Integer> areaChart2;
    @FXML
    private ComboBox<String> orderliness2;
    @FXML
    private TextArea COUNTPOINT1;
    @FXML
    private TextArea COUNTPOINT2;
    @FXML
    private TextArea DEGREESORT1;
    @FXML
    private TextArea DEGREESORT2;
    @FXML
    private CheckBox NonSort;
    @FXML
    private CheckBox Sort;
    @FXML
    private CheckBox backSort;
    @FXML
    private CheckBox partSort;
    @FXML
    private CheckBox Change;
    @FXML
    private CheckBox Choice;
    @FXML
    private CheckBox Insertion;
    @FXML
    private CheckBox shell;
    @FXML
    private CheckBox linear;
    @FXML
    private CheckBox OnePhase;
    @FXML
    private CheckBox TwoPhase;
    @FXML
    private CheckBox NaturalOnePhase;
    @FXML
    private CheckBox NaturalTwoPhase;
    @FXML
    private CheckBox QuickSortLeft;
    @FXML
    private CheckBox QuickSortRight;
    @FXML
    private CheckBox Piramidal;

    int count;
    int degreeSort;
    int countPoit;

    @FXML
    public void run1(ActionEvent actionEvent) {

        areaChart1.getData().clear();
        String N = COUNTPOINT1.getText();
        if (!N.matches("^?\\d+$")) {
            return;
        }
        countPoit = Integer.parseInt(N);

        N = COUNT1.getText();
        if (!N.matches("^?\\d+$")) {
            return;
        }
        count = Integer.parseInt(N);

        int[] change = new int[countPoit];
        int[] choise = new int[countPoit];
        int[] insertion = new int[countPoit];
        int[] shella = new int[countPoit];
        int[] line = new int[countPoit];
        int[] onePhase = new int[countPoit];
        int[] twoPhase = new int[countPoit];
        int[] naturalOnePhase = new int[countPoit];
        int[] naturalTwoPhase = new int[countPoit];
        int[] quickRight = new int[countPoit];
        int[] quickLeft = new int[countPoit];
        int[] heapSort = new int[countPoit];

        if (orderliness1.getSelectionModel().getSelectedItem() == "Неупорядоченный") {
            if (Change.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    change[i] = (int) forwardChange(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(change, "Обмен");
            }
            if (Choice.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    choise[i] = (int) forwardChoice(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(choise, "Выбор");
            }
            if (Insertion.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    insertion[i] = (int) forwardInsertion(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(insertion, "Вставка");
            }
            if (shell.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    shella[i] = (int) shellSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(shella, "Шелла");
            }
            if (linear.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    line[i] = (int) linearSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(line, "Линейная");
            }
            if (OnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortOnePhase onePhase1 = new BasicMergeSortOnePhase();
                    onePhase[i] = (int) onePhase1.SortOnePhase(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(onePhase, "Однофазная");
            }
            if (TwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortTwoPhase twoPhase1 = new BasicMergeSortTwoPhase();
                    twoPhase[i] = (int) twoPhase1.BasicSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(twoPhase, "Двухфазная");
            }
            if (NaturalOnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalOnePhaseSortWithArray onePhase1 = new NaturalOnePhaseSortWithArray();
                    naturalOnePhase[i] = (int) onePhase1.SortOnePhaseNatural(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalOnePhase, "Естественная однофазная");
            }
            if (NaturalTwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalTwoPhaseSortWithArray twoPhase1 = new NaturalTwoPhaseSortWithArray();
                    naturalTwoPhase[i] = (int) twoPhase1.SortTwoPhaseNatural(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalTwoPhase, "Естественная двухфазная");
            }
            if (QuickSortLeft.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(nonSortMass(count + part));
                    quickLeft[i] = (int) left.quickSortLeft();
                    part += 10;
                }
                setGraph(quickLeft, "Быстрая (Левый)");
            }
            if (QuickSortRight.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(nonSortMass(count + part));
                    quickRight[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph(quickRight, "Быстрая (Правый)");
            }
            if (Piramidal.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort right = new HeapSort();
                    heapSort[i] = (int) right.mainSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph(heapSort, "Пирамидальная");
            }
        }

        if (orderliness1.getSelectionModel().getSelectedItem() == "Упорядоченный") {
            if (Change.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    change[i] = (int) forwardChange(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(change, "Обмен");
            }
            if (Choice.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    choise[i] = (int) forwardChoice(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(choise, "Выбор");
            }
            if (Insertion.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    insertion[i] = (int) forwardInsertion(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(insertion, "Вставка");
            }
            if (shell.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    shella[i] = (int) shellSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(shella, "Шелла");
            }
            if (linear.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    line[i] = (int) linearSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(line, "Линейная");
            }
            if (OnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortOnePhase onePhase1 = new BasicMergeSortOnePhase();
                    onePhase[i] = (int) onePhase1.SortOnePhase(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(onePhase, "Однофазная");
            }
            if (TwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortTwoPhase twoPhase1 = new BasicMergeSortTwoPhase();
                    twoPhase[i] = (int) twoPhase1.BasicSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(twoPhase, "Двухфазная");
            }
            if (NaturalOnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalOnePhaseSortWithArray onePhase1 = new NaturalOnePhaseSortWithArray();
                    naturalOnePhase[i] = (int) onePhase1.SortOnePhaseNatural(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalOnePhase, "Естественная однофазная");
            }
            if (NaturalTwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalTwoPhaseSortWithArray twoPhase1 = new NaturalTwoPhaseSortWithArray();
                    naturalTwoPhase[i] = (int) twoPhase1.SortTwoPhaseNatural(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalTwoPhase, "Естественная двухфазная");
            }
            if (QuickSortLeft.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(fullSortMass(count + part));
                    quickLeft[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph(quickLeft, "Быстрая (Левый)");
            }
            if (QuickSortRight.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(fullSortMass(count + part));
                    quickRight[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph(quickRight, "Быстрая (Правый)");
            }
            if (Piramidal.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort right = new HeapSort();
                    heapSort[i] = (int) right.mainSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph(heapSort, "Пирамидальная");
            }
        }

        if (orderliness1.getSelectionModel().getSelectedItem() == "Упорядоченный обратно") {
            if (Change.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    change[i] = (int) forwardChange(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(change, "Обмен");
            }
            if (Choice.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    choise[i] = (int) forwardChoice(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(choise, "Выбор");
            }
            if (Insertion.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    insertion[i] = (int) forwardInsertion(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(insertion, "Вставка");
            }
            if (shell.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    shella[i] = (int) shellSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(shella, "Шелла");
            }
            if (linear.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    line[i] = (int) linearSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(line, "Линейная");
            }
            if (OnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortOnePhase onePhase1 = new BasicMergeSortOnePhase();
                    onePhase[i] = (int) onePhase1.SortOnePhase(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(onePhase, "Однофазная");
            }
            if (TwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortTwoPhase twoPhase1 = new BasicMergeSortTwoPhase();
                    twoPhase[i] = (int) twoPhase1.BasicSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(twoPhase, "Двухфазная");
            }
            if (NaturalOnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalOnePhaseSortWithArray onePhase1 = new NaturalOnePhaseSortWithArray();
                    naturalOnePhase[i] = (int) onePhase1.SortOnePhaseNatural(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalOnePhase, "Естественная однофазная");
            }
            if (NaturalTwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalTwoPhaseSortWithArray twoPhase1 = new NaturalTwoPhaseSortWithArray();
                    naturalTwoPhase[i] = (int) twoPhase1.SortTwoPhaseNatural(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalTwoPhase, "Естественная двухфазная");
            }
            if (QuickSortLeft.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(backSortMass(count + part));
                    quickLeft[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph(quickLeft, "Быстрая (Левый)");
            }
            if (QuickSortRight.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(backSortMass(count + part));
                    quickRight[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph(quickRight, "Быстрая (Правый)");
            }
            if (Piramidal.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort right = new HeapSort();
                    heapSort[i] = (int) right.mainSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph(heapSort, "Пирамидальная");
            }
        }

        if (orderliness1.getSelectionModel().getSelectedItem() == "Частично") {
            String n = DEGREESORT1.getText();
            if (!n.matches("^?\\d+$")) {
                return;
            }
            degreeSort = Integer.parseInt(n);
            if (Change.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    change[i] = (int) forwardChange(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(change, "Обмен");
            }
            if (Choice.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    choise[i] = (int) forwardChoice(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(choise, "Выбор");
            }
            if (Insertion.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    insertion[i] = (int) forwardInsertion(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(insertion, "Вствака");
            }
            if (shell.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    shella[i] = (int) shellSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(shella, "Шелла");
            }
            if (linear.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    line[i] = (int) linearSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(line, "Линейная");
            }
            if (OnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortOnePhase onePhase1 = new BasicMergeSortOnePhase();
                    onePhase[i] = (int) onePhase1.SortOnePhase(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(onePhase, "Однофазная");
            }
            if (TwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BasicMergeSortTwoPhase twoPhase1 = new BasicMergeSortTwoPhase();
                    twoPhase[i] = (int) twoPhase1.BasicSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(twoPhase, "Двухфазная");
            }
            if (NaturalOnePhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalOnePhaseSortWithArray onePhase1 = new NaturalOnePhaseSortWithArray();
                    naturalOnePhase[i] = (int) onePhase1.SortOnePhaseNatural(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalOnePhase, "Естественная однофазная");
            }
            if (NaturalTwoPhase.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    NaturalTwoPhaseSortWithArray twoPhase1 = new NaturalTwoPhaseSortWithArray();
                    naturalTwoPhase[i] = (int) twoPhase1.SortTwoPhaseNatural(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(naturalTwoPhase, "Естественная двухфазная");
            }
            if (QuickSortLeft.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(partSortMass(count + part));
                    quickLeft[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph(quickLeft, "Быстрая (Левый)");
            }
            if (QuickSortRight.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(partSortMass(count + part));
                    quickRight[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph(quickRight, "Быстрая (Правый)");
            }
            if (Piramidal.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort right = new HeapSort();
                    heapSort[i] = (int) right.mainSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph(heapSort, "Пирамидальная");
            }
        }
    }

    @FXML
    public void run2(ActionEvent actionEvent) {
        areaChart2.getData().clear();
        String N = COUNTPOINT2.getText();
        if (!N.matches("^?\\d+$")) {
            return;
        }
        countPoit = Integer.parseInt(N);

        N = COUNT2.getText();
        if (!N.matches("^?\\d+$")) {
            return;
        }
        count = Integer.parseInt(N);

        int[] nonSort = new int[countPoit];
        int[] sort = new int[countPoit];
        int[] BackSort = new int[countPoit];
        int[] PartSort = new int[countPoit];

        if (orderliness2.getSelectionModel().getSelectedItem() == "Обмен") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) forwardChange(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) forwardChange(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) forwardChange(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) forwardChange(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Выбор") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) forwardChoice(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) forwardChoice(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) forwardChoice(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) forwardChoice(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Вставка") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) forwardInsertion(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) forwardInsertion(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) forwardInsertion(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) forwardInsertion(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Шелла") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) shellSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) shellSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) shellSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) shellSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Линейная") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) linearSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) linearSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) linearSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) linearSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (orderliness2.getSelectionModel().getSelectedItem() == "Однофазная") {
            BasicMergeSortOnePhase onePhase1 = new BasicMergeSortOnePhase();
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) onePhase1.SortOnePhase(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) onePhase1.SortOnePhase(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) onePhase1.SortOnePhase(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) onePhase1.SortOnePhase(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Двухфазная") {
            BasicMergeSortTwoPhase twoPhase1 = new BasicMergeSortTwoPhase();
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) twoPhase1.BasicSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) twoPhase1.BasicSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) twoPhase1.BasicSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) twoPhase1.BasicSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Естественная однофазная") {
            NaturalOnePhaseSortWithArray onePhase1 = new NaturalOnePhaseSortWithArray();
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) onePhase1.SortOnePhaseNatural(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) onePhase1.SortOnePhaseNatural(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) onePhase1.SortOnePhaseNatural(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) onePhase1.SortOnePhaseNatural(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Естественная двухфазная") {
            NaturalTwoPhaseSortWithArray twoPhase1 = new NaturalTwoPhaseSortWithArray();
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    nonSort[i] = (int) twoPhase1.SortTwoPhaseNatural(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    sort[i] = (int) twoPhase1.SortTwoPhaseNatural(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    BackSort[i] = (int) twoPhase1.SortTwoPhaseNatural(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    PartSort[i] = (int) twoPhase1.SortTwoPhaseNatural(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Быстрая (Левый)") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(nonSortMass(count + part));
                    nonSort[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(fullSortMass(count + part));
                    sort[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(backSortMass(count + part));
                    BackSort[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort left = new QuickSort(partSortMass(count + part));
                    PartSort[i] = (int) left.quickSortLeft();
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Быстрая (Правый)") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(nonSortMass(count + part));
                    nonSort[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(fullSortMass(count + part));
                    sort[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(backSortMass(count + part));
                    BackSort[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    QuickSort right = new QuickSort(partSortMass(count + part));
                    PartSort[i] = (int) right.quickSortRight();
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
        if (orderliness2.getSelectionModel().getSelectedItem() == "Пирамидальная") {
            if (NonSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort left = new HeapSort();
                    nonSort[i] = (int) left.mainSort(nonSortMass(count + part));
                    part += 1000;
                }
                setGraph2(nonSort, "Неупорядоченный");
            }
            if (Sort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort left = new HeapSort();
                    sort[i] = (int) left.mainSort(fullSortMass(count + part));
                    part += 1000;
                }
                setGraph2(sort, "Упорядоченный");
            }
            if (backSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort left = new HeapSort();
                    BackSort[i] = (int) left.mainSort(backSortMass(count + part));
                    part += 1000;
                }
                setGraph2(BackSort, "Упорядоченный обратно");
            }
            if (partSort.isSelected()) {
                int part = 0;
                for (int i = 0; i < countPoit; i++) {
                    HeapSort left = new HeapSort();
                    PartSort[i] = (int) left.mainSort(partSortMass(count + part));
                    part += 1000;
                }
                setGraph2(PartSort, "Частично");
            }
        }
    }

    public int[] nonSortMass(int count) {
        int[] myArray = new int[count];
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = (int) (Math.random() * (count+10));
        }
        return myArray;
    }

    public int[] fullSortMass(int count) {

        int[] myArray = new int[count];
        if (count!=0) {
            myArray[0] = (int) (Math.random() * 5);
            for (int i = 1; i < myArray.length; i++) {
                myArray[i] = myArray[i - 1] + ((int) (Math.random() * 5));
            }
        }
        return myArray;
    }

    public int[] backSortMass(int count) {
        int[] a = new int[count];
        a[0] = (int) (Math.random() * 5);
        for (int i = 1; i < a.length; i++) {
            a[i] = a[i - 1] + ((int) (Math.random() * 5));
        }
        int myArray[] = new int[count];
        for (int i = count - 1; i >= 0; i--) {
            myArray[count - i - 1] = a[i];
        }
        return myArray;
    }

    public int[] partSortMass(int count) {
        int[] myArray = new int[count];
        myArray[0] = (int) (Math.random() * 5);
        int i;
        for (i = 1; i < ((count * degreeSort) / 100); i++) {
            myArray[i] = myArray[i - 1] + ((int) (Math.random() * 5));
        }
        for (; i < count; i++) {
            myArray[i] = (int) (Math.random() * count);
        }
        return myArray;
    }

    public long forwardChange(int myArray[]) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < myArray.length - 1; i++) {
            boolean wasSwap = false;
            for (int j = 0; j < myArray.length - 1; j++) {
                if (myArray[j] > myArray[j + 1]) {
                    int temp = myArray[j];
                    myArray[j] = myArray[j + 1];
                    myArray[j + 1] = temp;
                    wasSwap = true;
                }
            }
            if (!wasSwap) break;
        }
        long timeSpent = System.currentTimeMillis() - start;
        return timeSpent;
    }

    public long forwardChoice(int myArray[]) {
        long start = System.currentTimeMillis();
        // Перебираем каждый элемент массива (кроме последнего, он уже будет отсортирован к тому времени, когда мы до него доберемся)
        for (int i = 0; i < myArray.length - 1; i++) {
            // В переменной smallestIndex хранится индекс наименьшего значения, которое мы нашли в этой итерации.
            // Начинаем с того, что наименьший элемент в этой итерации - это первый элемент (индекс 0)
            int smallestIndex = i;
            // Затем ищем элемент поменьше в остальной части массива
            for (int j = i + 1; j < myArray.length; j++) {
                if (myArray[j] < myArray[smallestIndex])
                    smallestIndex = j;
            }
            // smallestIndex теперь наименьший элемент.
            // Меняем местами наше начальное наименьшее число с тем, которое мы обнаружили
            int t = myArray[i];
            myArray[i] = myArray[smallestIndex];
            myArray[smallestIndex] = t;
        }
        long timeSpent = System.currentTimeMillis() - start;
        return timeSpent;
    }

    public long forwardInsertion(int myArray[]) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < myArray.length; i++) {
            int tmp = myArray[i];
            int j = i - 1;
            for (; j >= 0 && myArray[j] > tmp; j--)//ищем место элемента в отсортированной части массива
                myArray[j + 1] = myArray[j];//сдвигаем элемент вправо, пока не дошли
            //место найдено, вставить элемент
            myArray[j + 1] = tmp;
        }
        long timeSpent = System.currentTimeMillis() - start;
        return timeSpent;
    }

    public long shellSort(int myArray[]) {
        long start = System.currentTimeMillis();
        int increment = 2 * (int) (Math.floor(Math.log(myArray.length) / Math.log(2)) - 1) + 1; //Длина между сравниваемыми элементами
        while (increment > 0) {
            for (int k = 0; k < myArray.length; k++) {
                int j = k; // сохраняем индекс и элемент
                int temp = myArray[k];
                for (; (j >= increment) && (myArray[j - increment] > temp); j -= increment)// просматриваем остальные элементы массива, отстоящие от j-ого на величину приращения
                    myArray[j] = myArray[j - increment];// пока отстоящий элемент больше текущего перемещаем его на текущую позицию
                myArray[j] = temp; // на выявленное место помещаем сохранённый элемент
            }
            if (increment > 1) //сокращаем расстояние между сравниваемыми элементами
                increment = increment / 2;
            else if (increment == 1)
                break;
        }
        for (int j = 0; j < myArray.length - 2; j++) {
            if (myArray[j] > myArray[j + 1]) {
                int b = myArray[j];
                myArray[j] = myArray[j + 1];
                myArray[j + 1] = b;
            }
        }
        long timeSpent = System.currentTimeMillis() - start;
        return timeSpent;
    }

    public long linearSort(int myArray[]) {
        int helpArray[] = new int[count];
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = (int) (Math.random() * count);
        }
        long start = System.currentTimeMillis();
        //Последовательно пройдём по массиву  и запишем во вспомогательный количество чисел, равных i
        for (int i = 0; i < myArray.length; i++) {
            helpArray[myArray[i]]++;
        }
        //Теперь достаточно пройти по вспомогательному массиву  и для каждого числа number из диапазона допустимых значений последовательно записать в массив А число number C[number] раз.
        int curIndex = 0;
        for (int i = 0; i < helpArray.length; i++) {
            for (int j = 0; j < helpArray[i]; j++) {
                myArray[curIndex++] = i;
            }
        }
        long timeSpent = System.currentTimeMillis() - start;
        return timeSpent;
    }

    public void setGraph(int mass[], String name) {
        XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < countPoit; i++) {
            series.getData().add(new XYChart.Data<>(i, mass[i]));
        }
        series.setName(name);
        areaChart1.getData().add(series);
    }

    public void setGraph2(int mass[], String name) {
        XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < countPoit; i++) {
            series.getData().add(new XYChart.Data<>(i, mass[i]));
        }
        series.setName(name);
        areaChart2.getData().add(series);
    }
}