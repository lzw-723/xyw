package ml.lzwi.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Gui入口
 * @author lzw-723
 */
public class MainGui {
    // public static void main(String[] args) {
        // TODO: 使用Javafx实现
    // }

    /**
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    private static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        // JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("xyw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 添加 "Hello World" 标签
        JLabel label = new JLabel("<html><b><font size=+2>"
        + "<center>Hello!<br><i>Press me now!</html>");
        // label.setForeground(Color.GREEN);
        panel.add(label);

        JTextField field = new JTextField("text");
        panel.add(field);

        frame.getContentPane().add(panel);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
