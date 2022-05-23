package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据抽取器
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
public class DataExtractor {
    private static final Logger log = LoggerFactory.getLogger(DataExtractor.class);
    private static final String AND_REG = "&";
    private static final String EQUALS_REG = "=";

    private DataExtractor() {

    }

    /**
     * 获取请求参数内容
     *
     * @param parameterNames 参数名称列表
     * @param args           参数列表
     * @return Gets the request parameter content
     */
    public static Object getArgs(String[] parameterNames, Object[] args) {
        Object target;
        if (args.length == 1) {
            target = args[0];
        } else {
            target = args;
        }
        if (target == null) {
            return null;
        }
        return appletArgs(parameterNames, args);
    }

    /**
     * 获取程序参数
     *
     * @param parameterNames 参数名
     * @param args           参数值
     * @return Get program parameters
     */
    public static Object appletArgs(String[] parameterNames, Object[] args) {
        if (parameterNames == null || parameterNames.length == 0 || args == null || args.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            sb.append(parameterNames[i]).append(EQUALS_REG).append(args[i].toString()).append(AND_REG);
        }
        if (sb.lastIndexOf(AND_REG) != -1) {
            sb.deleteCharAt(sb.lastIndexOf(AND_REG));
        }
        return sb.toString();
    }

}
