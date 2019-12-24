package princess.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * ai和全文检索
 */
public class AiEsUtil {

    public static void main(String[] args) {

    }

    /**
     * 递归遍历所有文件
     * @param file
     */
    public static void getAllTxt(File file, Operation operation) {
        File[] files=file.listFiles();
        StringBuilder result = new StringBuilder();
        for(File a :files) {
            String absolutePath = a.getAbsolutePath();
            if(a.isDirectory()) {
                getAllTxt(a, operation);
            }else{
                readContent( a,  absolutePath,  result,  operation);
            }
        }
    }

    /**
     * 读取内容
     * @param a
     * @param absolutePath
     * @param result
     * @param operation
     */
    public static void readContent(File a, String absolutePath, StringBuilder result, Operation operation){
        InputStreamReader fReader = null;
        BufferedReader br = null;
        try{
            fReader = new InputStreamReader(new FileInputStream(absolutePath),"GB2312");
            br = new BufferedReader(fReader);
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s).append("<br>");
            }
            operation.toDo(result.toString());
        }catch (Exception e){
            System.out.println("fileName = [" + a.getName() + "]");
            throw new RuntimeException("递归遍历文件出错！");
        }finally {
            try {
                br.close();
                fReader.close();
            }catch (Exception e){}
        }
    }

    @FunctionalInterface
    public interface Operation{
        public <T> void toDo(T t);
    }


}
