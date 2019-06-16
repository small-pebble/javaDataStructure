package com.atguigu.datastructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @Auther:smallPebble
 * @Date:2019/6/16
 * @Description:com.atguigu.datastructures.huffmancode
 * @version:1.0
 **/
public class HuffmanCode {
    public static void main(String[] args) {
        //i like like like java do you like a java
        //String str = "i like like like java do you like a java";
        //byte[] strBytes = str.getBytes();
        //List<Node> list = getBytesMap(strBytes);
        //创建此字符串的赫弗曼树
        //1.统计该字符串每个字符出现的次数，并存在map中，字符为键，出现次数为值
        //2.将值按顺序排列，并创建赫弗曼树
        //Node root = getBytesHuffmanTree(list);
        //preOrder(root);
        //将此赫弗曼树进行赫弗曼编码
        //getCodes(root);
        //System.out.println(huffmanCodes);
        //byte[] bytes = huffmanZip(strBytes);
        //System.out.println(Arrays.toString(bytes));
        //System.out.println(decode(huffmanCodes,bytes));
        /*byte[] decode = decode(huffmanCodes, bytes);
        StringBuilder sb = new StringBuilder();
        for(byte de:decode){
            sb.append((char)de);
        }
        System.out.println(sb.toString());*/

        //测试压缩
        /*String src = "C:\\Users\\11585\\Music\\src.bmp";
        String det = "C:\\Users\\11585\\Music\\det.zip";
        zipFile(src,det);
        System.out.println("压缩文件完成");*/
        //测试解压缩

        String zip = "C:\\Users\\11585\\Music\\det.zip";
        String dst = "C:\\Users\\11585\\Music\\src2.bmp";
        decodeFile(zip,dst);
        System.out.println("解压文件完成");
    }

    public static void decodeFile(String zip,String dst){
        InputStream in = null;
        ObjectInputStream oin = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(zip);
            oin = new ObjectInputStream(in);
            byte[] zipCodes = (byte[])oin.readObject();
            Map<Byte,String> huffcodes = (Map<Byte, String>) oin.readObject();
            byte[] decode = decode(huffcodes, zipCodes);
            out = new FileOutputStream(dst);
            out.write(decode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                oin.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipFile(String src,String det){
        FileInputStream in = null;
        FileOutputStream out = null;
        ObjectOutputStream oout = null;
        try {
            in = new FileInputStream(src);
            byte[] b = new byte[in.available()];
            in.read(b);
            byte[] detBytes = huffmanZip(b);
            out = new FileOutputStream(det);
            oout = new ObjectOutputStream(out);
            oout.writeObject(detBytes);
            oout.writeObject(huffmanCodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                //oout一定要关闭在out之前，不然会导致结束符不正确而无法解压
                oout.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //解压
    //将压缩的字符数组转成对应的二进制字符串
    //逐个扫描字符串，根据哈夫曼编码解码，放到一个list中，然后将list转成byte[]数组返回
    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] bytes){
        //将压缩的字符数组转成对应的二进制字符串
        boolean flag;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            flag = (i!=bytes.length-1);
            byte b = bytes[i];
            String s = byteToBitString(flag, b);
            sb.append(s);
        }
        System.out.println(sb.toString());

        //将map中的键值对交换，以便根据编码查对应的字符
        HashMap<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //逐个扫描二进制字符串，查询是否有对应的字符，有就添加大list中，没有就index++后再判断
        ArrayList<Byte> list = new ArrayList<>();
        for(int i=0;i<sb.length();){
            int count = 1;
            boolean none = true;
            Byte bb = null;
            while(none&&i+count<=sb.length()){
                String substring = sb.substring(i,count+i);
                bb = map.get(substring);
                if(bb==null){
                    count++;
                }else{
                    none = false;
                }
            }
            i=i+count;
            list.add(bb);
        }
        //将list转成字符数组，然后返回

        //System.out.println(list);
        byte[] res = new byte[list.size()];
        for(int i=0;i<res.length;i++){
            res[i] = list.get(i);
        }
        return res;
    }

    //将一个byte转成一个二进制字符串，int底层是以补码形式存储的
    // 当转成字符串后，负数有32位，需要截取后面8位，整数只有后面有值的几位，需要补齐
    //最后一位无论是整数还是负数，都不需要补齐和截取，（以原码形式存储？）
    //flag代表是否需要补高位，如果是最后一位就不需要补高位
    public static String byteToBitString(boolean flag,byte b){
        int temp = b;
        //不是最后一位，同一与256与以下，对整数有补高位的作用，对负数没有作用
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length() - 8);
        }else{
            return str;
        }
    }












    //将传入的字符数组按照哈夫曼编码拼接成一个字符串,并将字符串按照8位，转换成字符，并返回一个压缩后的字符串数组
    public static byte[] huffmanZip(byte[] strBytes) {
        //将原始的byte数组统计成Node的list
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte b : strBytes) {
            Integer num = map.get(b);
            if (num == null) {
                map.put(b, 1);
            } else {
                map.put(b, num + 1);
            }
        }
        List<Node> list = new ArrayList<>();
        Set<Byte> bytes = map.keySet();
        for (Byte b : bytes) {
            list.add(new Node(b, map.get(b)));
        }

        //将Node的list节点按照出现频率创建哈夫曼树
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.fre + rightNode.fre);
            parent.left = leftNode;
            parent.right = rightNode;
            list.add(parent);
            list.remove(leftNode);
            list.remove(rightNode);
        }
        Node root = list.get(0);
        getCodes(root);

        StringBuilder sb = new StringBuilder();
        for (byte b : strBytes) {
            sb.append(huffmanCodes.get(b));
        }
        int len = (sb.length() + 7) / 8;
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i+=8) {
            String substring;
            if (i + 8 > sb.length()) {
                substring = sb.substring(i);
            } else {
                substring = sb.substring(i, i + 8);
            }
            byte b = (byte) Integer.parseInt(substring, 2);
            huffmanCodeBytes[index] = b;
            index++;
        }
        return huffmanCodeBytes;
    }


    //保存赫弗曼编码表
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    //node代表将此节点下的叶子节点编码
    //code代表向左走编码追加一个0，向右走编码追加一个1
    //sb代表一个叶子节点的编码
    public static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) {
            if (node.value == null) {
                //说明不是叶子节点
                getCodes(node.left, "0", sb2);
                getCodes(node.right, "1", sb2);
            } else {
                huffmanCodes.put(node.value, sb2.toString());
            }
        }
    }

    //根据哈夫曼树进行编码
    public static Map<Byte, String> getCodes(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("传入的节点为空");
        }
        getCodes(node.left, "0", stringBuilder);
        getCodes(node.right, "1", stringBuilder);
        return huffmanCodes;
    }

    public static void preOrder(Node node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }
    }

    //将Node的list节点按照出现频率创建哈夫曼树
    /*private static Node getBytesHuffmanTree(List<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.fre + rightNode.fre);
            parent.left = leftNode;
            parent.right = rightNode;
            list.add(parent);
            list.remove(leftNode);
            list.remove(rightNode);
        }
        return list.get(0);
    }*/

    //将原始的byte数组统计成Node的list
   /* private static List<Node> getBytesMap(byte[] strBytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte b : strBytes) {
            Integer num = map.get(b);
            if (num == null) {
                map.put(b, 1);
            } else {
                map.put(b, num + 1);
            }
        }
        List<Node> list = new ArrayList<>();
        Set<Byte> bytes = map.keySet();
        for (Byte b : bytes) {
            list.add(new Node(b, map.get(b)));
        }
        return list;
    }*/


}

class Node implements Comparable<Node> {
    Byte value;
    Integer fre;
    Node left;
    Node right;

    public Node(byte value, int fre) {
        this.value = value;
        this.fre = fre;
    }

    public Node(int fre) {
        this.fre = fre;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", fre=" + fre +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.fre - o.fre;
    }
}
