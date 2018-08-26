package Controllers;


import Common.LogHelper;
import org.dom4j.io.OutputFormat;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

public class XmlController {

    public static void main(String[] args) {

        int Number = 6000;
        int Count = 0;
        String[] arr = {"？", "？", "!", "!", "$", "%", "^", "*", " "};
        for (int i = 0; i < arr.length; i++) {
            Number += 1;
            Count += 1;
            CreateXml1(arr[i], Number, Count);
            CreateXml2(arr[i], Number, Count);
            CreateXml3(arr[i], Number, Count);
        }

    }


//    public  static  void CreateXml(String fuhao,int bianhao) {
//
//
//        String xmlPath="J:\\xml文件\\CBHC0000000000001-20180828-00000001.XML";
//        try {
//            //文件是否存在
//            File file = new File(xmlPath);
//            if(!file.exists()){
//                throw new Exception("找不到xml文件："+xmlPath);
//            }
//            //解析
//            SAXReader reader = new SAXReader(false);
//            Document doc = reader.read(new FileInputStream(file),"utf-8");
//            Element root = doc.getRootElement();
//            List<Node> firstnodes = doc.selectNodes("/CHTR/RBIF" );
//            for (Node node : firstnodes) {
//
//                String  tstn= node.selectSingleNode("TSTN").getText();
//                if (tstn !=null && tstn.length()>0) {
//                    node.selectSingleNode("TSTN").setText(fuhao);
//                }
//            }
//
//            List<Node> secondnodes = doc.selectNodes("/CHTR/TSDTs/TSDT" );
//            for (Node node : secondnodes) {
//
//                String  ocnm= node.selectSingleNode("OCNM").getText();
//                if (ocnm !=null && ocnm.length()>0) {
//
//                    node.selectSingleNode("OCNM").setText(Integer.toString(bianhao));
//                }
//            }
//
//            UUID guid=UUID.randomUUID();
//            String outputpath="J:\\createxml文件\\"+String.valueOf(guid)+".XML";
//        } catch (Exception e) {
//            LogHelper.Error(e.getMessage(),e);
//        }
//
//    }

    //格式1
    public static void CreateXml1(String fuhao, int bianhao, int Count) {

        Document document = null;
        String xmlPath = "F:\\xml\\old\\CBHC0000000000001-20180828-00000001.XML";
        try {
            //文件是否存在
            File file = new File(xmlPath);
            if (!file.exists()) {
                throw new Exception("找不到xml文件：" + xmlPath);
            }
            //解析
            SAXBuilder saxBuilder = new SAXBuilder();
            document = saxBuilder.build(file);
            Element rootElement = document.getRootElement();

            List<Element> firstlist = rootElement.getChild("RBIF").getChildren();
            for (int temp = 0; temp < firstlist.size(); temp++) {
                Element carElement = firstlist.get(temp);
                if ("TSTN".equals(carElement.getName())) {
                    String tstn = carElement.getText();
                    if (tstn != null && tstn.length() > 0) {
                        String tstnContent = (carElement.getText() + fuhao).trim();
                        carElement.setText(tstnContent);
                    }
                }
            }

            List<Element> secondlist = rootElement.getChild("TSDTs").getChild("TSDT").getChildren();
            for (int temp = 0; temp < firstlist.size(); temp++) {

                Element carElement = secondlist.get(temp);
                if ("OCNM".equals(carElement.getName())) {
                    String ocnm = carElement.getText();
                    if (ocnm != null && ocnm.length() > 0) {
                        carElement.setText(Integer.toString(bianhao));
                    }
                }

            }
            String url = "F:\\xml\\new1\\CBHC0000000000001-20180828-0000000" + Count + ".xml";
            String Path = "F:\\xml\\new1";
            XMLOutputter XMLOut = new XMLOutputter();
            File filePath = new File(Path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(url);
            XMLOut.output(document, fos);
            fos.close();

        } catch (Exception e) {
            LogHelper.Error(e.getMessage(), e);
        }

    }

    //格式2
    public static void CreateXml2(String fuhao, int bianhao, int Count) {

        Document document = null;
        String xmlPath = "F:\\xml\\old\\CBHC0000000000001-20180828-00000001.XML";
        try {
            //文件是否存在
            File file = new File(xmlPath);
            if (!file.exists()) {
                throw new Exception("找不到xml文件：" + xmlPath);
            }
            //解析
            SAXBuilder saxBuilder = new SAXBuilder();
            document = saxBuilder.build(file);
            Element rootElement = document.getRootElement();

            List<Element> firstlist = rootElement.getChild("RBIF").getChildren();
            for (int temp = 0; temp < firstlist.size(); temp++) {
                Element carElement = firstlist.get(temp);
                if ("MIRS".equals(carElement.getName())) {
                    String mirs = carElement.getText();
                    if (mirs != null && mirs.length() > 0) {
                        String mirsContent = (carElement.getText() + fuhao).trim();
                        carElement.setText(mirsContent);
                    }
                }
            }

            List<Element> secondlist = rootElement.getChild("TSDTs").getChild("TSDT").getChildren();
            for (int temp = 0; temp < firstlist.size(); temp++) {

                Element carElement = secondlist.get(temp);
                if ("OCNM".equals(carElement.getName())) {
                    String ocnm = carElement.getText();
                    if (ocnm != null && ocnm.length() > 0) {
                        carElement.setText(Integer.toString(bianhao));
                    }
                }

            }
            String url = "F:\\xml\\new2\\CBHC0000000000001-20180828-0000000" + Count + ".xml";
            String Path = "F:\\xml\\new2";
            XMLOutputter XMLOut = new XMLOutputter();
            File filePath = new File(Path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(url);
            XMLOut.output(document, fos);
            fos.close();

        } catch (Exception e) {
            LogHelper.Error(e.getMessage(), e);
        }

    }

    //格式3
    public static void CreateXml3(String fuhao, int bianhao, int Count) {

        Document document = null;
        String xmlPath = "F:\\xml\\old\\CBHC0000000000001-20180828-00000001.XML";
        try {
            //文件是否存在
            File file = new File(xmlPath);
            if (!file.exists()) {
                throw new Exception("找不到xml文件：" + xmlPath);
            }
            //解析
            SAXBuilder saxBuilder = new SAXBuilder();
            document = saxBuilder.build(file);
            Element rootElement = document.getRootElement();

            List<Element> firstlist = rootElement.getChild("RBIF").getChildren();
            for (int temp = 0; temp < firstlist.size(); temp++) {
                Element carElement = firstlist.get(temp);
                String str = carElement.getText();
                str = str.replace(str.charAt(str.length() - 1) + "", fuhao);
                carElement.setText(str);
            }


            List<Element> secondlist = rootElement.getChild("TSDTs").getChild("TSDT").getChildren();
            for (int temp = 0; temp < secondlist.size(); temp++) {
                Element carElement = secondlist.get(temp);
                List<Element> thirdlist = carElement.getChildren();
                if (thirdlist.size() > 0) {
                    for (int i=0;i<thirdlist.size();i++) {

                        Element element = thirdlist.get(i);
                        String str = element.getText();
                        str = str.replace(str.charAt(str.length() - 1) + "", fuhao);
                        element.setText(str);
                    }
                }
                else {
                    String str = carElement.getText();
                    str = str.replace(str.charAt(str.length() - 1) + "", fuhao);
                    carElement.setText(str);
                }

            }
            String url = "F:\\xml\\new3\\CBHC0000000000001-20180828-0000000" + Count + ".xml";
            String Path = "F:\\xml\\new3";
            XMLOutputter XMLOut = new XMLOutputter();
            File filePath = new File(Path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(url);
            XMLOut.output(document, fos);
            fos.close();

        } catch (Exception e) {
            LogHelper.Error(e.getMessage(), e);
        }

    }


}
