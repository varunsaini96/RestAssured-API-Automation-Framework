package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="ExcelData")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet1");
        int colcount = excelUtility.getCellCount("Sheet1",1);

        String apidata[][] = new String[rownum][colcount];

        for (int i = 1; i <=rownum ; i++) {
            for(int j=0; j<colcount;j++)
            {
                apidata[i-1][j] = excelUtility.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }

    @DataProvider(name="UserNames")
    public String[] getUserName() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet1");

        String apidata[] = new String[rownum];
        for (int i = 1; i <= rownum; i++) {
            apidata[i-1] = excelUtility.getCellData("Sheet1",i,1);
        }

        return apidata;
    }
}
