/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author NEKO
 */
public class Cup {
    Header head;
    Grammar gram;

    public Cup() {
    }

    public Cup(Header head, Grammar gram) {
        this.head = head;
        this.gram = gram;
    }

    public Grammar getGram() {
        return gram;
    }

    public void setGram(Grammar gram) {
        this.gram = gram;
    }

    public Header getHead() {
        return head;
    }

    public void setHead(Header head) {
        this.head = head;
    }
    
    
}
