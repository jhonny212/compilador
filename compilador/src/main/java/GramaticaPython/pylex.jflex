package GramaticaPython;
import java_cup.runtime.Symbol;
import Errores.ErrorClass;
%%
%class pylex
%cup
%cupdebug
%line
%column
%full
%char
%public
%{
    StringBuffer string = new StringBuffer();
 public int fila,columna=0;
    public ErrorClass errores=new ErrorClass();
    public boolean add=true;
    private Symbol symbol(int type, Object value) {
    fila=yyline+1;
           columna+=yycolumn+1;
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}
numero=[0-9]
letra=[a-zA-Z]
InputCharacter = [^\r\n]
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* ("\r"|"\n"|"\r\n")?|"#" {InputCharacter}* ("\r"|"\n"|"\r\n")?

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
    "while"                 {if(add){return symbol(sym.WHILE,new String(yytext()));}}
    "else"                 {if(add){return symbol(sym.ELSE,new String(yytext()));}}
    "for"                 {if(add){return symbol(sym.FOR,new String(yytext()));}}
    "return"                 {if(add){return symbol(sym.RETURN,new String(yytext()));}}
    "elif"                 {if(add){return symbol(sym.ELSEIF,new String(yytext()));}}
    "PY"                 {if(add){return symbol(sym.PY,new String(yytext()));}}

    "if"                 {if(add){return symbol(sym.IF,new String(yytext()));}}
    ":"                 {if(add){return symbol(sym.TWOPOINT,new String(yytext()));}}
    ","                 {if(add){return symbol(sym.COMA,new String(yytext()));}}
    "in"                 {if(add){return symbol(sym.IN,new String(yytext()));}}
    "("                 {if(add){return symbol(sym.AP,new String(yytext()));}}
    ")"                 {if(add){return symbol(sym.CP,new String(yytext()));}}
    "range"                 {if(add){return symbol(sym.RANGE,new String(yytext()));}}
     ">"|"<"|">="|"<="|"!="|"=="      {if(add){return symbol(sym.OP,new String(yytext()));}}
     "+"                         {if(add){return symbol(sym.SUM,new String(yytext()));}}
     "-"                         {if(add){return symbol(sym.RES,new String(yytext()));}}

     "/"                         {if(add){return symbol(sym.DIV,new String(yytext()));}}
    "*"                         {if(add){return symbol(sym.MUL,new String(yytext()));}}
    "%%PROGRAMA"                         {if(add){add=false;
     yyclose();
    return symbol(sym.CODC,new String(yytext()));}}

    "%"                         {if(add){return symbol(sym.MODUL,new String(yytext()));}}
    "="                 {if(add){return symbol(sym.EQUALS,new String(yytext()));}}
    "and"                 {if(add){return symbol(sym.AND,new String(yytext()));}}
    "def"                 {if(add){return symbol(sym.DEF,new String(yytext()));}}
    "print"                 {if(add){return symbol(sym.PRINT,new String(yytext()));}}
    "intinput"   {if(add){return symbol(sym.INPUTI,new String(yytext()));}}
    "floatinput" {if(add){return symbol(sym.INPUTD,new String(yytext()));}}
    "charinput"  {if(add){return symbol(sym.INPUTC,new String(yytext()));}}
    "or"                 {if(add){return symbol(sym.OR,new String(yytext()));}}
    "not"                 {if(add){return symbol(sym.NOT,new String(yytext()));}}
    (("\n")+(("\n")|("\t")|(" ")|{Comment})*)  {if(add){return symbol(sym.SALTO_LINEA,new String(yytext()));}}
    (" ")+ {}
    ("\t")+ {}
      {Comment}                   {}

    {Identifier}                {if(add){return symbol(sym.ID,new String(yytext()));}}
    ({numero})+(".")({numero})+         {if(add){return symbol(sym.REAL,new Double(yytext()));}}
    ({numero})+                        {if(add){return symbol(sym.ENTERO,new Integer(yytext()));}}

    .   {if(add){
    fila=yyline+1;
      columna+=yycolumn+1;
    errores.AddError(0,yyline+1,yycolumn+1,yytext());}}


}

  <VERIFY> {
      \"                            {   yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                       if(add){ return symbol(sym.CADENA,val);}
                                    }
      [^\"]                         {string.append(yytext());yybegin(CHAR);}
      \\t                           { string.append("\\t"); yybegin(CHAR); }
      \\n                           { string.append("\\n"); yybegin(CHAR); }

      \\r                           { string.append("\\r"); yybegin(CHAR);}
      \\\"                          { string.append('\"'); yybegin(CHAR);}
      \\                            { string.append('\\'); yybegin(CHAR);}
}

<CHAR>{
    \"                            { yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                        char y=val.charAt(0);
                                     if(add){   return symbol(sym.CARACTER,y);}}
    [^\"]                         {string.append(yytext());yybegin(STRING);}
    \\t                           { string.append("\\t"); yybegin(STRING); }
    \\n                           { string.append("\\n"); yybegin(STRING); }

    \\r                           { string.append("\\r"); yybegin(STRING);}
    \\\"                          { string.append('\"'); yybegin(STRING);}
    \\                            { string.append('\\'); yybegin(STRING);}
}
<STRING>{
    \"                            { yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                     if(add){   return symbol(sym.CADENA,val);}}
    [^\"]+                         {string.append(yytext());yybegin(STRING);}
    \\t                           { string.append("\\t"); yybegin(STRING); }
    \\n                           { string.append("\\n"); yybegin(STRING); }

    \\r                           { string.append("\\r"); yybegin(STRING);}
    \\\"                          { string.append('\"'); yybegin(STRING);}
    \\                            { string.append('\\'); yybegin(STRING);}
}