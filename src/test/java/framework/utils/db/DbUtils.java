package framework.utils.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


/*
    Note: This is a sample class that demonstrates the configuration and usage of the jdbcTemplate based on the project
    name, that the user provides. Here I have
 */

public class DbUtils {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    DataSource mccDataSource;
//
//    @Autowired
//    DataSource rdcDataSource;
//
//    @Autowired
//    Environment environment;
//
//    @Deprecated
//    public List<Map<String, Object>> selectFrom(String query, Object... objectArgs) {
//        return jdbcTemplate.queryForList(query, objectArgs);
//    }
//
//    public List<Map<String, Object>> selectFrom(PROJECT_TYPE dcType, String query, Object... objectArgs) {
//        configureJdbcTemplate(jdbcTemplate, dcType);
//        return jdbcTemplate.queryForList(query, objectArgs);
//    }
//
//    @Deprecated
//    public List<Map<String, Object>> selectFrom(String query) {
//        return jdbcTemplate.queryForList(query);
//    }
//
//
//    public List<Map<String, Object>> selectFrom(PROJECT_TYPE dcType, String query) {
//        configureJdbcTemplate(jdbcTemplate, dcType);
//        return jdbcTemplate.queryForList(query);
//    }
//
//    @Deprecated
//    public int insertInto(String query, Object... objectArgs) {
//        return jdbcTemplate.update(query, objectArgs);
//    }
//
//    public int insertInto(PROJECT_TYPE dcType, String query, Object... objectArgs) {
//        configureJdbcTemplate(jdbcTemplate, dcType);
//        return jdbcTemplate.update(query, objectArgs);
//    }
//
//    @Deprecated
//    public int deleteFrom(String query, Object... objectArgs) {
//        return jdbcTemplate.update(query, objectArgs);
//    }
//
//    public int deleteFrom(PROJECT_TYPE dcType, String query, Object... objectArgs) {
//        configureJdbcTemplate(jdbcTemplate, dcType);
//        return jdbcTemplate.update(query, objectArgs);
//    }
//
//    private JdbcTemplate configureJdbcTemplate(JdbcTemplate jdbcTemplate, PROJECT_TYPE dct) {
//
//        if (dct == PROJECT_TYPE.REDDIT) {
//            jdbcTemplate.setDataSource(mccDataSource);
//        } else if (dct == PROJECT_TYPE.AMAZON) {
//            jdbcTemplate.setDataSource(rdcDataSource);
//        }
//
//        return jdbcTemplate;
//    }
//
//    private JdbcTemplate configureJdbcTemplate(JdbcTemplate jdbcTemplate, String project) {
//
//        if (project.equalsIgnoreCase("test3")) {
//            jdbcTemplate.setDataSource(redditDataSource);
//        } else if (dct == PROJECT_TYPE.RDC) {
//            jdbcTemplate.setDataSource(rdcDataSource);
//        }
//
//        return jdbcTemplate;
//    }
//
//    public String transformIntoSpringQuery(String query, int arrLength) {
//
//        String[] split = query.split("\\(");
//
//        if (arrLength == 1)
//            return split[0] + "(?" + split[1];
//        else {
//            for (int i = 0; i < arrLength - 1; i++) {
//                if (i == 0)
//                    split[0] += "(";
//                split[0] += "?,";
//            }
//
//            System.out.println(split[0] + "?" + split[1]);
//            return split[0] + "?" + split[1];
//        }
//
//    }
}
