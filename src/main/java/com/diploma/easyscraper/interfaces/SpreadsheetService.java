package com.diploma.easyscraper.interfaces;

import java.io.IOException;
import java.util.List;

public interface SpreadsheetService {
    String createSpreadsheet(List<List<String>> rows) throws IOException;
}
