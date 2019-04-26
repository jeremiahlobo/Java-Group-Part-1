package Core;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GeneratePDF {


 /*

    private static GeneratePDF ourInstance = new GeneratePDF();

    public static GeneratePDF getInstance() {
        return ourInstance;
    }

    // generate PDF from object
    public static boolean GeneratePDF(ObservableList<Customer> PDFlist) {

        try {

            DecimalFormat df = new DecimalFormat("#,###.00");

            for(PDFlist.) {
                Document document = new Document();
                StringBuilder filePaths = new StringBuilder();
                filePaths.append(DBConnection.class.getClassLoader().getResource("../"));
                filePaths.append(orderDetail.getBookingNo() + ".pdf");

                String filePath = "C:\\Users\\749213\\" + orderDetail.getBookingNo() + ".pdf";
                File pdfFile = new File(filePath);

                if(!pdfFile.exists())
                {
                    pdfFile.createNewFile();
                }

                PdfWriter writer = PdfWriter.getInstance(document,
                        new FileOutputStream(filePath));
                document.open();
                document.add(new Paragraph(""));

                // Add Image
                Image pdfIcon = Image.getInstance(DBConnection.class.getClassLoader().getResource("../") + "pdf _icon.jpg");
                // Fixed Positioning
                pdfIcon.setAbsolutePosition(10f, 55f);
                // Scale to new height and new width of image
                pdfIcon.scaleAbsolute(100, 100);
                // Add to document
                //document.add(pdfIcon);

                // General Information
                PdfPTable table = new PdfPTable(2); // 3 columns.
                table.setWidthPercentage(100); // Width 100%
                table.setSpacingBefore(10f); // Space before table
                table.setSpacingAfter(10f); // Space after table

                // Set Column widths
                float[] columnWidths = { 0.3f, 0.7f};
                table.setWidths(columnWidths);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
                cell1.setBorderColor(BaseColor.WHITE);
                cell1.setPaddingLeft(10);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.addElement(pdfIcon);

                BaseFont bf = BaseFont.createFont(
                        BaseFont.TIMES_ROMAN,
                        BaseFont.CP1252,
                        BaseFont.EMBEDDED);
                Font font = new Font(bf, 12);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Travel Experts Customer Invoice"));
                cell2.setBorderColor(BaseColor.WHITE);
                cell2.setPaddingLeft(10);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(cell1);
                table.addCell(cell2);
                document.add(table);

                // Price in Detail
                PdfPTable generalInfo = new PdfPTable(2); // 3 columns.
                generalInfo.setWidthPercentage(100); // Width 100%
                generalInfo.setSpacingBefore(10f); // Space before table
                generalInfo.setSpacingAfter(10f); // Space after table

                // Set Column widths
                float[] columnWidthsDetail = { 1f, 1f};
                generalInfo.setWidths(columnWidthsDetail);

                PdfPCell cell1Detail = new PdfPCell(new Paragraph("Booking No: " + orderDetail.getBookingNo()));
                cell1Detail.setBorderColor(BaseColor.WHITE);
                cell1Detail.setPaddingLeft(10);
                cell1Detail.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1Detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cell2Detail = new PdfPCell(new Paragraph("Booking Date: " + orderDetail.getBookingDate()));
                cell2Detail.setBorderColor(BaseColor.WHITE);
                cell2Detail.setPaddingLeft(10);
                cell2Detail.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2Detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cell3Detail = new PdfPCell(new Paragraph("Customer's Name: " + orderDetail.getBookingNo()));
                cell3Detail.setBorderColor(BaseColor.WHITE);
                cell3Detail.setPaddingLeft(10);
                cell3Detail.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3Detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cell4Detail = new PdfPCell(new Paragraph("Traveler Count: " + orderDetail.getTravelerCount()));
                cell4Detail.setBorderColor(BaseColor.WHITE);
                cell4Detail.setPaddingLeft(10);
                cell4Detail.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4Detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cell5Detail = new PdfPCell(new Paragraph("Trip Start: " + orderDetail.getTripStart()));
                cell5Detail.setBorderColor(BaseColor.WHITE);
                cell5Detail.setPaddingLeft(10);
                cell5Detail.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell5Detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cell6Detail = new PdfPCell(new Paragraph("Trip End: " + orderDetail.getTripEnd()));
                cell6Detail.setBorderColor(BaseColor.WHITE);
                cell6Detail.setPaddingLeft(10);
                cell6Detail.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell6Detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

                generalInfo.addCell(cell1Detail);
                generalInfo.addCell(cell2Detail);
                generalInfo.addCell(cell3Detail);
                generalInfo.addCell(cell4Detail);
                generalInfo.addCell(cell5Detail);
                generalInfo.addCell(cell6Detail);

                document.add(generalInfo);

                // Description
                PdfPTable descTB = new PdfPTable(1); // 3 columns.
                descTB.setWidthPercentage(100); // Width 100%
                descTB.setSpacingBefore(10f); // Space before table
                descTB.setSpacingAfter(10f); // Space after table

                // Set Column widths
                float[] descColumnWidths = { 1f};
                descTB.setWidths(descColumnWidths);

                PdfPCell desc = new PdfPCell(new Paragraph("Description: " + orderDetail.getDescription()));
                desc.setBorderColor(BaseColor.WHITE);
                desc.setPaddingLeft(10);
                desc.setHorizontalAlignment(Element.ALIGN_CENTER);
                desc.setVerticalAlignment(Element.ALIGN_LEFT);

                descTB.addCell(desc);
                document.add(descTB);

                // Price Detail Table
                PdfPTable priceDetail = new PdfPTable(4); // 3 columns.
                priceDetail.setWidthPercentage(100); // Width 100%
                priceDetail.setSpacingBefore(10f); // Space before table
                priceDetail.setSpacingAfter(10f); // Space after table

                // Set Column widths
                float[] priceColumnWidths = { 1f, 1f, 1f, 1f};
                priceDetail.setWidths(priceColumnWidths);

                PdfPCell prodName = new PdfPCell(new Paragraph("Product Name"));
                prodName.setBorderColor(BaseColor.GRAY);
                prodName.setPaddingLeft(10);
                prodName.setHorizontalAlignment(Element.ALIGN_CENTER);
                prodName.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell basePrice = new PdfPCell(new Paragraph("Base Price"));
                basePrice.setBorderColor(BaseColor.GRAY);
                basePrice.setPaddingLeft(10);
                basePrice.setHorizontalAlignment(Element.ALIGN_CENTER);
                basePrice.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell AgcComission = new PdfPCell(new Paragraph("Agency Comission"));
                AgcComission.setBorderColor(BaseColor.GRAY);
                AgcComission.setPaddingLeft(10);
                AgcComission.setHorizontalAlignment(Element.ALIGN_CENTER);
                AgcComission.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell totalPrice = new PdfPCell(new Paragraph("Price (+ 5% for Tax)"));
                totalPrice.setBorderColor(BaseColor.GRAY);
                totalPrice.setPaddingLeft(10);
                totalPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
                totalPrice.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prodNameVal = new PdfPCell(new Paragraph(orderDetail.getProdName()));
                prodNameVal.setBorderColor(BaseColor.GRAY);
                prodNameVal.setPaddingLeft(10);
                prodNameVal.setHorizontalAlignment(Element.ALIGN_CENTER);
                prodNameVal.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell basePriceVal = new PdfPCell(new Paragraph(orderDetail.getBasePrice().toString()));
                basePriceVal.setBorderColor(BaseColor.GRAY);
                basePriceVal.setPaddingLeft(10);
                basePriceVal.setHorizontalAlignment(Element.ALIGN_CENTER);
                basePriceVal.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell AgcComissionVal = new PdfPCell(new Paragraph(orderDetail.getAgencyCommission().toString()));
                AgcComissionVal.setBorderColor(BaseColor.GRAY);
                AgcComissionVal.setPaddingLeft(10);
                AgcComissionVal.setHorizontalAlignment(Element.ALIGN_CENTER);
                AgcComissionVal.setVerticalAlignment(Element.ALIGN_MIDDLE);

                BigDecimal total = orderDetail.getBasePrice().add(orderDetail.getAgencyCommission());
                BigDecimal finalPrice = total.multiply(new BigDecimal(0.05)).add(total);
                PdfPCell totalPriceVal = new PdfPCell(new Paragraph(df.format(finalPrice)));
                totalPriceVal.setBorderColor(BaseColor.GRAY);
                totalPriceVal.setPaddingLeft(10);
                totalPriceVal.setHorizontalAlignment(Element.ALIGN_CENTER);
                totalPriceVal.setVerticalAlignment(Element.ALIGN_MIDDLE);

                priceDetail.addCell(prodName);
                priceDetail.addCell(basePrice);
                priceDetail.addCell(AgcComission);
                priceDetail.addCell(totalPrice);
                priceDetail.addCell(prodNameVal);
                priceDetail.addCell(basePriceVal);
                priceDetail.addCell(AgcComissionVal);
                priceDetail.addCell(totalPriceVal);

                document.add(priceDetail);

                // Total price
                PdfPTable totalPriceTB = new PdfPTable(1); // 3 columns.
                totalPriceTB.setWidthPercentage(100); // Width 100%
                totalPriceTB.setSpacingBefore(10f); // Space before table
                totalPriceTB.setSpacingAfter(10f); // Space after table

                // Set Column widths
                float[] totalColumnWidths = { 1f};
                totalPriceTB.setWidths(totalColumnWidths);



                PdfPCell totalPriceCell = new PdfPCell(new Paragraph("Total: " + df.format(finalPrice)/* NumberFormat.getCurrencyInstance().format(finalPrice)));

                totalPriceCell.setBorderColor(BaseColor.WHITE);

                totalPriceCell.setPaddingLeft(10);
                totalPriceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                totalPriceCell.setVerticalAlignment(Element.ALIGN_RIGHT);

                totalPriceTB.addCell(totalPriceCell);

                document.add(totalPriceTB);*/

              //  //document.close();
             //   //writer.close();

             //   //sendEmail(orderDetail.getBookingNo(), orderDetail.getCustomerId());
        /*    }

                document.add(totalPriceTB);

                document.close();
                writer.close();

                sendEmail(orderDetail.getBookingNo(), orderDetail.getCustomerId());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    */
        /*
public static void makePdf(String jsonDetails) {
    Document document = new Document();
    PdfWriter writer = null;
    try {
        writer = PdfWriter.getInstance(document, new FileOutputStream("Invoice.pdf"));
    } catch (DocumentException e1) {
        e1.printStackTrace();
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    }
    document.open();
    try {
        document.add(new Paragraph("Travel Experts."));
//        var splitJson = jsonDetails.split(""},{"");
        document.add(new Paragraph(jsonDetails));


    } catch (DocumentException e1) {
        e1.printStackTrace();
    }
    document.close();
    writer.close();

    }//end makePDF*/
}//end GeneratePDF class
