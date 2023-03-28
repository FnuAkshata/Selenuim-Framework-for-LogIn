package AutomationLearning.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* -----  never used this class, instead optimized the code in base class ----*/

public class DataReader {

    public List<HashMap<String, String>> getJsondataToMap() throws IOException {

//        File file=new File("C:\\akshata\\SeleniumFrameworkDesign\\src\\test\\java\\AutomationLearning\\data\\PurchaseOrder.json");
        // reading json to string
        String jsonData = FileUtils.readFileToString(new File("C:\\akshata\\SeleniumFrameworkDesign\\src\\test\\java\\AutomationLearning\\data\\PurchaseOrder.json"),"UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data=mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
}
