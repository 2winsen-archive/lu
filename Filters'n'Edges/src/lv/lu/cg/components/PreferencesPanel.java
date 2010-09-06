package lv.lu.cg.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import lv.lu.cg.actions.ActionModel;
import lv.lu.cg.data.DataModel;
import lv.lu.cg.menu.FilterEvent.KernelType;

/**
 * 
 * @author vitalik
 *
 */
@SuppressWarnings("serial")
public class PreferencesPanel extends JPanel implements DialogPanel {
	
	private final DataModel dataModel = DataModel.getInstance();
	private final ActionModel actionModel = ActionModel.getInstance();
	
	private Object parent;
	
	public PreferencesPanel() {
		initGUI();
	}
	
	public void initGUI() {
		
		final JRadioButton kernel3x3 = new JRadioButton("3x3", false);
		final JRadioButton kernel5x5 = new JRadioButton("5x5", false);
		final JRadioButton kernel7x7 = new JRadioButton("7x7", false);
		
		switch (dataModel.getKernelType()) {
		case KERNEL_3x3:
			kernel3x3.setSelected(true);
			break;
		case KERNEL_5x5:
			kernel5x5.setSelected(true);
			break;
		case KERNEL_7x7:
			kernel7x7.setSelected(true);
			break;
		default:
			kernel3x3.setSelected(true);
			break;
		}

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(kernel3x3);
		bgroup.add(kernel5x5);
		bgroup.add(kernel7x7);

		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(3, 1));
		radioPanel.setPreferredSize(new Dimension(120, 110));
		radioPanel.add(kernel3x3);
		radioPanel.add(kernel5x5);
		radioPanel.add(kernel7x7);
		
		radioPanel.setBorder(BorderFactory.createTitledBorder(
		           BorderFactory.createEtchedBorder(), "Kernel size?"));
		this.add(radioPanel);
		
		JButton okButton = new JButton("OK");
		this.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parent != null) {
					if(kernel3x3.isSelected()) {
						dataModel.setKernelType(KernelType.KERNEL_3x3);
						actionModel.addAction("Kernel size changed to 3x3");
					} else if(kernel5x5.isSelected()) {
						dataModel.setKernelType(KernelType.KERNEL_5x5);
						actionModel.addAction("Kernel size changed to 5x5");
					} else if(kernel7x7.isSelected()) {
						dataModel.setKernelType(KernelType.KERNEL_7x7);
						actionModel.addAction("Kernel size changed to 7x7");
					}
					((JDialog)parent).dispose();
				}
			}
		});
		
	}

	@Override
	public void setParent(Object obj) {		
		parent = obj;
	}

}
