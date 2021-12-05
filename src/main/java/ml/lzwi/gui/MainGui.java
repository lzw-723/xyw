package ml.lzwi.gui;

import java.awt.Color;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ml.lzwi.XywHelper;
import ml.lzwi.util.XywUtil.Action;
import ml.lzwi.util.XywUtil.Result;

/**
 * Gui入口
 * 
 * @author lzw-723
 */
public class MainGui {
    // public static void main(String[] args) {
    // TODO: 使用Swing实现
    // }

    /**
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     * 
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private static void createAndShowGUI() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        // 确保一个漂亮的外观风格
        // JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // 创建及设置窗口
        JFrame frame = new JFrame("xyw");
        frame.setSize(280, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button1 = new JButton("开始");
        panel.add(button1);
        JButton button2 = new JButton("停止");
        panel.add(button2);
        JButton button3 = new JButton("配置");
        button3.setEnabled(false);
        panel.add(button3);
        JButton button4 = new JButton("退出");
        panel.add(button4);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        XywHelper xywHelper = XywHelper.getInstance();
        xywHelper.init();
        xywHelper.setCallback(new XywHelper.Callback() {

            @Override
            public void onStart() {
                // TODO Auto-generated method stub
                textArea.append("\n开始");
            }

            @Override
            public void onStop() {
                // TODO Auto-generated method stub
                textArea.append("\n停止");
            }

            @Override
            public void onAction(Action action, Result result) {
                // TODO Auto-generated method stub
                textArea.append("\n运行中");
                JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
                scrollBar.setValue(scrollBar.getMaximum() + 1);
            }

        });
        button1.addActionListener((actionEvent) -> {
            xywHelper.start();
        });
        button2.addActionListener((actionEvent) -> {
            xywHelper.stop();
        });
        button4.addActionListener((actionEvent) -> {
            System.exit(0);
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
