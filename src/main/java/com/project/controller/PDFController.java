package com.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.project.model.Details;

@RestController
public class PDFController {
	
	private String generateHtml(Details details){
        ClassLoaderTemplateResolver ctresolver=new ClassLoaderTemplateResolver();
        ctresolver.setPrefix("templates/");
        ctresolver.setSuffix(".html");
        ctresolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine tengine=new TemplateEngine();
        tengine.setTemplateResolver(ctresolver);
        Context context=new Context(Locale.getDefault());
        context.setVariable("details", details);
        return tengine.process("details", context);
     
	}   
	@PostMapping("/pdf")
    public ResponseEntity<byte[]> generateInvoicePdf(@RequestBody Details details) throws IOException {
        //Generate HTML using Thymeleaf
        String html=generateHtml(details);
        
        //Generate HTML to PDF using iTextPDF
        ByteArrayOutputStream opStream=new ByteArrayOutputStream();
        PdfWriter writer=new PdfWriter(opStream);
        PdfDocument pdf=new PdfDocument(writer);
        
        //converting HTML to PDF
        ConverterProperties properties=new ConverterProperties();
        HtmlConverter.convertToPdf(html, pdf, properties);
        
        //Return PDF as byteArray 
        byte[] pdfBytes=opStream.toByteArray();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        //Add suffix to PDF file name
        String fileSuffix = UUID.randomUUID().toString();
        
        
		//Send file to local storage
        File outputFile =new File("C:\\Users\\Public\\Downloads\\GeneratedPDF"+fileSuffix+".pdf");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(pdfBytes);
        }
        
        
        //Send file to Post response as header 
        headers.setContentDispositionFormData("GeneratedPDF", "GeneratedPDF"+fileSuffix+".pdf");
        headers.setContentLength(pdfBytes.length);
        return new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
    }
}
