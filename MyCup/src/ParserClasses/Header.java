/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;
import java.util.ArrayList;
     
/**
 *
 * @author NEKO
 */
public class Header {
    
    Import imports;
    ParserH parserh ;

    public Header() {
    }

    public Header(Import imports, ParserH parserh) {
        this.imports = imports;
        this.parserh = parserh;
    }

    public Import getImports() {
        return imports;
    }

    public void setImports(Import imports) {
        this.imports = imports;
    }

    public ParserH getParserh() {
        return parserh;
    }

    public void setParserh(ParserH parserh) {
        this.parserh = parserh;
    }
    
}
