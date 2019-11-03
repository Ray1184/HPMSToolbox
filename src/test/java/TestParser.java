import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.parser.LuaFunctionParser;

public class TestParser {

    private static final String TEST_CODE_1 =
            "function test_function(arg0, arg1)\n" +
                    "   if (arg0 == nil) then\n" +
                    "       return\n" +
                    "   endif\n" +
                    "   return calc_base(arg1)\n" +
                    "end";

    public static void main(String[] args) {
        try {
            LuaFunctionDeclare fn = LuaFunctionParser.parse(TEST_CODE_1);
            System.out.println(fn.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
