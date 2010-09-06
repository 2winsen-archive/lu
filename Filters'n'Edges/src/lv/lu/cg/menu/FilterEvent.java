package lv.lu.cg.menu;

/**
 * 
 * @author vitalik
 *
 */
public class FilterEvent {
	
	public enum KernelType {
		KERNEL_3x3,
		KERNEL_5x5,
		KERNEL_7x7;
	}
	
	public static final KernelType DEFAULT_TYPE = KernelType.KERNEL_3x3;
	
	private String name;
	private KernelType type;
	private int distance;
	
	public FilterEvent(String name) {
		this.name = name;
		this.type = DEFAULT_TYPE;
		this.distance = 1;
	}
	
	public FilterEvent(String name, KernelType type) {
		this.name = name;
		switch (type) {
		case KERNEL_3x3:
			distance = 1;
			break;
		case KERNEL_5x5:
			distance = 2;
			break;
		case KERNEL_7x7:
			distance = 3;
			break;
		default:
			distance = 1;
			break;
		}
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getDistance() {
		return distance;
	}

	public KernelType getType() {
		return type;
	}
	
}
