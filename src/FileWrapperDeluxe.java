import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class FileWrapperDeluxe extends JPanel implements ActionListener {

	JButton selectFolder;

	JFileChooser chooser;
	String choosertitle;

	public FileWrapperDeluxe () {
		selectFolder = new JButton("");
		selectFolder.addActionListener(this);
		selectFolder.setBounds(0, 0, 100, 100);
		add(selectFolder);
	}

	public static void main (final String[] args) {

		final JFrame f = new JFrame("Test");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setBounds(200, 200, 1000, 500);
		FileWrapperDeluxe panel = new FileWrapperDeluxe();

		f.addWindowListener(
				new WindowAdapter() {
					public void windowClosing (WindowEvent e) {
						System.exit(0);
					}
				}
		);

		f.getContentPane().add(panel, "Center");
		f.setSize(panel.getPreferredSize());


		final File file = new File("D:\\");
		final MyFile mf = new MyFile(file);
		final TreeModel model = new FileTreeModel(mf);
		final JTree tree = new JTree(model);
		tree.addTreeSelectionListener(e -> {
			m1(e);
		});
		tree.setEditable(true);
		f.add(new JScrollPane(tree));


		f.setVisible(true);
	}

	public static void m1(TreeSelectionEvent e){
		e.getNewLeadSelectionPath();
	}


	public void actionPerformed (ActionEvent e) {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			System.out.println(chooser.getSelectedFile());
		} else {
			System.out.println("No Selection ");
		}
	}

	public Dimension getPreferredSize () {
		return new Dimension(500, 400);
	}
}
