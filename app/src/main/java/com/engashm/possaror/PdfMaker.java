package com.engashm.possaror;

import android.graphics.pdf.PdfDocument;

public class PdfMaker {

   PdfDocument pdfDocument = null;
    public PdfMaker(){

    }

    private void initObjects(){
        pdfDocument = new PdfDocument();



    }
}
