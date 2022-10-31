package com.PSL.management.Security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import com.PSL.management.dataModel.SessionData;

public class SessionDataconfig {
  public static ByteArrayInputStream sessionDataDBToCSV(List<SessionData> sessionsData) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (SessionData sessionData : sessionsData) {
        List<String> data = Arrays.asList(
              String.valueOf(sessionData.getId()),
              sessionData.getUsername(),
              sessionData.getSessionid(),
              sessionData.getSessionAccessTime(),
              sessionData.getMaxInInterval(),
              sessionData.getSessionEndTime()
            );
        csvPrinter.printRecord(data);
      }
      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}


