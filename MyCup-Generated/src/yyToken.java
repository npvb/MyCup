/**
 *
 * @author Paulette
 */
public class Yytoken {
  
  public int m_index;
  public String m_text;
  public int m_line;
  public int m_charBegin;
  public int m_charEnd;
  public String m_lexema;
  
  Yytoken (int index, String text, int line, int charBegin, int charEnd, String lexema) {
    m_index = index;
    m_text = text;
    m_line = line;
    m_charBegin = charBegin;
    m_charEnd = charEnd;
    m_lexema = lexema;
  }

 @Override
  public String toString() {
    return "Text   : "+m_text+
           "\nindex : "+m_index+
           "\nline  : "+m_line+
           "\ncBeg. : "+m_charBegin+
           "\ncEnd. : "+m_charEnd+
           "\nLexema. : "+m_lexema;

  }
}
