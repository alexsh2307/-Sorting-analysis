package sample;

import java.util.ArrayList;

class BasicMergeSortOnePhase {//доступ поэлементно
    private int k=1;//длина серии

    //фаза разделения
    public void Divide(ArrayList<Integer> MainArray, ArrayList<Integer> B, ArrayList<Integer> C) {
        boolean Change = true;
        for (int i=0; i< MainArray.size(); i++) {
            //кидаем элементы то в один ,то в другой массив
            if (Change) {
                B.add(MainArray.get(i));
                if (B.size() % k == 0)//пока не заполнится серия
                    Change = false;
            }
            else {
                C.add(MainArray.get(i));
                if (C.size() % k == 0)
                    Change = true;
            }
        }
    }
    //Фаза слияния
    public ArrayList<Integer> Merge(ArrayList<Integer> MainArray,ArrayList<Integer> FromFirst,ArrayList<Integer> FromSecond,int k,int IndexFirst,int IndexSecond) {
        int IterationFirst, IterationSecond;
        int BarrierFirst, BarrierSecond;
        if (FromFirst.size() - IndexFirst < k)
            IterationFirst = FromFirst.size() - IndexFirst;
        else
            IterationFirst = k;
        if (FromSecond.size() - IndexSecond < k)
            IterationSecond = FromSecond.size() - IndexSecond;
        else
            IterationSecond = k;
        /////////////////////////////////////////////
        BarrierFirst = IndexFirst + IterationFirst;
        BarrierSecond = IndexSecond + IterationSecond;

        for (int i=0; i<(IterationFirst + IterationSecond); i++) {
            if (BarrierFirst == IndexFirst) {
                MainArray.add(FromSecond.get(IndexSecond));
                IndexSecond += 1;
                continue;
            }
            if (BarrierSecond == IndexSecond) {
                MainArray.add(FromFirst.get(IndexFirst));
                IndexFirst += 1;
                continue;
            }
            if (FromFirst.get(IndexFirst) < FromSecond.get(IndexSecond)) {
                MainArray.add(FromFirst.get(IndexFirst));
                IndexFirst += 1;
            }
            else {
                MainArray.add(FromSecond.get(IndexSecond));
                IndexSecond += 1;
            }
        }
        ArrayList<Integer> IndexArr = new ArrayList<>();
        IndexArr.add(IndexFirst);
        IndexArr.add(IndexSecond);
        return IndexArr;
    }


    public void MergeChoice(ArrayList<Integer> FromArray1,ArrayList<Integer> FromArray2,ArrayList<Integer> ToArray1,ArrayList<Integer> ToArray2,int k) {
        boolean Change = true;
        int IndexFirst = 0 , IndexSecond = 0;
        ArrayList<Integer> IndexArr;
        //поочерди в функцию подаются массивы и слияние происходит по частям то в один ,то в другой(убирая фазу разделения)
        //функция выполняется пока проход не будет завершен
        while (FromArray1.size() != IndexFirst || FromArray2.size() != IndexSecond) {
            if (Change) {
                IndexArr = Merge( ToArray1, FromArray1, FromArray2, k, IndexFirst, IndexSecond);
                IndexFirst = IndexArr.get(0);
                IndexSecond = IndexArr.get(1);
            }
            else{
                IndexArr = Merge(ToArray2, FromArray1, FromArray2, k, IndexFirst, IndexSecond);
                IndexFirst = IndexArr.get(0);
                IndexSecond = IndexArr.get(1);
            }
            Change = !Change;
        }
        FromArray1.clear();
        FromArray2.clear();
    }

    //функция получает ссылку на объект или массив (все изменения происходящие с ссылкой и присовенные отражаются и на основных объектах)
    public long SortOnePhase(int[] myArray) {
        long start = System.currentTimeMillis();
        ArrayList<Integer> MainArray = new ArrayList<>();
        for (int i = 0; i < myArray.length; i++){
            MainArray.add(myArray[i]);
        }
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();
        ArrayList<Integer> D = new ArrayList<>();
        ArrayList<Integer> E = new ArrayList<>();
        int IndexFirst , IndexSecond;
        int k = 1;//длина серии
        boolean Change = true;

        Divide(MainArray, B, C);//используется 1 раз в начале
        while (k <= MainArray.size()/2) {//делим на два тк идет слияние по 2-ум массивам
            if (Change)
                MergeChoice(B, C, D, E, k);
            else
                MergeChoice(D, E, B, C, k);
            Change = !Change;
            k *= 2;
        }
        MainArray.clear();
        //по итогу мы получили два максимально отсортированных массива,
        //которые теперь нужно слить в один
        ArrayList<Integer> IndexArr;
        if (Change) {
            IndexFirst = 0;
            IndexSecond = 0;
            while (B.size() != IndexFirst || C.size() != IndexSecond) {
                IndexArr = Merge(MainArray, B, C, k, IndexFirst, IndexSecond);
                IndexFirst = IndexArr.get(0);
                IndexSecond = IndexArr.get(1);
            }
        }
        else {
            IndexFirst = 0;
            IndexSecond = 0;
            while (D.size() != IndexFirst || E.size() != IndexSecond) {
                IndexArr = Merge(MainArray, D, E, k, IndexFirst, IndexSecond);
                IndexFirst = IndexArr.get(0);
                IndexSecond = IndexArr.get(1);
            }
        }

        return System.currentTimeMillis() - start;
    }
}
