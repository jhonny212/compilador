package GramaticaJAVA;
import java_cup.runtime.Symbol;
import Errores.ErrorClass;
%%
%class javalex
%cup
%cupdebug
%line
%column
%full
%char
%public
%{
    StringBuffer string = new StringBuffer();
    public boolean add=true;
    public int fila,columna=0;
     public ErrorClass errores=new ErrorClass();
    private Symbol symbol(int type, Object value) {
        fila=yyline+1;
        columna+=yycolumn+1;

        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}
numero=[0-9]
letra=[a-zA-Z]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
Identifier = [:jletter:] [:jletterdigit:]*
%state VERIFY
%state STRING
%state CHAR

%%
<YYINITIAL>{
    "\""
    {yybegin(VERIFY);}
    "'"
    {yybegin(STRING);}
    "while"                 {if(add){return symbol(sym.WHILE,new String(yytext()));}}
    "else"                 {if(add){return symbol(sym.ELSE,new String(yytext()));}}
    "for"                 {if(add){return symbol(sym.FOR,new String(yytext()));}}
    "do"                 {if(add){return symbol(sym.DO,new String(yytext()));}}
    ";"                 {if(add){return symbol(sym.PUNTOCOMA,new String(yytext()));}}
    "class"                 {if(add){return symbol(sym.CLASS,new String(yytext()));}}
    "switch"                 {if(add){return symbol(sym.SWITCH,new String(yytext()));}}
    "case"                 {if(add){return symbol(sym.CASE,new String(yytext()));}}
    "default"                 {if(add){return symbol(sym.DEFAULT,new String(yytext()));}}
    "break"                 {if(add){return symbol(sym.BREAK,new String(yytext()));}}
    ":"                 {if(add){return symbol(sym.BOTHPOINT,new String(yytext()));}}
    "%%PY"                 {if(add){add=false;
      yyclose();
     return symbol(sym.PY,new String(yytext()));}}
    "!"                 {if(add){ return symbol(sym.NOT,new String(yytext()));}}


    "return"                 {if(add){return symbol(sym.RETURN,new String(yytext()));}}
    "if"                 {if(add){return symbol(sym.IF,new String(yytext()));}}
    ","                 {if(add){return symbol(sym.COMA,new String(yytext()));}}
    "("                 {if(add){return symbol(sym.AP,new String(yytext()));}}
    ")"                 {if(add){return symbol(sym.CP,new String(yytext()));}}
     ">"|"<"|">="|"<="|"!="|"=="      {if(add){return symbol(sym.OP,new String(yytext()));}}
     "+"                         {if(add){return symbol(sym.SUM,new String(yytext()));}}
     "-"                         {if(add){return symbol(sym.RES,new String(yytext()));}}
     "/"                         {if(add){return symbol(sym.DIV,new String(yytext()));}}
     "*"                         {if(add){return symbol(sym.MUL,new String(yytext()));}}
     "%"                         {if(add){return symbol(sym.MODUL,new String(yytext()));}}
     "="                 {if(add){return symbol(sym.EQUALS,new String(yytext()));}}
     "&&"                 {if(add){return symbol(sym.AND,new String(yytext()));}}
     "public"                 {if(add){return symbol(sym.PUBLIC,new String(yytext()));}}
     "void"                  {if(add){return symbol(sym.VOID,new String(yytext()));}}
     "int"                     {if(add){return symbol(sym.INT,new String(yytext()));}}
     "double"|"float"                 {if(add){return symbol(sym.DOUBLE,new String(yytext()));}}
     "char"                          {if(add){return symbol(sym.CHAR,new String(yytext()));}}
     "System.out.println"                 {if(add){return symbol(sym.PRINTLN,new String(yytext()));}}
     "System.out.print"                 {if(add){return symbol(sym.PRINT,new String(yytext()));}}

     "{"                 {if(add){return symbol(sym.AC,new String(yytext()));}}
     "}"                 {if(add){return symbol(sym.CC,new String(yytext()));}}

     "intinput"   {if(add){return symbol(sym.INPUTI,new String(yytext()));}}
     "floatinput" {if(add){return symbol(sym.INPUTD,new String(yytext()));}}
     "charinput"  {if(add){return symbol(sym.INPUTC,new String(yytext()));}}

     "||"                 {if(add){return symbol(sym.OR,new String(yytext()));}}
     ("\n")+             {}
     (" ")+ {}
     ("\t")+ {}
     {LineTerminator} {}
     {Comment} {}
    ("this."){Identifier}                {if(add){return symbol(sym.ID,new String(yytext()));}}
    {Identifier}                {if(add){return symbol(sym.ID,new String(yytext()));}}
    ({numero})+(".")({numero})+         {if(add){return symbol(sym.REAL,new Double(yytext()));}}
    ({numero})+                        {if(add){return symbol(sym.ENTERO,new Integer(yytext()));}}

     .   {
     if(add){
        fila=yyline+1;
          columna+=yycolumn+1;
        errores.AddError(0,yyline+1,yycolumn+1,yytext());}}

}

  <VERIFY> {
      \"                            {   yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                      if(add){  return symbol(sym.CADENA,val);}
                                    }
      [^\"]+                         {string.append(yytext());yybegin(VERIFY);}
      \\t                           { string.append('\t'); yybegin(VERIFY); }
      \\n                           { string.append('\n'); yybegin(VERIFY); }

      \\r                           { string.append('\r'); yybegin(VERIFY);}
      \\\"                          { string.append('\"'); yybegin(VERIFY);}
      \\                            { string.append('\\'); yybegin(VERIFY);}
}

<CHAR>{
    "'"                            { yybegin(YYINITIAL);
                                     String val=string.toString();
                                     string=new StringBuffer();
                                     char y=val.charAt(0);
                                   if(add){  return symbol(sym.CARACTER,y);}
                                  }
    .                             { string=new StringBuffer();yybegin(STRING);}

}
<STRING>{
    "'"                           {yybegin(YYINITIAL); {}}
    [^']                          {string.append(yytext());yybegin(CHAR);}
    \\t                           { string.append('\t'); yybegin(CHAR); }
    \\n                           { string.append('\n'); yybegin(CHAR); }
    \\r                           { string.append('\r'); yybegin(CHAR);}
    \\\"                          { string.append('\"'); yybegin(CHAR);}
    \\                            { string.append('\\'); yybegin(CHAR);}
}