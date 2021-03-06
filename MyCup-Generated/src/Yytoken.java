class yyToken {
  public int m_index;
  public String m_text;
  public int m_line;
  public int m_charBegin;
  public int m_charEnd;
  public String m_lexema;
  
  yyToken (int index, int line, int charBegin, String text) {
    m_index = index;
    m_text = text;
    m_line = line;
    m_charBegin = charBegin;
   
  }
 
  public String toString() {
    return "Text   : "+m_text+
           "\nindex : "+m_index+
           "\nline  : "+m_line+
           "\ncBeg. : "+m_charBegin+
           "\ncEnd. : "+m_charEnd+
           "\nLexema. : "+m_lexema;

  }
}

