package util;

import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * IO 工具类
 *
 * @author anonymous
 */
public class IOUtil {

    /**
     * 输出资源文件内容
     *
     * @param resourceName 资源文件名
     */
    @SneakyThrows
    public static void printContents(String resourceName) {
        InputStream in = IOUtil.class.getResourceAsStream(resourceName);
        Stream<String> lines = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines();
        lines.forEach(System.out::println);
    }
}
