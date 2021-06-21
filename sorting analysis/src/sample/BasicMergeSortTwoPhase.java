package sample;

import java.util.ArrayList;

class BasicMergeSortTwoPhase {//доступ поэлементно
    private int k=1;//длина серии(количество упорядоченных элементов)

    private ArrayList<Integer> arrayC = new ArrayList<>();//массивы помощники
    private ArrayList<Integer> arrayB=new ArrayList<>();
    private ArrayList<Integer> arrayA = new ArrayList<>();//массив слияния
    private int positionA=0, positionB = 0, positionC = 0;//позиция элемента вспомогательных массивов

    public void merge(){
        //временные порционные(размер = 1) массивы,(по факту не нужны ,но сделаны для max похождения на сортировку данных в файле
        //(чтобы потом переработать алгоритм в сортировку ФАЙЛА не помещающегося в память))
        ArrayList<Integer> S1 = new ArrayList<>();
        ArrayList<Integer> S2 = new ArrayList<>();
        int index1=arrayB.size(),index2=arrayC.size();

        S1.add(arrayB.get(positionB));
        S2.add(arrayC.get(positionC));
        //условия циклов: ориентация на выход за массив,выход за серию
        while ( index1>0 & index2>0 ){
            int i = 0,j = 0;
            while ( i < k & j < k & index1>0 & index2>0 ) {
                //сравниваем элементы, записанные в порционные массивы, вспомогательных массивов(А и В).
                if ( S1.get(0) <= S2.get(0) ) {
                    arrayA.add(S1.get(0));
                    index1--;  i++;
                    S1.clear();
                    if(positionB<arrayB.size()-1) {
                        positionB++;
                        S1.add(arrayB.get(positionB));
                    }
                }
                else {
                    arrayA.add(S2.get(0));
                    index2--;  j++;
                    S2.clear();
                    if(positionC<arrayC.size()-1) {
                        positionC++;
                        S2.add(arrayC.get(positionC));
                    }
                }
            }
            while ( i < k & index1>0) {//если одна из серий закончилась, записываем следом другую
                arrayA.add(S1.get(0));
                index1--;  i++;
                S1.clear();
                if(positionB<arrayB.size()-1) {
                    positionB++;
                    S1.add(arrayB.get(positionB));
                }
            }
            while ( j < k & index2>0) {
                arrayA.add(S2.get(0));
                index2--;  j++;
                S2.clear();
                if(positionC<arrayC.size()-1) {
                    positionC++;
                    S2.add(arrayC.get(positionC));
                }
            }
        }
        while (index1>0) {//если один из массивов закончился ,записываем другой
            arrayA.add(S1.get(0));
            index1--;
            S1.clear();
            if(positionB<arrayB.size()-1) {
                positionB++;
                S1.add(arrayB.get(positionB));
            }
        }
        while (index2>0) {
            arrayA.add(S2.get(0));
            index2--;
            S2.clear();
            if(positionC<arrayC.size()-1) {
                positionC++;
                S2.add(arrayC.get(positionC));
            }
        }
        arrayB.clear(); positionB=0;
        arrayC.clear(); positionC=0;
        k *= 2;//длина серии,которая постоянно увеличивается в два раза
    }

    public void div(){
        ArrayList<Integer> S = new ArrayList<>();//временные порционные(N) массивы
        int index=arrayA.size();

        S.add(arrayA.get(positionA));
        while(index>0){
            for ( int i = 0; i < k & index>0 ; i++ ){
                arrayB.add(S.get(0));
                index--;
                S.clear();
                if(positionA<arrayA.size()-1) {
                    positionA++;
                    S.add(arrayA.get(positionA));
                }
            }
            for ( int j = 0; j < k & index>0 ; j++ ){
                arrayC.add(S.get(0));
                index--;
                S.clear();
                if(positionA<arrayA.size()-1) {
                    positionA++;
                    S.add(arrayA.get(positionA));
                }
            }
        }
        arrayA.clear();
        positionA=0;
    }

    public long BasicSort(int[] myArray){
        long start = System.currentTimeMillis();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < myArray.length; i++){
            array.add(myArray[i]);
        }
        arrayA.addAll(0, array);
        while(k<arrayA.size()){//пока серия не станет равной длине массива
            div();//разделение на массивы
            merge();//слияние
        }
        long timeSpent = System.currentTimeMillis() - start;
        return timeSpent;
    }
}