package framework.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.sun.codemodel.JCodeModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import org.junit.Assert;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JsonUtils {

    Logger logger = LogManager.getLogger(JsonUtils.class);

    public Object readJsonFromResponseEntity(ResponseEntity response, String jp) throws IOException {

        return JsonPath.parse(response.getBody().toString()).read(jp);
    }


    public Object readJsonFromString(String json, String jp) throws IOException {

        return JsonPath.parse(json).read(jp);
    }

    public String setJsonAtJsonPath(String parent, Object child, String jsonPath) throws JsonProcessingException, ParseException {

        ObjectMapper om = new ObjectMapper();
        JSONParser jp = new JSONParser(JSONParser.MODE_PERMISSIVE);

        Object childParse = jp.parse(om.writeValueAsString(child));
     //   Object parentParse = jp.parse(om.writeValueAsString(parent));

        DocumentContext documentContext = JsonPath.parse(parent).set(jsonPath, childParse);

        return documentContext.jsonString();
    }


    public JSONObject convertStringToMinidevJsonObject(String jsonObject) throws ParseException {
        JSONParser jp = new JSONParser(JSONParser.MODE_PERMISSIVE);
        return (JSONObject) jp.parse(jsonObject);
    }

    public JSONArray convertStringToMinidevJsonArray(String jsonObject) throws ParseException {
        JSONParser jp = new JSONParser(JSONParser.MODE_PERMISSIVE);
        return (JSONArray) jp.parse(jsonObject);
    }

    public JSONArray converyListToJsonArray(List list) throws JsonProcessingException, ParseException {

        JSONParser jp = new JSONParser(JSONParser.MODE_PERMISSIVE);
        ObjectMapper om = new ObjectMapper();

        JSONArray ja = (JSONArray) jp.parse(om.writeValueAsString(list));

        return ja;
    }


    public JSONObject convertToJsonObject(Object object) throws JsonProcessingException, ParseException {

        JSONParser jp = new JSONParser(JSONParser.MODE_PERMISSIVE);
        ObjectMapper om = new ObjectMapper();

        JSONObject ja = (JSONObject) jp.parse(om.writeValueAsString(object));

        return ja;
    }

    public void compareJsonObjects(Object jArr1, Object jArr2) throws JSONException {

        logger.info("Comparison of " + jArr1 + " and " + jArr2 + " started");

        JSONCompareResult result = JSONCompare.compareJSON(jArr1.toString(), jArr2.toString(), JSONCompareMode.LENIENT);

        registerResults(result);

        logger.info("Comparison of arrays passed");
    }


    private void registerResults(JSONCompareResult result) {
        if (result.failed()) {

            Iterator<FieldComparisonFailure> iter = result.getFieldFailures().iterator();

            while (iter.hasNext()) {
                FieldComparisonFailure fcf = iter.next();

                String mismatchDetails = "The mismatch occured for the field : " + fcf.getField() + ", the expected value is : "
                        + fcf.getExpected() + ", and the actual value is :  " + fcf.getActual();

                logger.error(mismatchDetails);
            }

            Assert.assertFalse("The comparison between the request and response objects failed", result.failed());
        }
    }


    /*
     * Sample invocation -> jsonToClass(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
     *
     * 	URL inpJson = new File("/Users/dmp001j/eclipse-workspace/git/UI/allocation-order-service.test/mappings/Schemas/ibd.json").toURI().toURL();
     *
     * 	File pojoDir = new File("/Users/dmp001j/eclipse-workspace/git/UI/allocation-order-service.test/src/main/java/com/walmart/move/nim/utilities/pojos");
     *
		uc.jsonToClass(inpJson, pojoDir, "", "IBD");
     *
     *  @param inpJson file which has to be converted to a POJO, Supply the absolute path, converted to a URL as in the example above.
     *
     *  @param outputPojoDirectory The absolute path where the POJO file(s) should be created.
     *
     *  @param outputPojoDirectory The class name of the created primary POJO entity.
     *
     */
    public void jsonToClass(URL inputJson, File outputPojoDirectory, String packageName, String className)
            throws IOException {

        JCodeModel codeModel = new JCodeModel();
        URL source = inputJson;

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }

            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        SchemaMapper mapper = new SchemaMapper(
                new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());

        mapper.generate(codeModel, className, packageName, source);
        codeModel.build(outputPojoDirectory);
    }


    public String getJsonStringFromFile(String pathToJsonFile) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(pathToJsonFile));
        String myJson = scan.useDelimiter("\\Z").next();

        scan.close();
        return myJson;
    }


//    @Test
    public void createJsonFromFile() throws IOException {

        String pwd = System.getProperty("user.dir")+"/REDDIT";

        URL inpJson = new File(pwd+ "/xxx/.json").toURI().toURL();

        File pojoDir = new File(pwd + "/xxx/yyy/pojo");

        jsonToClass(inpJson, pojoDir, "", "test");


    }


    public Object getPojofromJsonObject(Object obj, Class aClass) throws IOException, ParseException {
    	
    	
    	JSONObject jsonObj = convertToJsonObject(obj);
        ObjectMapper mapper = new ObjectMapper();
        Object object = mapper.readValue(jsonObj.toJSONString(), aClass);
        return object;
    }


    public List<?> getPojoListfromPath(String json, Class className) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, className));
    }
}
