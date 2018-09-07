package Controllers;

import Common.LogHelper;
import Common.XMLHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.hibernate.jpa.internal.util.XmlHelper;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.UUID;

public class Dom4jController {

    public static void main(String[] args) {
        int Number = 6000;
        int Count = 0;
        String[] arr = {"？", "？", "!", "!", "$", "%", "^", "*", " "};
        for (int i = 0; i < arr.length; i++) {
            Number += 1;
            Count += 1;
            CreateXml(arr[i], Number, Count);
        }

    }


    public static void CreateXml(String fuhao, int bianhao, int Count) {

        String xmlPath = "D:\\xml\\oldfile\\NISF0000000000001-20180826-00000001.XML";
        try {
            //文件是否存在
            File file = new File(xmlPath);
            if (!file.exists()) {
                throw new Exception("找不到xml文件：" + xmlPath);
            }
            //解析
            SAXReader reader = new SAXReader(false);
            Document doc = reader.read(new FileInputStream(file));
            Element root = doc.getRootElement();
            List<Node> firstnodes = doc.selectNodes("/CHTR/RBIF");
            for (Node node : firstnodes) {

                String tstn = node.selectSingleNode("RICD").getText();
                if (tstn != null && tstn.length() > 0) {
                    node.selectSingleNode("RICD").setText(fuhao);
                }
            }
            UUID guid = UUID.randomUUID();
            String outputpath = "D:\\xml\\newfile" + guid.toString() + ".XML";
            XMLHelper.writeToXml(outputpath,doc);
        } catch (Exception e) {
            LogHelper.Error(e.getMessage(), e);
        }

    }
}
