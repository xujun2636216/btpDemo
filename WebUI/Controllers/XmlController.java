package Controllers;


import Common.LogHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.UUID;

public class XmlController {

    public static void main(String[] args) {

        int Number=6000;
        String[] arr={"？","？","!","!","$","%","^","*"};
        for (int i=0;i<arr.length;i++) {
            Number+=1;
            CreateXml(arr[i],Number);
        }

    }


    public  static  void CreateXml(String fuhao,int bianhao) {


        String xmlPath="J:\\xml文件\\CBHC0000000000001-20180828-00000001.XML";
        try {
            //文件是否存在
            File file = new File(xmlPath);
            if(!file.exists()){
                throw new Exception("找不到xml文件："+xmlPath);
            }
            //解析
            SAXReader reader = new SAXReader(false);
            Document doc = reader.read(new FileInputStream(file),"utf-8");
            Element root = doc.getRootElement();
            List<Node> firstnodes = doc.selectNodes("/CHTR/RBIF" );
            for (Node node : firstnodes) {

                String  tstn= node.selectSingleNode("TSTN").getText();
                if (tstn !=null && tstn.length()>0) {
                    node.selectSingleNode("TSTN").setText(fuhao);
                }
            }

            List<Node> secondnodes = doc.selectNodes("/CHTR/TSDTs/TSDT" );
            for (Node node : secondnodes) {

                String  ocnm= node.selectSingleNode("OCNM").getText();
                if (ocnm !=null && ocnm.length()>0) {

                    node.selectSingleNode("OCNM").setText(Integer.toString(bianhao));
                }
            }

            UUID guid=UUID.randomUUID();
            String outputpath="J:\\createxml文件\\"+String.valueOf(guid)+".XML";
        } catch (Exception e) {
            LogHelper.Error(e.getMessage(),e);
        }

    }


}
