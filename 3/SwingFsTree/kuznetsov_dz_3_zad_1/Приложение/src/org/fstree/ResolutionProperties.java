/*
 * Класс для хранения и изменения настроек окна приложения, 
 * таких как высота и ширина, позицонирование на экране
 * Look and feel и прочих 
 */

// TODO : сделать обработку события изменения размера фрейма 
// TODO : сделать центрирование более динамическим (в зависимости от размеров
// jframe)

package org.fstree;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class ResolutionProperties {

	protected double screenWidth;
	protected double screenHeight;

	protected double appDimensionX;
	protected double appDimensionY;

	protected int appWindowFrameWidth;
	protected int appWindowFrameHeight;

	// по-умолчанию помещаем jframe по центру текущего экрана
	ResolutionProperties() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth = screenSize.getWidth();
		this.screenHeight = screenSize.getHeight();

	}

	public double getscreenWidth() {
		return screenWidth;
	}

	public double getscreenHeight() {
		return screenHeight;
	}

	public void setappDimensionX(double dx) throws IsNegativeException {
		if (dx < 0) {
			throw new IsNegativeException("Negative value is forbidden");
		} else {
			this.appDimensionX = dx;
		}
	}

	public void setappDimensionY(double dy) throws IsNegativeException {
		if (dy < 0) {
			throw new IsNegativeException("Negative value is forbidden");
		} else {
			this.appDimensionY = dy;
		}
	}

	// TODO: сделать центрирование динамическим (в зависимости от размеров
	// jframe)
	public void setappDimensionX(int appWidth) {
		// this.appDimensionX =
		// this.screenWidth/2-this.grabappWindowWidth(frame)/2;
		this.appDimensionX = this.screenWidth / 2 - appWidth / 2;
	}

	public void setappDimensionY(int appHeight) {
		// this.appDimensionY =
		// this.screenHeight/2-this.grabappWindowHeight(frame)/2;
		this.appDimensionY = this.screenHeight / 2 - appHeight / 2;
	}

	public double getappDimensionX() {
		return this.appDimensionX;
	}

	public double getappDimensionY() {
		return this.appDimensionY;
	}

	public int grabappWindowWidth(Form frame) {
		Rectangle r = frame.getBounds();
		this.appWindowFrameWidth = r.width;
		return r.width;
	}

	public int grabappWindowHeight(Form frame) {
		Rectangle r = frame.getBounds();
		this.appWindowFrameHeight = r.height;
		return r.height;
	}
}
