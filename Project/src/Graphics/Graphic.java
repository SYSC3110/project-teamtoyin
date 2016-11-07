package Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author Osama Buhamad
 *
 * @date 11/06/2016
 *
 */

public abstract class Graphic extends JPanel {
	//these variables are used to define the x-axis and y-axis position of a node 
		private int xPosition;
		private int yPosition;
		

		//these two variables are used to set the dimensions of the box that represents the node 
		private int width;
		private int height;
		
		//these variables are used to properly position the node name in the box 
		private int xTextOffset;
		private int yTextOffset;
		
		private String nodeName;

		
		public Graphic(String nodeName, int xPosition, int yPosition){
			this.nodeName=nodeName;
			this.xPosition=xPosition;
			this.yPosition=yPosition;
		}

		/**
		 * return the x-axis Position
		 * @return
		 */
		public int getxPosition() {
			return xPosition;
		}

		/**
		 * setting the x-axis Position
		 * @param xPosition
		 */
		public void setxPosition(int xPosition) {
			this.xPosition = xPosition;
		}
		
		/**
		 * return the y-axis Position
		 * @return
		 */
		public int getyPosition() {
			return yPosition;
		}

		/**
		 * Setting the y-axis Position
		 * @param yPosition
		 */
		public void setyPosition(int yPosition) {
			this.yPosition = yPosition;
		}

		/**
		 * return the width of the node box 
		 * @return
		 */
		public int getWidth() {
			return width;
		}

		/**
		 * setting the size of node's box width
		 * @param width
		 */
		public void setWidth(int width) {
			this.width = width;
		}

		/**
		 * return the height of the node box
		 * @return
		 */
		public int getHeight() {
			return height;
		}

		/**
		 * setting the height of the node's box
		 * @param height
		 */
		public void setHeight(int height) {
			this.height = height;
		}
		
		public String getNodeName(){
			return this.nodeName;
			}
		public void setNodeName(String nodeName){
			this.nodeName=nodeName;
			}
		
		public int getxTextOffset() {
			return xTextOffset;
		}

		public void setxTextOffset(int xTextOffset) {
			this.xTextOffset = xTextOffset;
		}

		public int getyTextOffset() {
			return yTextOffset;
		}

		public void setyTextOffset(int yTextOffset) {
			this.yTextOffset = yTextOffset;
		}

}
