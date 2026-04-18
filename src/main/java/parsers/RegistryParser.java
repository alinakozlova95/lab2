/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author alina
 */
public class RegistryParser{

    private final List<Parser> parsers;

    public RegistryParser() {
        this.parsers = new ArrayList<>();
    }

    public void register(Parser parser) {
        parsers.add(parser);
    }

    public Parser getParser(String fileName) {
        for (Parser parser : parsers) {
            if (parser.supports(fileName)) {
                return parser;
            }
        }
        throw new IllegalArgumentException("Unsupported file format: " + fileName);
    }

    public List<Parser> getRegisteredParsers() {
        return new ArrayList<>(parsers);
    }

    public boolean hasParser(String fileName) {
        for (Parser parser : parsers) {
            if (parser.supports(fileName)) {
                return true;
            }
        }
        return false;
    }
}
