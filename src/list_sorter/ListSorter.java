package list_sorter;

import javax.swing.JFrame;

public class ListSorter extends JFrame {
	public ListSorter() {
		setTitle("Alphabetical List Sorter");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel();
		getContentPane().add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		ListSorter window = new ListSorter();
	}

}
