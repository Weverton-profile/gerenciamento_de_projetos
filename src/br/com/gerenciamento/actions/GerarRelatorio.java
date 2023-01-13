package br.com.gerenciamento.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.gerenciamento.dao.LogDAO;
import br.com.gerenciamento.jdbc.ConnectionFactory;
import br.com.gerenciamento.model.Log;


public class GerarRelatorio implements Acao {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        try (Connection con = new ConnectionFactory().recuperarConexao()) {
            Document documento = new Document(PageSize.A4);
            LogDAO logDao = new LogDAO(con);
            String paramId = req.getParameter("idProjeto");
            Integer id = Integer.valueOf(paramId);
            documento.setMargins(40f, 40f, 40f, 40f);
            
            try {
                resp.reset();
                resp.setContentType("Application/pdf");
                resp.addHeader("Content-Disposition", "inline filename"+"relatorio.pdf");
                PdfWriter.getInstance(documento, resp.getOutputStream());
                documento.open();
                documento.addTitle("Relatorio do Projeto");
                Paragraph tituloDoRelatorio = new Paragraph(new Phrase(20f, "Relatorio do Projeto", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16f)));
                Paragraph linhaEmBranco = new Paragraph(new Phrase(20f, "                     ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16f)));
                tituloDoRelatorio.setAlignment(Element.ALIGN_CENTER);
                documento.add(tituloDoRelatorio);   
                documento.add(linhaEmBranco);
                PdfPTable tabela = new PdfPTable(2);
                tabela.setWidthPercentage(100f);
                tabela.setWidths(new float[] {80f, 20f});
                Font font = new Font();
                font.setFamily(FontFactory.HELVETICA_BOLD);
                font.setColor(BaseColor.WHITE);
                PdfPCell col1 = new PdfPCell(new Paragraph(new Phrase("Descrição", font)));
                col1.setBackgroundColor(new BaseColor(108, 92, 204));
                col1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell col2 = new PdfPCell(new Paragraph(new Phrase("Data", font)));
                col2.setBackgroundColor(new BaseColor(108, 92, 204));
                col2.setHorizontalAlignment(Element.ALIGN_CENTER);           
                tabela.addCell(col1);
                tabela.addCell(col2);
                ArrayList<Log> logs = new ArrayList<Log>();
                logs = logDao.listarLogs(id);
                for (Log log : logs) {
                  PdfPCell descricao = new PdfPCell(new Phrase(log.getDescricao(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8f)));
                  descricao.setPaddingLeft(10f);
                  PdfPCell data = new PdfPCell(new Phrase(log.getData(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8f)));
                  data.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tabela.addCell(descricao);
                  tabela.addCell(data);
                }
                documento.add(tabela);
                
            } catch (FileNotFoundException | DocumentException e) {
                System.out.println(e.getMessage());
            } finally {
                documento.close();
            }
            return null;
        }
    }

}
