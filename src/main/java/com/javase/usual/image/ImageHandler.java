package com.usual.image;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

public class ImageHandler {
	public static void main(String[] args) {
		// create command
		ConvertCmd cmd = new ConvertCmd(true);

		// create the operation, add images and operators/options
		IMOperation op = new IMOperation();
		op.addImage("E:\\05-work\\03-workspaces\\TQZ\\WebRoot\\fuxiao\\images\\xiao3.jpg");
		op.resize(533,354);
		op.addImage("E:\\05-work\\03-workspaces\\TQZ\\WebRoot\\fuxiao\\images\\banner3.jpg");
		cmd.setSearchPath("D:\\Program Files\\GraphicsMagick-1.3.21-Q8");
		// execute the operation
		try {
			cmd.run(op);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IM4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		cropImageCenter("E:\\05-work\\03-workspaces\\TQZ\\WebRoot\\fuxiao\\images\\banner1.jpg",
				"E:\\05-work\\03-workspaces\\TQZ\\WebRoot\\fuxiao\\images\\xiao11.jpg",104,61);*/
	}
	
	public static void cropImageCenter(String srcPath, 
			String destPath, int rectWidth, int rectHeight) {
		IMOperation op = new IMOperation();
		op.addImage();
		op.resize(rectWidth/2, rectHeight/2, "^").gravity("center")
			.extent(rectWidth, rectHeight);
		op.addImage();
		ConvertCmd convertCmd = new ConvertCmd();
		convertCmd.setSearchPath("D:\\Program Files\\GraphicsMagick-1.3.21-Q8");
		try {
			convertCmd.run(op, srcPath, destPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IM4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
