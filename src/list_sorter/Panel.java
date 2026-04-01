package list_sorter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Panel extends JPanel implements ActionListener {
	JButton SelectFile;
	JButton SaveFile;
	JButton Sort;
	JTextArea textArea;
	JScrollPane scrollArea;
	ArrayList<String> list = new ArrayList<String>();
	File selectedFile = null;

	public Panel() {
		BorderLayout border = new BorderLayout(10, 10);
		GridLayout buttonLayout = new GridLayout(1, 3);
		this.setLayout(border);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);

		SelectFile = new JButton("Select File");
		Sort = new JButton("Sort");
		SaveFile = new JButton("Save File");

		SelectFile.addActionListener(this);
		Sort.addActionListener(this);
		SaveFile.addActionListener(this);

		textArea = new JTextArea("List contents will be displayed below:\n");
		textArea.setEditable(false);
		scrollArea = new JScrollPane(textArea);

		buttonPanel.add(SelectFile);
		buttonPanel.add(Sort);
		buttonPanel.add(SaveFile);

		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(scrollArea, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Select File")) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();

				textArea.setText("Selected file: " + selectedFile.getAbsolutePath() + "\n");
			}

			// Read the file
			Scanner fileReader = null;
			try {
				fileReader = new Scanner(selectedFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Clear list
			list.clear();

			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				list.add(line);
				// write to textarea
				textArea.append(line + "\n");

			}
			fileReader.close();

		}

		if (e.getActionCommand().equals("Sort")) {
			list.sort(null);
			// update text area with new sorted list
			textArea.setText("The list has been sorted:\n");

			ListIterator<String> namesIterator = list.listIterator();
			while (namesIterator.hasNext()) {
				textArea.append(namesIterator.next() + "\n");
			}
		}

		if (e.getActionCommand().equals("Save File")) {
			// Get the selected file's name and path
			String pathName = selectedFile.getParent();
			String fileName = selectedFile.getName();

			// Add the new file's name to the end of the path
			String newFilePath = pathName + "\\Sorted " + fileName;

			// Create the new file and write the sorted list to it
			File newFile = new File(newFilePath);
			PrintWriter fileWriter = null;
			try {
				fileWriter = new PrintWriter(newFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			ListIterator<String> namesIterator = list.listIterator();

			while (namesIterator.hasNext()) {
				fileWriter.println(namesIterator.next());
			}
			fileWriter.close();
			textArea.append("\nThe file has been saved to: " + newFilePath);

		}
	}

}
