/**
 * The DisplayView class is the main user interface for when
 * the webchat is active. On the left side of the frame is the user's
 * local webcam. On the right is the incoming stream of the connected
 * webcam. The interface includes radio buttons that allow the user to 
 * choose the level of compression to use when sending video. It also
 * allows the user to choose between broadcasting in black and white
 * or color. 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class DisplayView extends JFrame {
	JPanel panel = new JPanel();
	JPanel videoComponents = new JPanel();
	ButtonGroup compressionGroup = new ButtonGroup();
	ButtonGroup colorGroup = new ButtonGroup();
	JRadioButton colorButton = new JRadioButton("Color");
	JRadioButton bwButton = new JRadioButton("B/W");
	JRadioButton noCompressionButton = new JRadioButton("None");
	JRadioButton quarterCompressionButton = new JRadioButton("1/4");
	JRadioButton halfCompressionButton = new JRadioButton("1/2");
	JButton endButton = new JButton("End");
	JPanel endPanel = new JPanel();
	
	public DisplayView(Webcam webcam) {
		/**
		 * Setup new webcam panel to display local webcam
		 */
		WebcamPanel webPanel = new WebcamPanel(webcam);
		webPanel.setFPSDisplayed(false);
		webPanel.setDisplayDebugInfo(false);
		webPanel.setImageSizeDisplayed(false);
		webPanel.setMirrored(true);
		videoComponents.setLayout(new GridLayout(0,2));
		videoComponents.add(webPanel);
		videoComponents.add(panel);
		
		this.setLayout(new BorderLayout());
		this.add(videoComponents, BorderLayout.CENTER);
		JLabel label = new JLabel("Receiving Video", JLabel.CENTER);
		this.add(label, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		
		/**
		 * Add compression radio buttons to a button group
		 */
		compressionGroup.add(halfCompressionButton);
		compressionGroup.add(quarterCompressionButton);
		compressionGroup.add(noCompressionButton);
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,3));
		
		colorGroup.add(colorButton);
		colorGroup.add(bwButton);
		JPanel colorButtons = new JPanel();
		colorButtons.add(colorButton);
		colorButtons.add(bwButton);
		colorButton.setSelected(true);
		
		
		JPanel buttons = new JPanel();
		buttons.add(halfCompressionButton);
		buttons.add(quarterCompressionButton);
		buttons.add(noCompressionButton);
		halfCompressionButton.setSelected(true);
		radioPanel.add(colorButtons);
		radioPanel.add(buttons);
		endButton.setPreferredSize(new Dimension(100,50));
		endPanel.add(endButton);
		radioPanel.add(endPanel);
			
		JLabel buttonLabel = new JLabel("Compression Level", JLabel.CENTER);
		buttonPanel.add(buttonLabel, BorderLayout.NORTH);
		buttonPanel.add(radioPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
	
	/**
	 * Displays the input image to the receiving side of the video chat
	 * @param image
	 */
	public void displayImage(BufferedImage image) {
		this.panel.removeAll();
		this.panel.add(new JLabel(new ImageIcon(image)));
		this.pack();
	}
	
	/**
	 * Add an actionlistener to the close button to assign proper close action
	 * @param closeListener
	 */
	public void addCloseListener(ActionListener closeListener) {
		endButton.addActionListener(closeListener);
	}
	
	/**
	 * Add an actionlistener to the color radio buttons to allow for 
	 * different functionality based on which is chosen
	 * @param colorButtonListener
	 */
	public void addColorSelect(ActionListener colorButtonListener) {
		colorButton.addActionListener(colorButtonListener);
		bwButton.addActionListener(colorButtonListener);
	}
	
	/**
	 * Add an actionlistener to the compression buttons to allow for
	 * different functionality based on which is chosen
	 * @param selectionButtonListener
	 */
	public void addSelectionListener(ActionListener selectionButtonListener) {
        halfCompressionButton.addActionListener(selectionButtonListener);
        noCompressionButton.addActionListener(selectionButtonListener);
        quarterCompressionButton.addActionListener(selectionButtonListener);
    }
}
