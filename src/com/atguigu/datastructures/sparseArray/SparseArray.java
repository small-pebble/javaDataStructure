package com.atguigu.datastructures.sparseArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @Auther:smallPebble
 * @Date:2019/5/30
 * @Description:com.atguigu.datastructures
 * @version:1.0
 **/
public class SparseArray {
    public static void main(String[] args) throws Exception {
        //创建一个二维数组
        System.out.println("创建一个二维数组");
        int[][] arr1 = new int[11][11];
        //1代表黑色的棋子，2代表白色的棋子
        arr1[1][2] = 1;
        arr1[2][3] = 2;
        //打印原始数组
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t", arr1[i][j]);
            }
            System.out.println();
        }

        //将原始数组转化为稀疏数组
        System.out.println("将原始数组转化为稀疏数组");
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (arr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int row = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (arr1[i][j] != 0) {
                    row++;
                    sparseArr[row][0] = i;
                    sparseArr[row][1] = j;
                    sparseArr[row][2] = arr1[i][j];
                }
            }
        }

        for (int i = 0; i < sum + 1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d\t", sparseArr[i][j]);
            }
            System.out.println();
        }

        File file = new File("C:\\Users\\11585\\Documents\\javadatastructure\\array.data");
       /* FileWriter fw = new FileWriter(file);
        for(int i=0;i<sum+1;i++){
            for(int j=0;j<3;j++){
                fw.write(sparseArr[i][j]+"\t");
            }
            fw.write("\r\n");
        }
        fw.close();*/

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int rowr = 0;
        int[][] sparseArr3 = new int[sum + 1][3];
        while ((line = br.readLine()) != null) {
            String[] strs = line.split("\t");
            for (int i = 0; i < 3; i++) {
                sparseArr3[rowr][i] = Integer.parseInt(strs[i]);
            }
            rowr++;
        }
        br.close();

        System.out.println("读出的数据");
        for (int i = 0; i < sum + 1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d\t", sparseArr3[i][j]);
            }
            System.out.println();
        }


        System.out.println("将稀疏数组转化为原始数组");
        int[][] arr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t", arr2[i][j]);
            }
            System.out.println();
        }
    }
}
