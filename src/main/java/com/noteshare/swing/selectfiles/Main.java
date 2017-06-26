package com.noteshare.swing.selectfiles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @ClassName			: Main 
 * @Description			: 程序入口类
 * @author 				： NoteShare 
 * @date 				： 2016年12月11日 下午10:15:10
 */
public class Main {
	private static String selectFilePath = "";
	private static String selectFileName = "";
	public static void main(String[] args) throws Exception {
		/**====================================窗口的绘制=============================start==*/
		// 创建jframe
		JFrame frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle("项目连接关闭情况检查程序");
		frame.setBounds(100, 100, 800, 500);
		// 创建jpanel
		JPanel mainJpanel = new JPanel();
		// 采用绝对布局
		mainJpanel.setLayout(new BorderLayout());
		frame.setContentPane(mainJpanel);
		//==========================窗口头部表单区=================start
		//==========================窗口中部说明区=================start
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		mainJpanel.add(centerPanel, BorderLayout.CENTER);
		//==========================窗口中部说明区=================end
		//==========================窗口底部按钮区==================start
		//创建按钮面板
		JPanel soutchJpanel = new JPanel();
		soutchJpanel.setLayout(new FlowLayout());
		// 创建开始按钮
		final JButton startButton = new JButton("开始扫描");
		startButton.setSize(60, 30);
		startButton.setVisible(true);
		soutchJpanel.add(startButton);
		
		//文件选择按钮
		final JButton selectButton = new JButton("选择要扫描的文件或文件夹");
		selectButton.setSize(120,30);
		selectButton.setVisible(true);
		soutchJpanel.add(selectButton);
		//这个要放在后面，会触发所有可见组件的 paint方法
		mainJpanel.add(soutchJpanel, BorderLayout.PAGE_END);
		frame.setVisible(true);
		/**====================================窗口的绘制=============================end==*/
		/**===========================================事件定义==========================start=======*/
		/**
		 * 单击执行按钮开始获取数据
		 */
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PrintWriter pw = null;
				try {
					List<String> questionFilePath = new ArrayList<String>();
					//单击开始扫描
					new CheckConn().scanFile(new File(selectFilePath),questionFilePath);
					//输出错误文件
					File file = new File(Constant.ERRORFILEPATH);
					if(!file.exists()){
						file.mkdirs();
					}
					File logFile = new File(Constant.ERRORLOGFILEPATH);
					FileOutputStream fos = new FileOutputStream(logFile,true);
					pw = new PrintWriter(fos);
					for (String filepath : questionFilePath) {
						//记录错误文件路径
						pw.write(filepath + "\n");
						//拷贝错误文件
						String filePathTemp = filepath.substring(filepath.indexOf(selectFileName + "\\"));
						File filetemp = new File(Constant.ERRORFILEPATH + filePathTemp);
						FileUtil.copyFile(new File(filepath), filetemp);
					}
					JOptionPane.showMessageDialog(null, "扫描完成请查看" + Constant.ERRORFILEPATH + "目录下的文件", "提示", JOptionPane.PLAIN_MESSAGE);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}finally {
					if(null != pw){
						pw.close();
					}
				}
			}
		});
		/**
		 * 单击执行按钮开始获取数据
		 */
		selectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("请选择要扫描的文件夹"); 
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = jfc.showOpenDialog(frame);
				File file = null;  
		        if(JFileChooser.APPROVE_OPTION == result) {
		        	file = jfc.getSelectedFile();
		        	selectFilePath = file.getAbsolutePath();
		        	selectFileName = file.getName();
		        }
			}
		});
		
		/**
		 * 窗口关闭事件监听
		 */
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
            	//销毁定时器
                System.exit(0);	
            }
        });
		/**===========================================事件定义==========================end=======*/
	}
}
