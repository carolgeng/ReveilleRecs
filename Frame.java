import javax.swing.JFrame;

public class Frame extends JFrame{

	private final int WIDTH = 1500;
	private final int HEIGHT = 850;
	
	public Frame(String line)
	{
		super(line);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Panel());
		setVisible(true);
	}
}
