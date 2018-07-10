package beans.controllers;

import beans.models.Ticket;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("bookedTicketsPdfView")
public class BookedTicketsPdfView extends AbstractView {
    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=bookedTickets.pdf");

        List<Ticket> tickets = (List<Ticket>) model.get("purchasedTicketsForEvent");

        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);

        pdfDocument.add(new Paragraph("Booked tickets"));

        tickets.stream()
                .map(ticket -> new Paragraph("Ticked with seat#" + ticket.getSeats()))
                .forEach(pdfDocument::add);
        pdfDocument.close();
    }
}
