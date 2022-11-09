package com.mobi.ocs.utilities;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class RoundedBorder implements PdfPCellEvent {

  
	  public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas)
      {
          PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
          cb.roundRectangle(
            rect.getLeft() + 1.5f,
            rect.getBottom() + 1.5f, 
            rect.getBorderWidth() - 5,
            rect.getHeight() - 5, 6);
          cb.stroke();
      }
	
}

