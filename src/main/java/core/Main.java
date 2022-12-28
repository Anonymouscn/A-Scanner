package core;

import async.TaskPool;
import core.interact.Banner;
import core.interact.ParamInterpreter;

/**
 * 主类 main class to boot
 *
 * @author anonymous
 */
public class Main {
    public static void main(String[] args) {
        Banner.show();
        ParamInterpreter.action(ParamInterpreter.read(args));
        TaskPool.pool.shutdown();
    }
}
