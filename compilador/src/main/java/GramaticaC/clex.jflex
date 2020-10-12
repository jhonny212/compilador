package GramaticaC;
import java_cup.runtime.Symbol;
import Errores.ErrorClass;
%%
%class clex
%cup
%cupdebug
%line
%column
%full
%char
%public
%{
    StringBuffer string = new StringBuffer();
public int fila=0,columna=0;
     public ErrorClass errores=new ErrorClass();
    private Symbol symbol(int type, Object value) {
    fila=yyline+1;
            columna+=yycolumn+1;
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}
numero=[0-9]
letra=[a-zA-Z]
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

    "int"                     {return symbol(sym.INT,new String(yytext()));}
    "float"                 {return symbol(sym.DOUBLE,new String(yytext()));}
    "char"                          {return symbol(sym.CHAR,new String(yytext()));}
    "const"                          {return symbol(sym.CONST,new String(yytext()));}

    "while"                 {return symbol(sym.WHILE,new String(yytext()));}
    "else"                 {return symbol(sym.ELSE,new String(yytext()));}
    "for"                 {return symbol(sym.FOR,new String(yytext()));}
    "do"                 {return symbol(sym.DO,new String(yytext()));}
    ";"                 {return symbol(sym.PUNTOCOMA,new String(yytext()));}
    "switch"                 {return symbol(sym.SWITCH,new String(yytext()));}
    "case"                 {return symbol(sym.CASE,new String(yytext()));}
    "default"                 {return symbol(sym.DEFAULT,new String(yytext()));}
    "break"                 {return symbol(sym.BREAK,new String(yytext()));}
    ":"                 {return symbol(sym.BOTHPOINT,new String(yytext()));}
    "#include"                 {return symbol(sym.INCLUDE,new String(yytext()));}
    "void"                 {return symbol(sym.VOID,new String(yytext()));}
    "main"                 {return symbol(sym.MAIN,new String(yytext()));}
    "clrscr"                 {return symbol(sym.CLEAR,new String(yytext()));}
    "getch"                 {return symbol(sym.GETCH,new String(yytext()));}

    "&"                 {return symbol(sym.Y,new String(yytext()));}
    ("VB.")({Identifier} )       {return symbol(sym.CALLVB,new String(yytext()));}
    ("PY.")({Identifier} )       {return symbol(sym.CALLPY,new String(yytext()));}
    ("JAVA.")({Identifier} )      {return symbol(sym.INITJAVA,new String(yytext()));}
    ({Identifier} )(".")({Identifier} )       {return symbol(sym.CALLJAVA,new String(yytext()));}

    ("-")({numero})+(".")({numero})     {return symbol(sym.REAL,new Double(yytext()));}
    ("-")({numero})+                    {return symbol(sym.ENTERO,new Integer(yytext()));}

    "if"                 {return symbol(sym.IF,new String(yytext()));}
    ","                 {return symbol(sym.COMA,new String(yytext()));}
    "("                 {return symbol(sym.AP,new String(yytext()));}
    ")"                 {return symbol(sym.CP,new String(yytext()));}
     ">"|"<"|">="|"<="|"!="      {return symbol(sym.OP,new String(yytext()));}
     "+"                         {return symbol(sym.SUM,new String(yytext()));}
     "-"                         {return symbol(sym.RES,new String(yytext()));}
     "/"                         {return symbol(sym.DIV,new String(yytext()));}
     "*"                         {return symbol(sym.MUL,new String(yytext()));}
     "%"                         {return symbol(sym.MODUL,new String(yytext()));}
     "="                 {return symbol(sym.EQUALS,new String(yytext()));}
     "&&"                 {return symbol(sym.AND,new String(yytext()));}
     "{"                 {return symbol(sym.AC,new String(yytext()));}
     "}"                 {return symbol(sym.CC,new String(yytext()));}
     "["                 {return symbol(sym.ACOR,new String(yytext()));}
     "]"                 {return symbol(sym.CCOR,new String(yytext()));}
     "printf"   {return symbol(sym.PRINT,new String(yytext()));}
     "scanf"                 {return symbol(sym.SCANF,new String(yytext()));}

     "intinput"   {return symbol(sym.INPUTI,new String(yytext()));}

     "||"                 {return symbol(sym.OR,new String(yytext()));}
     ("\n")+             {}
     (" ")+ {}
     ("\t")+ {}

    {Identifier}                {return symbol(sym.ID,new String(yytext()));}
    ({numero})+(".")({numero})         {return symbol(sym.REAL,new Double(yytext()));}
    ({numero})+                        {return symbol(sym.ENTERO,new Integer(yytext()));}
     .   {
            fila=yyline+1;
            columna+=yycolumn+1;
            errores.AddError(0,yyline+1,yycolumn+1,yytext());}


}

  <VERIFY> {

      \"                            {   yybegin(YYINITIAL);
                                        String val=string.toString();
                                        String xx_=val;
                                        string=new StringBuffer();
                                        int x=val.indexOf("%d");
                                        if(x==-1){
                                         x=val.indexOf("%c");
                                         if(x==-1){
                                            x=val.indexOf("%f");
                                            if(x!=-1){
                                                val=val.replaceFirst("%f","");
                                                x=val.indexOf("%f");
                                                if(x==-1){
                                                return symbol(sym.MASCARA,xx_);
                                                }
                                            }
                                         }else{
                                            val=val.replaceFirst("%c","");
                                            x=val.indexOf("%c");
                                            if(x==-1){
                                            return symbol(sym.MASCARA,xx_);
                                            }
                                         }
                                        }else{
                                            val=val.replaceFirst("%d","");
                                            x=val.indexOf("%d");
                                            if(x==-1){
                                            return symbol(sym.MASCARA,xx_);
                                            }
                                        }
                                        return symbol(sym.CADENA,xx_);
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
                                     return symbol(sym.CARACTER,y);
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