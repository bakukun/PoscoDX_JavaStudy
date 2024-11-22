package paint;

public class ColorPoint extends Point {
	
	private String color;
	
	public ColorPoint(int x,int y, String color) {
		setX(x);
		setY(y);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		System.out.println("Point[x=" + getX() + "," + "y=" + getY() +",color=" + color + "]를 그렸습니다."); // 이 x와 y가 문제 -> get 메소드로 해결
		// super.show(); 부분 재구현
		// 부모의 기능을 안쓰면 완전 재구현
	}

	@Override
	public void show(boolean visible) {
		super.show(visible);
	}
	
	@Override
	public void draw() {
		show();
	}
	
	
	
	
	
	

}
