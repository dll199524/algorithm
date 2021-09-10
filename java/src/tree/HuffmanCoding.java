package tree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCoding {
    public static void main(String[] args) throws IOException {
        String content = "i like like java do you like a java";
        byte[] contentByte = content.getBytes();
        List<HuffmanCodingNode> nodes = getweights(contentByte);

        System.out.println("生成哈夫曼树");
        HuffmanCodingNode root = createHuffmanTree(nodes);
        root.preOrder();

        System.out.println("生成哈夫曼编码表");
        Map<Byte, String> huffmanCode = getCodes(root);
        System.out.println("生成的哈夫曼编码表 = " + huffmanCode);

        byte[] huffmanBytes = zip(contentByte, huffmanCode);
        System.out.println("huffmanBytes = " + Arrays.toString(huffmanBytes));

        System.out.println("原来的字符串" + content);
        byte[] sourceBytes = decode(huffmanCode, huffmanBytes);
        System.out.println("解码后的字符串" + new String(sourceBytes));

        String inputFile = "C:\\Users\\ASUS\\Desktop\\src.jpg";
        String outFile = "C:\\Users\\ASUS\\Desktop\\src.zip";
        zipFile(inputFile, outFile);
        System.out.println("压缩成功");


        String newInputFile = "C:\\Users\\ASUS\\Desktop\\src.zip";
        String newOutputFile = "C:\\Users\\ASUS\\Desktop\\newsrc.jpg";
        unzipFile(newInputFile, newOutputFile);
        System.out.println("解压成功");
        

    }

    public static byte[] huffmanZip(byte[] bytes) {
        List<HuffmanCodingNode> nodes = getweights(bytes);
        HuffmanCodingNode root = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(root);
        byte[] huffmanBytes = zip(bytes, huffmanCodes);
        return huffmanBytes;
    }


    //获取字节数组每个数值对应的权重值
    private static List<HuffmanCodingNode> getweights(byte[] bytes) {
        List<HuffmanCodingNode> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) counts.put(b, 1);
            else counts.put(b, count + 1);
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanCodingNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    } 

    //创建哈夫曼树
    public static HuffmanCodingNode createHuffmanTree(List<HuffmanCodingNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanCodingNode leftNode = nodes.get(0);
            HuffmanCodingNode rightNode = nodes.get(1);
            HuffmanCodingNode parent = new HuffmanCodingNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //生成哈夫曼编码表
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    private static Map<Byte, String> getCodes(HuffmanCodingNode root) {
        if (root == null) return null;
        getCodes(root.left, "0", new StringBuilder());
        getCodes(root.right, "1", new StringBuilder());
        return huffmanCodes;
    }

    private static void getCodes(HuffmanCodingNode node, String code, StringBuilder stringBuilder) {
        StringBuilder curCode = new StringBuilder(stringBuilder);
        curCode.append(code);
        if (node != null) {
            if (node.date == null) {
                getCodes(node.left, "0", curCode);
                getCodes(node.right, "1", curCode);
            } else {
                huffmanCodes.put(node.date, curCode.toString());
            }
        }
    }

    //生成哈弗曼编码
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len;
        byte lastbyte = (byte) (stringBuilder.length() % 8);
        if (lastbyte == 0) len = stringBuilder.length() / 8;
        else {
            len = stringBuilder.length() / 8 + 1;
            for (int i = lastbyte; i < 8; i++) {
                stringBuilder.append('0');
            }
        }
        byte[] huffmanBytes = new byte[len + 1];
        huffmanBytes[len] = lastbyte;
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            strByte = stringBuilder.substring(i, i + 8);
            huffmanBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanBytes;
    }


    //哈夫曼解码
    public static String byteToBitString(byte b) {
        int temp = b;
        temp |= 0x100;
        String bitStr = Integer.toBinaryString(temp);
        return  bitStr.substring(bitStr.length() - 8);
    }

    private static byte[] decode(Map<Byte, String> huffmancodes, byte[] huffmanBytes) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length - 1; i++) {
            byte b = huffmanBytes[i];
            String strToAppend = byteToBitString(b);
            boolean isLastByte = (i == huffmanBytes.length - 2);
            if (isLastByte) {
                byte validByte = huffmanBytes[huffmanBytes.length - 1];
                strToAppend = strToAppend.substring(0, validByte);
            }
            stringBuilder.append(strToAppend);
        }

        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmancodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }


        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                }
                else flag = false;
            }
            list.add(b);
            i += count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //文件压缩
    public static void zipFile(String inputFile, String outFile) throws IOException {
        //输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //输入流
        InputStream is = null;
        try {
            is = new FileInputStream(inputFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(outFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            oos.close();
            os.close();
        }

    }

    //文件解压
    public static void unzipFile(String inputFile, String outputFile) throws IOException{
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(inputFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(outputFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
            ois.close();
            is.close();
        }

    }

}




class HuffmanCodingNode implements Comparable<HuffmanCodingNode> {
    Byte date;
    int weight;
    HuffmanCodingNode left;
    HuffmanCodingNode right;
    public HuffmanCodingNode(Byte data, int weight) {
        this.date = data;
        this.weight = weight;
    }
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }
    @Override
    public String toString() {
        return "HuffmanCodingNode [date=" + date + ", weight=" + weight + "]";
    }
    @Override
    public int compareTo(HuffmanCodingNode o) {
        return this.weight - o.weight;
    } 
}
