package sample;

import java.util.ArrayList;

public class NaturalTwoPhaseSortWithArray {
    int IndexFirst ;
    int IndexSecond;
    int LastNumberFirst;
    int LastNumberSecond;


    public void DivideNatural(ArrayList<Integer> MainArray, ArrayList<Integer> FirstArray, ArrayList<Integer> SecondArray) {
        // в данном случае разделение на массивы будет не поэлементо а по длине упорядоченной серии
        // (проверяем элементы до тех пор пока они упорядочены и записываем эту серию в один из массиво)
        int LastNumber = 0;
        boolean Change = true;
        for (int i=0; i< MainArray.size(); i++) {
            if (MainArray.get(i) < LastNumber)
                Change = !Change;
            if (Change)
                FirstArray.add(MainArray.get(i));
            else
                SecondArray.add(MainArray.get(i));
            LastNumber = MainArray.get(i);
        }
    }

    public ArrayList<Integer> FillSession(ArrayList<Integer> Array,int Index,ArrayList<Integer> MainArray) {
        ArrayList<Integer> result = new ArrayList<>();
        int LastNumber = 0;
        while (Array.size() != Index) {
            MainArray.add(Array.get(Index));
            LastNumber = Array.get(Index);
            Index ++;
            if (Array.size() != Index) {
                if (Array.get(Index) < LastNumber) {
                    result.add(Index);
                    result.add(Array.get(Index));
                    return result;
                }
            }
        }
        result.add(Index);
        result.add(LastNumber);
        return result;
    }

    public boolean CheckAndFillSessionOne(ArrayList<Integer> MainArray , ArrayList<Integer> ArrayFirst, ArrayList<Integer> ArraySecond) {
        ArrayList<Integer> Arr;
        if (ArrayFirst.size() == IndexFirst) {
            Arr = FillSession(ArraySecond, IndexSecond, MainArray);
            IndexSecond = Arr.get(0);
            LastNumberSecond = Arr.get(1);
            return true;
        }
        if (ArrayFirst.get(IndexFirst) < LastNumberFirst) {
            Arr = FillSession(ArraySecond, IndexSecond, MainArray);
            IndexSecond = Arr.get(0);
            LastNumberSecond = Arr.get(1);
            LastNumberFirst = ArrayFirst.get(IndexFirst);
            return true;
        }
        return false;
    }
    public boolean CheckAndFillSessionTwo(ArrayList<Integer> MainArray,ArrayList<Integer> ArrayFirst,ArrayList<Integer> ArraySecond) {
        ArrayList<Integer> Arr;
        if (ArrayFirst.size() == IndexSecond) {
            Arr = FillSession(ArraySecond, IndexFirst, MainArray);
            IndexFirst = Arr.get(0);
            LastNumberFirst = Arr.get(1);
            return true;
        }
        if (ArrayFirst.get(IndexSecond) < LastNumberSecond) {
            Arr = FillSession(ArraySecond, IndexFirst, MainArray);
            IndexFirst = Arr.get(0);
            LastNumberFirst = Arr.get(1);
            LastNumberSecond = ArrayFirst.get(IndexSecond);
            return true;
        }
        return false;
    }

    public int MergeNatural(ArrayList<Integer> MainArray,ArrayList<Integer> ArrayFirst,ArrayList<Integer> ArraySecond)
    {
        boolean WasEndSession;

        WasEndSession = CheckAndFillSessionOne(MainArray, ArrayFirst, ArraySecond);
        if (WasEndSession)
            return 1;
        WasEndSession = CheckAndFillSessionTwo(MainArray, ArraySecond, ArrayFirst);
        if (WasEndSession)
            return 1;
        if (ArrayFirst.get(IndexFirst) < ArraySecond.get(IndexSecond)) {
            LastNumberFirst = ArrayFirst.get(IndexFirst);
            MainArray.add(ArrayFirst.get(IndexFirst));
            IndexFirst += 1;
        }
        else {
            LastNumberSecond = ArraySecond.get(IndexSecond);
            MainArray.add(ArraySecond.get(IndexSecond));
            IndexSecond += 1;
        }
        return 0;
    }

    public long SortTwoPhaseNatural(int[] array) {
        long start = System.currentTimeMillis();
        ArrayList<Integer> MainArray = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            MainArray.add(array[i]);
        }

        ArrayList<Integer> B = new ArrayList<>();//вспомогательные массивы
        ArrayList<Integer> C = new ArrayList<>();
        int size = MainArray.size();
        int AddDiv = 0;
        int Temp;
        while (AddDiv != 1) {
            B.clear();
            C.clear();
            DivideNatural(MainArray,B, C);
            MainArray.clear();
            IndexFirst = 0;
            IndexSecond = 0;
            LastNumberFirst = 0;
            LastNumberSecond = 0;
            AddDiv = 0;
            while (IndexFirst + IndexSecond != size) {
                Temp =  MergeNatural(MainArray, B, C );
                AddDiv += Temp;
            }
        }
        return System.currentTimeMillis() - start;
    }
}
