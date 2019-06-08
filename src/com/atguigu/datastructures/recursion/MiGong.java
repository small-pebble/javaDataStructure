package com.atguigu.datastructures.recursion;

/**
 * @Auther:smallPebble
 * @Date:2019/6/6
 * @Description:com.atguigu.datastructures.recursion
 * @version:1.0
 **/
public class MiGong {
    public static void main(String[] args) {
        //用二维数组创建一个迷宫
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for(int i=0;i<8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] =1;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf("%d\t",map[i][j]);
            }
            System.out.println();
        }
        System.out.println("===============");

        //创建一个找路径的方法，传入起点和地图,返回是否能出迷宫
        getWay(map,1,1);
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf("%d\t",map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean getWay(int[][] map,int i,int j){
        //0表示此处没有走过，1表示墙不能走，2表示此处可以走通，3表示此处不能走通
        if(map[6][5]==2){
            return true;
        }else{
            //找路径的策略为下->右->上->左
            if(map[i][j]==0){
                map[i][j]=2;
                if(getWay(map,i+1,j)){
                    return true;
                }else if(getWay(map,i,j+1)){
                    return true;
                }else if(getWay(map,i-1,j)){
                    return true;
                }else if(getWay(map,i,j-1)){
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }


}
