/**
 * 
 */
package bui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

/**
 * Concrete class for IsoShape decorator pattern
 * @author nelsoncs
 */
public class IsoShapeBase implements IsoShape {

	protected Composite parent;
	protected GC gc;
	protected Color color;
	protected int x;
	protected int y;
	protected int [] shape;

	
	/**
	 * 
	 * @param parent
	 * @param x
	 * @param y
	 * @param style
	 */
	public IsoShapeBase(Composite parent, int x, int y, int style) {
		//super(parent, style);

		this.parent = parent;
		
		/** TODO gc does not need to be the whole map. can be just the polygon */
		this.gc = new GC( this.parent );
		
		this.setColor( 255, 255, 255 );
		
		this.x = x;
		this.y = y;
		
		this.shape = new int[8];
		
		this.shape[0] = x;
		this.shape[1] = y - Img.y_adj;
		
		this.shape[2] = x + Img.x_adj;
		this.shape[3] = y;
		
		this.shape[4] = x;
		this.shape[5] = y + Img.y_adj;
		
		this.shape[6] = x - Img.x_adj;
		this.shape[7] = y;
	}
	
	
	public void draw(){
		try {

			this.gc.setBackground( this.color );

			this.gc.setAlpha(64);
			this.gc.fillPolygon( this.shape );
			
			System.out.println( this.getClass() );
			//this.setTabList();
			
			this.tryDispose();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void setColor( int r, int g, int b ){

		this.color = new Color( this.parent.getDisplay(), r, g, b );
	}
	
	
	public void tryDispose(){
		
		/** TODO each decorator base ans child is destroying and then
		 * recreating GC, might be more efficient to destroy once
		 * if can track the inheritance chain
		 */
//		if( this.getChildren().length == 0 )
//		{
			if( this.gc.isDisposed() == false )
				this.gc.dispose();
			
			if( this.color.isDisposed() == false )
				this.color.dispose();
//		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
