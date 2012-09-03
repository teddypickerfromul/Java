package sample3;

public class LocateLogic {
	public double calculateDistance(

			Point t1, Point t2) {

			/* вычисление расстояния */

			double dx = t1.getX() - t2.getX();

			double dy = t1.getY() - t2.getY();

			return Math.hypot(dx, dy);

			}		
}
