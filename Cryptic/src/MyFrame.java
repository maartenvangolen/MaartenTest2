import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {

	private InLezen iL;
	private UitSchrijven uS;
	private JButton bijwerken, coderen, visuals, secretButton;
	private JTextField tfIn, tfOut;
	private JLabel visual, secretLabel;
	private ArrayList<String> input = new ArrayList<String>();
	private String output = "";

	public MyFrame() {
		setLayout(new FlowLayout());
		bijwerken = new JButton("Bijwerken");
		add(bijwerken);
		bijwerken.addActionListener(this);

		coderen = new JButton("Coderen");
		add(coderen);
		coderen.addActionListener(this);

		tfIn = new JTextField("", 15);
		add(tfIn);

		tfOut = new JTextField("DIT IS OUTPUT", 15);
		add(tfOut);
		tfOut.setEditable(false);

		visual = new JLabel("Want visuals?");
		add(visual);

		visuals = new JButton("[OFF]-on");
		add(visuals);
		visuals.addActionListener(this);

		secretLabel = new JLabel("Want secrets?");
		add(secretLabel);

		secretButton = new JButton("[OFF]-on");
		add(secretButton);
		secretButton.addActionListener(this);

		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) {
		boolean active = visuals.getText().equals("off-[ON]");
		boolean isHidden = secretButton.getText().equals("[OFF]-on");
		if (event.getSource() == bijwerken) {
			iL.bijwerken(input, output, active);
			if (active) {
				System.out.println("\nXXXXXXXXXXXXXXXFINISHEDXXXXXXXXXXXXXX");
			} else {
				System.out.println("\ndone");
			}
		}

		if (event.getSource() == coderen && !isHidden) {
			uS.setFiles(tfIn.getText(), output);
			try {
				tfOut.setText("");
				if (active) {
					System.out.println("\n*the mice bring you secrets*");
				}
				uS.translate(active);
				if (active) {
					System.out.println("\n*They are done talking!*");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == coderen && isHidden) {
			uS.setFiles(tfIn.getText(), output);
			try {
				tfOut.setText("");
				if (active) {
					System.out.println("\n*hamsters are running at full speed*");
				}
				uS.coderen(active);
				if (active) {
					System.out.println("\n*They are done!*");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == visuals) {
			if (visuals.getText().equals("[OFF]-on")) {
				visuals.setText("off-[ON]");
			} else {
				visuals.setText("[OFF]-on");
			}
		}

		if (event.getSource() == secretButton) {
			if (secretButton.getText().equals("[OFF]-on")) {
				secretButton.setText("off-[ON]");
			} else {
				secretButton.setText("[OFF]-on");
			}
		}

	}

	public void setTextField(String s) {
		tfOut.setText(s);
	}

	public String getTextField() {
		return tfOut.getText();
	}

	public void setInLezen(InLezen in) {
		iL = in;
	}

	public void setUitSchrijven(UitSchrijven ui) {
		uS = ui;
	}

	public void setFiles(String inputs, String outputs) {
		input.add(inputs);
		output = outputs;
		tfOut.setText(output);
	}

	public String getOutput() {
		return output;
	}

}
