/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoxml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ueda
 */
public class XMLAPIHandle {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting getting cml content.");
        String url = "https://youtube-api-challenger.appspot.com/xml/members";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        InputStreamReader isr = new InputStreamReader(con.getInputStream());
        BufferedReader br = new BufferedReader(isr);

        DocumentBuilderFactory fatory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fatory.newDocumentBuilder();
        Document doc = builder.parse(con.getInputStream());

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("Member");

        //take in table...
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("username");
        tableModel.addColumn("fullname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Password");
        tableModel.addColumn("Birthday");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Avatar");
        tableModel.addColumn("Status");

        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("------------------------------");
            Node node = nodeList.item(i);
            Element element = (Element) node;
            System.out.println("Id: " + element.getAttribute("id"));
            System.out.println("User name: " + element.getElementsByTagName("UserName").item(0).getTextContent());
            System.out.println("Full Name: " + element.getElementsByTagName("FullName").item(0).getTextContent());
            System.out.println("Email : " + element.getElementsByTagName("Email").item(0).getTextContent());
            System.out.println("Password: " + element.getElementsByTagName("Password").item(0).getTextContent());
            System.out.println("Birthday: " + element.getElementsByTagName("Birthday").item(0).getTextContent());
            System.out.println("Gender: " + element.getElementsByTagName("Gender").item(0).getTextContent());
            System.out.println("Avatar: " + element.getElementsByTagName("Avatar").item(0).getTextContent());
            System.out.println("Status: " + element.getElementsByTagName("Status").item(0).getTextContent());

            Object str[] = new Object[]{
                element.getAttribute("id"),
                element.getElementsByTagName("UserName").item(0).getTextContent(),
                element.getElementsByTagName("FullName").item(0).getTextContent(),
                element.getElementsByTagName("Email").item(0).getTextContent(),
                element.getElementsByTagName("Password").item(0).getTextContent(),
                element.getElementsByTagName("Birthday").item(0).getTextContent(),
                element.getElementsByTagName("Gender").item(0).getTextContent(),
                element.getElementsByTagName("Avatar").item(0).getTextContent(),
                element.getElementsByTagName("Status").item(0).getTextContent()
            };

            tableModel.addRow(str);
        }
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JFrame frame = new JFrame("List");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        scrollPane.setBounds(25, 50, 750, 300);
        
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
