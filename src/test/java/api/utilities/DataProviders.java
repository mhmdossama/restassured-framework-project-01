package api.utilities;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    // DataProvider class is used to provide data for the test cases
    // This class is used to read data from the Excel file and provide it to the test cases
    @DataProvider(name = "Data")
    public String [][] getData( ) throws Exception {
        XLUtility xl = new XLUtility("TestData/userData.xlsx");
        int rowCount = xl.getRowCount("Sheet1");
        int colCount = xl.getCellCount("Sheet1", 1);
        String [][] data = new String  [rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws Exception {
        String path = "TestData/userData.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        String[] apidata = new String[rownum];
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apidata;
    }


}
